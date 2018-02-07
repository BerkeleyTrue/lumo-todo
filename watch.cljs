(require '[lumo.build.api :as b])

(b/watch
  "src"
  {
   :main 'todo.main
   :output-to "out/main.js"
   :target :nodejs})
