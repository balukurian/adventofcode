(ns adventofcode.password.validator)

(defn alphabets []
  (map #(keyword (str (char %)))
       (range 97 123)))

(defn get-password-letter-count [password]
  (let [alphabet-values (map (fn [alphabet]
                               {alphabet 0}) (alphabets))]
    (reduce (fn [acc current-letter]
              (update acc (keyword current-letter) inc))
            (reduce into alphabet-values)
            (.split password ""))))

(defn transform-password [raw-password]
  (let [parts (.split raw-password " ")
        min-value (Integer/parseInt (first (.split (first parts) "-")))
        max-value (Integer/parseInt (second (.split (first parts) "-")))
        password-letter (first (.split (nth parts 1) ""))
        password (nth parts 2)]
    {:min                   min-value
     :max                   max-value
     :letter                password-letter
     :password              password
     :password-letter-count (get-password-letter-count password)}))

(defn boolean-bit [result]
  (if result
    1
    0))

(defn bit-boolean [bit]
  (if (= bit 1)
    true
    false))

(defn letter-pos-check [letter password pos]
  (= letter (nth (.split password "") (- pos 1))))

(defn valid-password? [password]
  (let [letter-count (get (password :password-letter-count) (keyword (password :letter)))]
    (bit-boolean (bit-xor (boolean-bit (letter-pos-check (password :letter) (password :password) (password :min)))
                          (boolean-bit (letter-pos-check (password :letter) (password :password) (password :max)))))))


(defn validate-all-passwords [raw-passwords]
  (->> raw-passwords
       (map transform-password)
       (filter valid-password?)
       (count)))
