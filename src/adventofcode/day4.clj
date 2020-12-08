(ns adventofcode.day4
  (:require [adventofcode.passport.parser :as parser]
            [adventofcode.passport.validator :as validator]))

(defn valid-passports []
  (let [input (slurp "resources/day4input")
        passports (parser/process-input input)
        passport-statuses (map #(validator/valid-passport? %) passports)]
    (count (filter #(true? %) passport-statuses))))