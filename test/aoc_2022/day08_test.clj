(ns aoc-2022.day08-test
  (:require [clojure.test :refer :all]
            [aoc-2022.day08 :refer :all]
            [aoc-2022.common :as aoc]
            [clojure.string :as str]))

(def testinput (str/split-lines (slurp "resources/examples/day08.txt")))

(def testgrid (mapv #(mapv parse-long (str/split % #"")) testinput))

(deftest test-getting-trees
  (testing "Test getting trees to left of a point"
    (is (= [6 5] (trees-to-left 2 2 testgrid)))
    (is (= [] (trees-to-left 2 0 testgrid))))
  (testing "Test getting trees to right"
    (is (= [3 2] (trees-to-right 2 2 testgrid)))
    (is (= [] (trees-to-right 2 4 testgrid))))
  (testing "Test getting trees above"
    (is (= [3 5] (trees-above 2 2 testgrid)))
    (is (= [] (trees-above 0 2 testgrid))))
  (testing "Test getting trees below"
    (is (= [5 3] (trees-below 2 2 testgrid)))
    (is (= [] (trees-below 4 2 testgrid)))))

