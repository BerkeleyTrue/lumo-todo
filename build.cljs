(require '[lumo.build.api :as b])

(b/build
  "src"
  { :output-to "out/main.js"})
