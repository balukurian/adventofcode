(ns adventofcode.passport.validator-test
  (:require [clojure.test :refer :all]
            [adventofcode.passport.validator :as validator]))


(deftest valid-passport
  (testing "should return true if passport is valid"
    (is (= true (validator/valid-passport? {:ecl "gry"
                                            :pid "860033327"
                                            :eyr "2020"
                                            :hcl "#fffffd"
                                            :byr "1937"
                                            :iyr "2017"
                                            :cid "147"
                                            :hgt "183cm"})))))

(deftest valid-birth-year
  (testing "passport should have valid birth of four digits and values between 1920 and 2002"
    (is (= true (validator/valid-passport? {:ecl "gry"
                                            :pid "860033327"
                                            :eyr "2020"
                                            :hcl "#fffffd"
                                            :byr "1937"
                                            :iyr "2017"
                                            :cid "147"
                                            :hgt "183cm"})))
    (is (= false (validator/valid-passport? {:ecl "gry"
                                             :pid "860033327"
                                             :eyr "2020"
                                             :hcl "#fffffd"
                                             :byr "2003"
                                             :iyr "2017"
                                             :cid "147"
                                             :hgt "183cm"})))
    (is (= false (validator/valid-passport? {:ecl "gry"
                                             :pid "860033327"
                                             :eyr "2020"
                                             :hcl "#fffffd"
                                             :byr "20033"
                                             :iyr "2017"
                                             :cid "147"
                                             :hgt "183cm"})))
    (is (= false (validator/valid-passport? {:ecl "gry"
                                             :pid "860033327"
                                             :eyr "2020"
                                             :hcl "#fffffd"
                                             :byr "1919"
                                             :iyr "2017"
                                             :cid "147"
                                             :hgt "183cm"})))))

(deftest valid-year-of-issue
  (testing "year of issue in passport should be of four digits and between 2010 and 2020"
    (is (= false (validator/valid-passport? {:ecl "gry"
                                             :pid "860033327"
                                             :eyr "2020"
                                             :hcl "#fffffd"
                                             :byr "2002"
                                             :iyr "2009"
                                             :cid "147"
                                             :hgt "183cm"})))
    (is (= true (validator/valid-passport? {:ecl "gry"
                                            :pid "860033327"
                                            :eyr "2020"
                                            :hcl "#fffffd"
                                            :byr "2002"
                                            :iyr "2010"
                                            :cid "147"
                                            :hgt "183cm"})))
    (is (= false (validator/valid-passport? {:ecl "gry"
                                             :pid "860033327"
                                             :eyr "2020"
                                             :hcl "#fffffd"
                                             :byr "2002"
                                             :iyr "20101"
                                             :cid "147"
                                             :hgt "183cm"})))))

(deftest valid-year-of-expiration
  (testing "year of expiration in passport should be between 2020 and 2030"
    (is (= false (validator/valid-passport? {:ecl "gry"
                                             :pid "860033327"
                                             :eyr "2019"
                                             :hcl "#fffffd"
                                             :byr "2002"
                                             :iyr "2010"
                                             :cid "147"
                                             :hgt "183cm"})))
    (is (= true (validator/valid-passport? {:ecl "gry"
                                            :pid "860033327"
                                            :eyr "2020"
                                            :hcl "#fffffd"
                                            :byr "2002"
                                            :iyr "2010"
                                            :cid "147"
                                            :hgt "183cm"}))))
  (testing "year of expiration should be four digits long"
    (is (= false (validator/valid-passport? {:ecl "gry"
                                             :pid "860033327"
                                             :eyr "20191"
                                             :hcl "#fffffd"
                                             :byr "2002"
                                             :iyr "2010"
                                             :cid "147"
                                             :hgt "183cm"})))))

