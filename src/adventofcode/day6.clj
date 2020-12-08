(ns adventofcode.day6
  (:require [adventofcode.customsquestions.parser :as parser]
            [adventofcode.customsquestions.group :as group]))

(defn result []
  (let [input (parser/process-input (slurp "resources/day6input"))
        group-results (map #(group/result %) input)]
    (reduce + group-results)))
