(ns aoc-2022.day10
  (:require clojure.set
            [clojure.string :as str]))

(def input (slurp "resources/day10.txt"))

(def instructions (str/split-lines input))

(defn parse-value [line]
  (parse-long (second (str/split line #" "))))

(parse-value "addx -15")
()