(ns adventofcode.passport.validator)

(def required-fields [:ecl
                      :pid
                      :eyr
                      :hcl
                      :byr
                      :iyr
                      :hgt])

(def valid-measurements #{:cm :in})

(def valid-eye-colors #{:amb :blu :brn :gry :grn :hzl :oth})

(defn- missing-fields [passport]
  (filter #(nil? (% passport)) required-fields))

(defn- valid-birth-year? [passport]
  (and (= 4 (count (:byr passport)))
       (>= (Integer/parseInt (:byr passport)) 1920)
       (<= (Integer/parseInt (:byr passport)) 2002)))

(defn- valid-year-of-issue?
  [passport]
  (and (= 4 (count (:iyr passport)))
       (>= (Integer/parseInt (:iyr passport)) 2010)
       (<= (Integer/parseInt (:iyr passport)) 2020)))

(defn- valid-year-of-expiration? [passport]
  (and (= 4 (count (:eyr passport)))
       (>= (Integer/parseInt (:eyr passport)) 2020)
       (<= (Integer/parseInt (:eyr passport)) 2030)))

(defn- validate-height-in-cm [height]
  (and (>= height 150) (<= height 193)))

(defn- validate-height-in-inches [height]
  (and (>= height 59) (<= height 76)))

(defn- valid-height? [passport]
  (let [height (.substring (:hgt passport) 0 (- (count (:hgt passport)) 2))
        measurement (.substring (:hgt passport) (- (count (:hgt passport)) 2) (count (:hgt passport)))]
    (and (contains? valid-measurements (keyword measurement))
         (if (= measurement "cm")
           (validate-height-in-cm (Integer/parseInt height))
           (validate-height-in-inches (Integer/parseInt height))))))

(defn- between-zero-and-nine [number]
  (and (>= (Character/getNumericValue number) 0)
       (<= (Character/getNumericValue number) 9)))

(defn- between-a-and-f [char]
  (and (>= (int char) (int \a))
       (<= (int char) (int \f))))

(defn- valid-hair-color? [passport]
  (and (= (first (passport :hcl)) \#)
       (= 6 (count (rest (passport :hcl))))
       (every? identity (map (fn [value]
                                (or (between-a-and-f value)
                                    (between-zero-and-nine value))) (rest (passport :hcl))))))

(defn- valid-eye-color? [passport]
  (contains? valid-eye-colors (keyword (:ecl passport))))


(defn- valid-passport-number? [passport]
  (and (= 9 (count (passport :pid)))
       (every? identity (map (fn [number]
                               (between-zero-and-nine number)) (char-array (passport :pid))))))

(defn- has-required-fields? [passport]
  (= 0 (count (missing-fields passport))))

(defn valid-passport? [passport]
  (and (has-required-fields? passport)
       (valid-birth-year? passport)
       (valid-year-of-issue? passport)
       (valid-year-of-expiration? passport)
       (valid-height? passport)
       (valid-hair-color? passport)
       (valid-eye-color? passport)
       (valid-passport-number? passport)))


