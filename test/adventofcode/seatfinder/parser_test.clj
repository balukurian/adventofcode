(ns adventofcode.seatfinder.parser_test
  (:require [clojure.test :refer :all]
            [adventofcode.seatfinder.parser :as parser]))

(deftest read-raw-input
  (testing "raw input is converted to list of binary space partitioning"
    (is (= ["BFFFBBFRRR"
            "FFFBBBFRRR"] (parser/process-input "BFFFBBFRRR\nFFFBBBFRRR")))))