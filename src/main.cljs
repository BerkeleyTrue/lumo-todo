(ns todo.main
  (:require
    [todo.ring.core :refer [run]]
    [todo.ring.utils :as r]))
    

(enable-console-print!)

(defn handler
  ; the main request handler
  [{:keys [uri]}]
  (do
    (println "Request coming from uri: " uri)
    (r/response "hello World")))

(run handler
     {:port 3000})
