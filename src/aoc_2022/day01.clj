(ns aoc-2022.day01
  (:require [aoc-2022.common :as common]
            [clojure.string :as string]))

(def puzzle-input
  (let [raw-input (slurp "resources/day01.txt")]
    (string/split raw-input #"\n")))

(defn calculate-calorie-values []
  (loop [x (first puzzle-input)
         xs puzzle-input
         intermediate-vals []
         final-vals []]
    (if (empty? xs)
      final-vals
      (if (= x "")
        (let [new-val (reduce + intermediate-vals)]
          (recur (first xs) (rest xs) [] (conj final-vals new-val)))
        (recur (first xs) (rest xs) (conj intermediate-vals (common/str->int x)) final-vals)))))

(defn part-1 []
  (let [xs (calculate-calorie-values)]
    (apply max xs)))


(defn part-2 []
  (let [xs (calculate-calorie-values)]
    (reduce + (take 3 (reverse (sort xs))))))

(do
  (println (part-1))
  (println (part-2)))







