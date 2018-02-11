(require '[lumo.build.api :as b])

(b/watch
  "src"
  { :main 'todo.main
    :output-to "out/main.js"
    :output-dir "out"
    :target :nodejs
    :source-map "out/main.js.map"})

