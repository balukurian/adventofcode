(ns adventofcode.seatfinder.seatfinder)

(def total-rows 128)
(def total-columns 8)

(defn- row-representation [seat-representation]
  (into [] (.split (.substring seat-representation 0 7) "")))

(defn- column-representation [seat-representation]
  (into [] (.split (.substring seat-representation 7 10) "")))

(defn- row-direction-picker [direction]
  (if (= direction "F")
    first
    second))

(defn- column-direction-picker [direction]
  (if (= direction "L")
    first
    second))

(defn- find [representation total direction-picker]
  (loop [representation representation
         total total
         current (range 0 total)]
    (if (= (count current) 1)
      (first current)
      (recur (rest representation)
             (/ total 2)
             ((direction-picker (first representation)) (partition (/ total 2) current))))))

(defn- find-column [seat-representation]
  (find (column-representation seat-representation) total-columns column-direction-picker))

(defn- find-row [seat-representation]
  (find (row-representation seat-representation) total-rows row-direction-picker))

(defn find-seat-id [seat-representation]
  (+ (* (find-row seat-representation) 8)
     (find-column seat-representation)))
