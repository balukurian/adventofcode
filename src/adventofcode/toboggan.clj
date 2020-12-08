(ns adventofcode.toboggan)


(def forest-map (slurp "resources/day3input"))

(defn grown-map [forest-map]
  (let [forest-map-list (.split forest-map "\n")]
    (map (fn [row]
           (apply str (take 100 (repeat row)))) forest-map-list)))

(defn tree? [object]
  (= object "#"))

(def puzzle-two-inputs
  [{:right 1
    :down  1}
   {:right 3
    :down  1}
   {:right 5
    :down  1}
   {:right 7
    :down  1}
   {:right 1
    :down  2}])

(defn trees-in-path [right down]
  (loop [coll (take-nth down (grown-map forest-map))
         index 0
         acc 0]
    (if (= coll ())
      acc
      (recur (rest coll)
             (+ index right)
             (if (tree? (nth (.split (first coll) "") index))
               (inc acc)
               acc)))))

(defn puzzle-two-answer []
  (reduce * (map #(trees-in-path (:right %) (:down %)) puzzle-two-inputs)))
