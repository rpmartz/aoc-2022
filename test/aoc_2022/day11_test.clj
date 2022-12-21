(ns aoc-2022.day11-test
  (:require [clojure.test :refer :all]
            [aoc-2022.day11 :refer :all]))

(deftest test-one-turn
  (testing "Test performing turn for one monkey")
  (let [monkey {:items [79 98]
                :op (fn [x] (* x 19))
                :test (fn [x] (= 0 (mod x 23)))
                :tdst 2
                :fdst 3}
        res (turn monkey)]
    (is (= (get-in res [:monkey :items]) []))
    (is (= (get-in res [:monkey :tdst]) 2))
    (is (= (get-in res [:monkey :fdst]) 3))))
