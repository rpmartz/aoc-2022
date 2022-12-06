(ns aoc-2022.day06)

(def input (slurp "resources/day06.txt"))

(defn part-1 []
  (loop [items (rest (partition 4 1 input))
         window (first (partition 4 1 input))
         acc 4]

    (if (or (> acc 100000) (= 4 (count (set window))))
      acc
      (recur (rest items) (first items) (inc acc)))))

(do
  (println (part-1)))