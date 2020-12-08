(ns adventofcode.day5
  (:require [adventofcode.seatfinder.seatfinder :as seat-finder]
            [adventofcode.seatfinder.parser :as parser]))

(defn get-seat-ids []
  (let [input (slurp "resources/day5input")
        seat-representations (parser/process-input input)
        seat-ids (map #(seat-finder/find-seat-id %) seat-representations)]
    (into #{} seat-ids)))

(defn find-max-seat-id []
  (apply max (get-seat-ids)))


(defn find-my-seat-id []
  (let [all-seats (range (apply min (get-seat-ids)) (+ (apply max (get-seat-ids)) 1))
        occupied-seats (get-seat-ids)
        seat-occupied-map (map (fn [seat-id]
                                 {:seat-id  seat-id
                                  :occupied (contains? occupied-seats seat-id)}) all-seats)]
    (filter #(false? (get % :occupied)) seat-occupied-map)))