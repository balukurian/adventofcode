(ns adventofcode.customsquestions.parser_test
  (:require [clojure.test :refer :all]
            [adventofcode.customsquestions.parser :as parser]))

(deftest process-input
  (testing "should process input into a list of group answers"
    (is (= ["a\nb\nc"
            "a\nb\nc"] (parser/process-input "a\nb\nc\n\na\nb\nc")))))
