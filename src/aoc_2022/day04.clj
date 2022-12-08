(ns aoc-2022.day04
  (:require
   [clojure.string :as string]))

(def lines (string/split-lines (slurp "resources/day04.txt")))

(defn parse-interval [s]
  (let [numbers (string/split s #"-")
        start (Integer/parseInt (first numbers))
        end (Integer/parseInt (second numbers))]
    (list start end)))

(defn parse-intervals [pair]
  (let [left-interval (parse-interval (first pair))
        right-interval (parse-interval (second pair))]
    (concat left-interval right-interval)))

(defn overlaps-completely? [xs]
  (let [lb (first xs)
        le (second xs)
        rb (first (drop 2 xs))
        re (second (drop 2 xs))]
    (cond
      (and (<= lb rb) (>= le re)) true
      (and (<= rb lb) (>= re le)) true
      :else false)))

(defn overlaps-at-all? [xs]
  (let [lb (first xs)
        le (second xs)
        rb (first (drop 2 xs))
        re (second (drop 2 xs))]
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