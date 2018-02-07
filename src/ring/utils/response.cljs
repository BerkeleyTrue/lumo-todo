(ns todo.ring.utils.response)

(defn response
  [body]
  {:body body
   :status 200})

(defn header
  "Add a header key/value to a response"
  [resp name value]
  (assoc-in resp [:headers name] value))


(defn content-type
  "Add content type header to a resonse"
  [resp content-type]
  (header "Content-Type" content-type))

(defn redirect
  "Create a redirect resonse"
  ([url] (redirect url 302))
  ([url status]
   (header
     { :status status :body ""}
     "Location"
     url)))
