(ns aoc-2022.day07
  (:require
   [clojure.string :as s]
   [clojure.core.match :refer [match]]))

(def lines (s/split-lines (slurp "resources/day07.txt")))

(reduce (fn [[sizemap dirstack] line]
          (match [(s/split line #" ")]
            [["$" "cd" "/"]] [sizemap ["/"]] ; add root to stack
            [["$" "cd" ".."]] [sizemap (pop dirstack)] ; pop dirr off stack
            [["$" "cd" dirname]] [sizemap (conj dirstack dirname)] ; add current dir to stack
            [(:or ["dir" _] ["$" "ls"])] [sizemap dirstack] ; ignore
            [[filesize fname]] [sizemap dirstack])) [{} []] lines)