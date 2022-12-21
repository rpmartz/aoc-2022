(ns aoc-2022.day11
  (:require [clojure.string :as str]))

(def initial-state {0 {:items [98 89 52]
                       :op (fn [x] (* x 2))
                       :test (fn [x] (= 0 (mod x 5)))
                       :tdst 6
                       :fdst 1}

                    1 {:items [57 95 80 92 57 78]
                       :op (fn [x] (* x 13))
                       :test (fn [x] (= 0 (mod x 2)))
                       :tdst 2
                       :fdst 6}

                    2 {:items [82 74 97 75 51 92 83]
                       :op (fn [x] (+ x 5))
                       :test (fn [x] (= 0 (mod x 19)))
                       :tdst 7
                       :fdst 5}

                    3 {:items [97 88 51 68 76]
                       :op (fn [x] (+ x 6))
                       :test (fn [x] (= 0 (mod x 7)))
                       :tdst 0
                       :fdst 4}

                    4 {:items [63]
                       :op (fn [x] (+ x 1))
                       :test (fn [x] (= 0 (mod x 17)))
                       :tdst 0
                       :fdst 1}

                    5 {:items [94 91 51 63]
                       :op (fn [x] (+ x 4))
                       :test (fn [x] (= 0 (mod x 13)))
                       :tdst 4
                       :fdst 3}

                    6 {:items [61 54 94 71 74 68 98 83]
                       :op (fn [x] (+ x 2))
                       :test (fn [x] (= 0 (mod x 3)))
                       :tdst 2
                       :fdst 7}

                    7 {:items [90 56]
                       :op (fn [x] (* x x))
                       :test (fn [x] (= 0 (mod x 11)))
                       :tdst 3
                       :fdst 5}})

(defn turn [monkey]
  (let [items (get monkey :items)
        op (get monkey :op)
        test (get monkey :test)
        truthy-dest (get monkey :tdst)
        falsey-dest (get monkey :fdst)]
    (loop [[x & xs] items
           truthy-items []
           falsey-items []]
      (if (nil? x)
        {:monkey (assoc monkey :items [])
         :res [{:dest truthy-dest :items truthy-items}
               {:dest falsey-dest :items falsey-items}]}
        (let [value-to-test (quot (op x) 3)]
           (if (true? (test value-to-test))
             (recur xs (conj truthy-items value-to-test) falsey-items)
             (recur xs truthy-items (conj falsey-items value-to-test)))
          )
       ))))

(defn build-test [n divisor]
  (= 0 (mod n divisor)))

(str/split (slurp "resources/day11.txt") #"\n\n")

(mod 10 6)

(mod 98 5)

(quot 1501 3)