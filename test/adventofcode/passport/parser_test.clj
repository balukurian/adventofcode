(ns adventofcode.passport.parser_test
  (:require [clojure.test :refer :all]
            [adventofcode.passport.parser :as parser]))


(deftest raw-input-parser
  (testing "should process raw passport field into passport object"
    (is (= [{:ecl "gry"}] (parser/process-input "ecl:gry"))))

  (testing "should process multiple raw passport fields into passport object"
    (is (= [{:ecl "gry"
            :pid "123"}] (parser/process-input "ecl:gry pid:123"))))
  (testing "should process raw passport fields in multiple lines into passport object"
    (is (= [{:ecl "gry"
            :pid "123"
            :byr "123"}] (parser/process-input "ecl:gry pid:123\nbyr:123"))))
  (testing "should process multiple raw passport fields into passport objects"
    (is (= [{:ecl "gry"
             :pid "123"
             :byr "123"}
            {:ecl "gry"
             :pid "123"
             :byr "123"}](parser/process-input "ecl:gry pid:123\nbyr:123\n\necl:gry pid:123\nbyr:123")))))