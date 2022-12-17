(ns aoc-2022.day13-test
  (:require [clojure.test :refer :all]
            [aoc-2022.day13 :refer :all]))

(deftest test-comparison
  (testing "Testing comparison numbers"
    (is (= -1 (cmp 4 5)))
    (is (= 0 (cmp 4 4)))  ; not sure this is right
    (is (= 1 (cmp 5 4))))

  (testing "Test comparing two equal length lists"
    (is (= -1 (cmp [4] [5])))
    (is (= 0 (cmp [4] [4])))
    (is (= 1 (cmp [5] [4]))))

  (testing "Test comparing a list and an int"
    (is (= -1 (cmp 4 [5])))
    (is (= -1 (cmp [4] 5)))
    (is (= 0 (cmp [4] 4)))
    (is (= 0 (cmp 4 [4])))
    (is (= 1 (cmp 5 [4])))
    (is (= 1 (cmp [5] 4)))))