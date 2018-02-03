(require '[lumo.build.api :as b])

(b/build
  "src"
  {
   :main 'todo.main
   :output-to "out/main.js"
   :target :nodejs})
