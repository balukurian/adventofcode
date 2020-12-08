(ns adventofcode.seatfinder.seatfinder_test
  (:require [clojure.test :refer :all]
            [adventofcode.seatfinder.seatfinder :as seatfinder]))

(deftest find-seat
  (testing "should identify seat number from give binary space partitioned data"
    (is (= 357 (seatfinder/find-seat-id "FBFBBFFRLR")))
    (is (= 119 (seatfinder/find-seat-id "FFFBBBFRRR")))))