(ns adventofcode.seatfinder.parser)

(defn process-input [input]
  (into [] (.split input "\n")))
