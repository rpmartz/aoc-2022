(ns aoc-2022.day09
  (:require [aoc-2022.common :as aoc]
            [clojure.string :as str]))

(def input (aoc/lines "resources/day09.txt"))

(defn parse-move [move]
  (let [[dir steps] (str/split move #" ")]
    {:dir (keyword dir) :steps (aoc/str->int steps)}))



(map parse-move input)