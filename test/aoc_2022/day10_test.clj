(ns aoc-2022.day10-test
  (:require [clojure.test :refer :all]
            [aoc-2022.day10 :refer :all]))

(deftest test-parsing-add
  (testing "test parsing add instruction"
    (is (= -15 (parse-value "addx -15")))))