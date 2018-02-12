(ns todo.main
  (:require
    [todo.ring.core :refer [run]]
    [todo.ring.utils.response :as r]
    [todo.router.core :as router]))

(enable-console-print!)

(run
  (router/combine-routes
    (router/GET "/foo"
      (fn []
        (println "foo")
        (r/response "Hello Foo")))
    (router/POST "/:bar"
      (fn []
        (println "post bar")
        (r/response "Post Successful")))
    (router/GET "/"
      (fn []
        (println "home")
        (r/response "Hello Berks")))
    (router/ALL "*"
      (fn []
        (println "404")
        (r/not-found "404 Not Found"))))
  {:port 3000})
