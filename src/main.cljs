(ns todo.main
  (:require
    [todo.ring.core :refer [run]]
    [todo.ring.utils.response :as r]
    [todo.router.core :refer [GET]]))

(enable-console-print!)

(run
  (GET "/"
   (fn []
     (do
       (println "foo")
       (r/response "Hello Berks"))))
  {:port 3000})
