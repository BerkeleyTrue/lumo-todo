(ns todo.ring.core
  (:require http
            url
            [clojure.string :as str]))

(defn- node-req-to-ring
  ; Convert incoming node request to ring request
  [node-req]
  (let [socket (.-socket node-req)
        parsed-url (.parse url (.-url node-req))]
    {
      :server-port (.-localPort socket)
      :server-name (.-localAddress socket)
      :remote-addr (.-remoteAddress socket)
      :uri (.-pathname parsed-url)
      :query-string (.-query parsed-url)
      :scheme :http
      :headers (.-headers node-req)
      :request-method (keyword
                        (str/lower-case
                          (.-method node-req)))}))

(defn- ring-res-to-node
  ; Convert a ring response to a node response
  [{:keys [status headers body]} res]
  (set! (.-statusCode res) status)
  (dorun (map #(.setHeader res % (get headers %))
              (keys headers)))
  (.end res body))

(defn- ring-to-node-handler
  "Convert ring handler to node handler"
  [handler]
  (fn [req, res]
    (ring-res-to-node
      (handler (node-req-to-ring req))
      res)))

(defn run
  "Run a Node http server"
  [handler {port :port :or 3000}]
  (.listen
    (.createServer http (ring-to-node-handler handler))
    port
    (fn [err]
      (if err
        (println err)
        (println (str "Server started on port " port))))))
