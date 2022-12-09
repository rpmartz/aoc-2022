(ns aoc-2022.day06)

(def input (slurp "resources/day06.txt"))

(defn part-1 [x]
  (loop [items (rest (partition x 1 input))
         window (first (partition x 1 input))
         acc x]

    (if (= x (count (set window)))
      acc
      (recur (rest items) (first items) (inc acc)))))

(do
  (println (part-1 4))
  (println (part-1 14)))
