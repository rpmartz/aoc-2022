(ns aoc-2022.day08-test
  (:require [clojure.test :refer :all]
            [aoc-2022.day08 :refer :all] 
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

(deftest test-transpose-column
  (testing "test transposing first column"
    (is (= [3 2 6 3 3] (transpose-column 0 testgrid)))))

(deftest test-scenic-score
  (testing "test scenic score from example"
    (is (= 4 (scenic-score 1 2 testgrid)))
    (is (= 8 (scenic-score 3 2 testgrid)))))

(deftest test-calculating-visbility
  (testing "test calculating visibility to right"
    (is (= 2 (visibility-in-direction 3 2 testgrid :right))))
  (testing "test calculating visibility to left"
    (is (= 2 (visibility-in-direction 3 2 testgrid :left))))

  (testing "test calculating visibility above"
    (is (= 2 (visibility-in-direction 3 2 testgrid :up))))

  (testing "test calculating visibility looking down"
    (is (= 1 (visibility-in-direction 3 2 testgrid :down)))))
