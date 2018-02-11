(ns todo.router.core
  (:require path-to-regexp
            [todo.ring.utils.request :refer [get-uri]]))

(defn- decode-param
  [val]
  (if (or (string? val) (= (count val) 0))
    val
    (try
      (js/decodeURIComponent val)
      (catch js/URIError e
        (assoc! e :status 400 :statusCode 400 :message (str "failed to decode param '" val "'"))
        (throw e)))))

(defn- match-route
  [path {:keys [end] :or {end true}}]
  (let [
        route-keys []
        route-regexp (path-to-regexp path route-keys)
        fast-star (= path "*")
        fast-slash (and (= path "/") (not end))]
    (cond 
      (true? fast-slash) (constantly {:params {} :path ""})
      (true? fast-star) (fn [req] {:params (decode-param (get-uri req)) :path "/"})
      :else
        (fn [req]
          (let [uri (get-uri req)]
            (if-let [match (.exec route-regexp uri)]
              {
                :fast-star fast-star
                :fast-slash fast-slash
                :match match
                :params (as-> route-keys v
                            (map (fn [key] (:name key)) v)
                            (zipmap v (rest match)))}))))))

(defn- apply-routes
  [request & handlers]
  (some #(% request) handlers))

(defn combine-routes
  [& routes]
  #(apply apply-routes % routes))

(defn GET
  [path handler]
  (let [path-to-match (match-route path)]
    (fn [req]
      (if (= (:request-method req) :get)
        (if-let [match (path-to-match req)]
          (handler req))))))

(defn ALL
  [path handler]
  (let [path-to-match (match-route path)]
    (fn [req]
      (if-let [match (path-to-match req)]
        (handler req)))))
