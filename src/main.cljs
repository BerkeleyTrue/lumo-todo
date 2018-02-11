(ns todo.main
  (:require
    [todo.ring.core :refer [run]]
    [todo.ring.utils.response :as r]
    [todo.router.core :as ring :refer [GET ANY]]))

(enable-console-print!)

(run
  (ring/combine-routes
    (GET "/foo"
      (fn []
        (println "foo")
        (r/response "Hello Foo")))
    (GET "/"
      (fn []
        (do
          (println "home")
          (r/response "Hello Berks"))))
    (ANY "*"
      (fn []
        (println "404")
        (r/not-found "404 Not Found"))))
  {:port 3000})