(deftest valid-height
  (testing "passport is invalid if height does not have valid measurement in cm or inches"
    (is (= true (validator/valid-passport? {:ecl "gry"
                                            :pid "860033327"
                                            :eyr "2020"
                                            :hcl "#fffffd"
                                            :byr "2002"
                                            :iyr "2010"
                                            :cid "147"
                                            :hgt "183cm"})))
    (is (= true (validator/valid-passport? {:ecl "gry"
                                            :pid "860033327"
                                            :eyr "2020"
                                            :hcl "#fffffd"
                                            :byr "2002"
                                            :iyr "2010"
                                            :cid "147"
                                            :hgt "59in"})))
    (is (= false (validator/valid-passport? {:ecl "gry"
                                             :pid "860033327"
                                             :eyr "2020"
                                             :hcl "#fffffd"
                                             :byr "2002"
                                             :iyr "2010"
                                             :cid "147"
                                             :hgt "183x"}))))
  (testing "passport height in cm should be in the valid range"
    (is (= false (validator/valid-passport? {:ecl "gry"
                                             :pid "860033327"
                                             :eyr "2020"
                                             :hcl "#fffffd"
                                             :byr "2002"
                                             :iyr "2010"
                                             :cid "147"
                                             :hgt "2000cm"}))
        (is (= false (validator/valid-passport? {:ecl "gry"
                                                 :pid "860033327"
                                                 :eyr "2020"
                                                 :hcl "#fffffd"
                                                 :byr "2002"
                                                 :iyr "2010"
                                                 :cid "147"
                                                 :hgt "1in"}))))))

(deftest valid-hair-color
  (testing "Hair color should not contain invalid values"
    (is (= false (validator/valid-passport? {:ecl "gry"
                                             :pid "860033327"
                                             :eyr "2020"
                                             :hcl "#fffffz"
                                             :byr "2002"
                                             :iyr "2010"
                                             :cid "147"
                                             :hgt "59in"})))))
(deftest valid-eye-color
  (testing "eye color should be exactly one of: amb blu brn gry grn hzl oth"
    (is (= true (validator/valid-passport? {:ecl "gry"
                                            :pid "860033327"
                                            :eyr "2020"
                                            :hcl "#fffffa"
                                            :byr "2002"
                                            :iyr "2010"
                                            :cid "147"
                                            :hgt "59in"})))
    (is (= false (validator/valid-passport? {:ecl "x"
                                             :pid "860033327"
                                             :eyr "2020"
                                             :hcl "#fffffz"
                                             :byr "2002"
                                             :iyr "2010"
                                             :cid "147"
                                             :hgt "59in"})))))

(deftest valid-passport-number
  (testing "passport number should be numeric and nine digits long"
    (is (= false (validator/valid-passport? {:ecl "gry"
                                             :pid "123123123123"
                                             :eyr "2020"
                                             :hcl "#fffffa"
                                             :byr "2002"
                                             :iyr "2010"
                                             :cid "147"
                                             :hgt "59in"})))
    (is (= false (validator/valid-passport? {:ecl "gry"
                                             :pid "12345678a"
                                             :eyr "2020"
                                             :hcl "#fffffa"
                                             :byr "2002"
                                             :iyr "2010"
                                             :cid "147"
                                             :hgt "59in"})))
    (is (= true (validator/valid-passport? {:ecl "gry"
                                             :pid "123456789"
                                             :eyr "2020"
                                             :hcl "#fffffa"
                                             :byr "2002"
                                             :iyr "2010"
                                             :cid "147"
                                             :hgt "59in"})))))

(deftest invalid-passport
  (testing "should return false if passport is invalid"
    (is (= false (validator/valid-passport? {:ecl "gry"
                                             :eyr "2019"
                                             :hcl "#fffffd"
                                             :byr "1937"
                                             :iyr "2017"
                                             :cid "147"
                                             :hgt "183cm"})))))

(deftest passport-missing-cid
  (testing "passport is valid if cid is the only missing field"
    (is (= true (validator/valid-passport? {:ecl "gry"
                                            :pid "860033327"
                                            :eyr "2020"
                                            :hcl "#fffffd"
                                            :byr "1937"
                                            :iyr "2017"
                                            :hgt "183cm"}))))

  (testing "passport is invalid if there are other fields missing along with cid"
    (is (= false (validator/valid-passport? {:ecl "gry"
                                             :pid "860033327"
                                             :eyr "2020"
                                             :hcl "#fffffd"
                                             :iyr "2017"
                                             :hgt "183cm"})))))
