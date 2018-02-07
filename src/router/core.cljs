(ns todo.router.core)

(defn GET
  [path handler]
  (fn [req]
    (if (= (:request-method req) :get)
      (handler)
      nil)))
