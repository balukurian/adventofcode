(ns adventofcode.passport.parser)

(defn- field-key [field-parts]
  (keyword (first field-parts)))

(defn- field-value [field-parts]
  (second field-parts))

(defn- split-by-new-line [input]
  (.split input "\n"))

(defn- split-by-colon [input]
  (.split input ":"))

(defn- split-by-space [input]
  (.split input " "))

(defn- split-by-multiple-new-lines [times input]
  (.split input (apply str (take times (repeat "\n")))))

(defn- merge-field-lines [input]
  (apply str (interpose " " input)))

(defn- form-passport [passport-fields]
  (reduce (fn [acc field]
            (let [field-parts (split-by-colon field)]
              (assoc acc (field-key field-parts) (field-value field-parts))))
          {}
          (split-by-space passport-fields)))

(defn- parse-raw-passport [raw-passport]
  (->> raw-passport
       (split-by-new-line)
       (merge-field-lines)
       (form-passport)))

(defn process-input [input]
  (->> input
       (split-by-multiple-new-lines 2)
       (map parse-raw-passport)))

