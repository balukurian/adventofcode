(ns adventofcode.customsquestions.parser)

(defn process-input [input]
  (into [] (.split input "\n\n")))