(ns aoc-2022.day01
  (:require [aoc-2022.common :as common]
            [clojure.string :as string]))

(def per-elf-str-values
  ;; puzzle input split by double newline, which separates each 
  ;; elf's food values into a string with numeric values separated by \n
  (string/split (slurp "resources/day01.txt") #"\n\n"))

(def per-elf-line-values
  ;; puzzle input structured with each elf's calorie values
  ;; represented as a collection of numeric strings
  (map #(string/split % #"\n") per-elf-str-values))

(def elf-calorie-values
  ;; total value that each elf is carrying
  (loop [line (first per-elf-line-values)
         lines (rest per-elf-line-values)
         acc []]
    (if (empty? lines)
      acc
      (let [current-line-value (reduce + (map common/str->int line))]
        (recur (first lines) (rest lines) (conj acc current-line-value))))))

(defn part-1 []
  (apply max elf-calorie-values))

(defn part-2 []
  (reduce + (take 3 (reverse (sort elf-calorie-values)))))


(do
  (println (part-1))
  (println (part-2)))







