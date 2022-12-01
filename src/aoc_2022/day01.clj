(ns aoc-2022.day01
  (:require [aoc-2022.common :as common]
            [clojure.string :as string]))

(def puzzle-input
  (let [raw-input (slurp "resources/day01.txt")]
    (string/split raw-input #"\n")))

(defn part-1 []
  (loop [xs puzzle-input
         x (first puzzle-input)
         max-so-far 0
         current-holdings []]
    (if (empty? xs)
      max-so-far
      (if (= x "")
        (let [current-elements-sum (reduce + (map common/str->int current-holdings))]
          (if (> current-elements-sum max-so-far)
            (recur (rest xs) (first xs) current-elements-sum [])
            (recur (rest xs) (first xs) max-so-far [])))
        (recur (rest xs) (first xs) max-so-far (conj current-holdings x))))))









