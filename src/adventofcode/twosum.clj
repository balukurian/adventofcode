(ns adventofcode.twosum)

(defn- cartesian-product-b [expense-a expense-b all-expenses]
  (map (fn [expense-c]
         {:sum (+ expense-b expense-a expense-c)
          :pair [expense-b expense-a expense-c]}) all-expenses))

(defn- cartesian-product-a [expense-a all-expenses]
  (map (fn [expense-b] (cartesian-product-b expense-a expense-b all-expenses)) all-expenses))

(defn two-sum [all-expenses]
  (let [cartesian-map (flatten (map (fn [expense-a]
                                      (cartesian-product-a expense-a all-expenses)) all-expenses))]
    (first (filter #(= 2020 (:sum %)) cartesian-map))))

(defn expense-product [all-expenses]
  (reduce * (:pair (two-sum all-expenses))))
