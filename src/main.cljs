(ns todo.main
  (:require
    [todo.ring.core :refer [run]]
    [todo.ring.utils.response :as r]))

(enable-console-print!)

(defn handler
  ; the main request handler
  [{:keys [uri]}]
  (do
    (println "Request coming from uri: " uri)
    (r/response "Hello Berks")))

(run handler
     {:port 3000})
