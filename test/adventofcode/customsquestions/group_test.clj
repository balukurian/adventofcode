(ns adventofcode.customsquestions.group_test
  (:require [clojure.test :refer :all]
            [adventofcode.customsquestions.group :as group]))

(deftest individual-result
  (testing "should get count of all questions that are answered yes by a person in a group"
    (is (= 3 (group/result "abc")))))

(deftest group-result
  (testing "should get count of all questions that are answered by everyone in a group"
    (is (= 0 (group/result "abc\nefg")))
    (is (= 1 (group/result "a\na\na")))
    (is (= 1 (group/result "ab\nbc\ndb")))))


