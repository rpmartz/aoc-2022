(ns aoc-2022.day09-test
  (:require [clojure.test :refer :all]
            [aoc-2022.day09 :refer :all]))


(deftest test-parsing-move
  (testing "Testing Parsing Lines to Moves"
    (is (parse-move "R 4") {:dir :R :steps 4})))

(deftest test-moving-right
  (testing "Test moving right"
    (is (= {:head {:x 3 :y 3}
            :tail {:x 2 :y 3}
            :head-visited #{{:x 0 :y 3}
                            {:x 1 :y 3}
                            {:x 2 :y 3}
                            {:x 3 :y 3}}
            :tail-visited #{{:x 0 :y 3}
                            {:x 1 :y 3}
                            {:x 2 :y 3}}}
           (move-right {:head {:x 2 :y 3}
                        :tail {:x 1 :y 3}
                        :head-visited #{{:x 0 :y 3}
                                        {:x 1 :y 3}
                                        {:x 2 :y 3}}
                        :tail-visited #{{:x 0 :y 3}
                                        {:x 1 :y 3}}})))))