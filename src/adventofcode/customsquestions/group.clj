(ns adventofcode.customsquestions.group)

(defn alphabet-map []
  (reduce (fn [acc value]
            (assoc acc (keyword (Character/toString (char value))) 0))
          {}
          (range 97 (+ 97 26))))

(defn questions-answered [answers]
  (into [] (.split answers "")))

(defn answer-results [group-answers]
  (map (fn [answer]
         (reduce (fn [acc current-answer]
                   (update acc (keyword current-answer) inc))
                 (alphabet-map)
                 (questions-answered answer))) group-answers))

(defn add [alphabet-map-a alphabet-map-b]
  (reduce (fn [acc key]
            (assoc acc key (+ (get alphabet-map-a key) (get alphabet-map-b key))))
          {}
          (keys alphabet-map-a)))

(defn group-answer-result [individual-answers]
  (reduce (fn [acc answer]
            (add acc answer)) (alphabet-map) individual-answers))

(defn result [answers]
  (let [group-answers (.split answers "\n")
        group-size (count group-answers)
        individual-answers (answer-results group-answers)
        group-answer-result (group-answer-result individual-answers)]
    (count (filter #(= group-size (get group-answer-result %)) (keys (alphabet-map))))))
