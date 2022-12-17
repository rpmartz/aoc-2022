(ns aoc-2022.day13
  (:require
   [clojure.string :as str]))


(defn cmp [l r]
  (cond
    (and (number? l) (number? r)) (compare l r)
    (and (sequential? l) (sequential? r)) (cmp (first l) (first r))
    ))


(compare 4 5)
(sequential? [4])