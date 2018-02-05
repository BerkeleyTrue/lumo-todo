(ns todo.ring.utils)

(defn response
  [body]
  {:body body
   :status 200})
