(ns aoc-2022.day04
  (:require
   [clojure.string :as string]))

(def lines (string/split-lines (slurp "resources/day04.txt")))

(defn parse-interval [s]
  (let [[start end] (string/split s #"-")]
    (list (Integer/parseInt start) (Integer/parseInt end))))

(defn parse-intervals [pair]
  (let [[left-interval right-interval] pair]
    (concat (parse-interval left-interval) (parse-interval right-interval))))

(defn overlaps-completely? [xs]
  (let [[lb le rb re] xs]
    (cond
      (and (<= lb rb) (>= le re)) true
      (and (<= rb lb) (>= re le)) true
      :else false)))

(defn overlaps-at-all? [xs]
  (let [[lb le rb re] xs]
    (cond
      (and (<= lb rb) (>= le rb)) true
      (and (<= rb lb) (>= re lb)) true
      :else false)))

(def interval-list
  (map parse-intervals (map #(string/split % #",") lines)))

(defn part-1 []
  (reduce + (map #(if (overlaps-completely? %) 1 0) interval-list)))

(defn part-2 []
  (reduce + (map #(if (overlaps-at-all? %) 1 0) interval-list)))

(do
  (println (part-1))
  (println (part-2)))