(ns todo.ring.utils.request)

(defn get-uri
  [req]
  (:uri req))
