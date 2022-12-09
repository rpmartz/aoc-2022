(ns aoc-2022.day09-test
  (:require [clojure.test :refer :all]
            [aoc-2022.day09 :refer :all]))


(deftest test-parsing-move
  (testing "Testing Parsing Lines to Moves"
    (is (parse-move "R 4") {:dir :R :steps 4})))