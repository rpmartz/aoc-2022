(ns aoc-2022.day09-test
  (:require [clojure.test :refer :all]
            [aoc-2022.day09 :refer :all]
            [aoc-2022.common :as aoc]))


(deftest test-parsing-move
  (testing "Testing Parsing Lines to Moves"
    (is (parse-move "R 4") {:dir :R :steps 4})))

(deftest test-moving-right
  (testing "Test moving right"
    (is (= {:head {:x 3 :y 3}
            :tail {:x 2 :y 3} 
            :tail-visited #{{:x 0 :y 3}
                            {:x 1 :y 3}
                            {:x 2 :y 3}}}
           (move-right {:head {:x 2 :y 3}
                        :tail {:x 1 :y 3} 
                        :tail-visited #{{:x 0 :y 3}
                                        {:x 1 :y 3}}})))))

(deftest test-moving-left
  (testing "Test moving left"
    (is (= {:head {:x 3 :y 3}
            :tail {:x 4 :y 3} 
            :tail-visited #{{:x 5 :y 3} {:x 4 :y 3}}}
           (move-left {:head {:x 4 :y 3}
                       :tail {:x 5 :y 3} 
                       :tail-visited #{{:x 5 :y 3}}})))))

(deftest test-moving-up
  (testing "Move up - tail move NOT required for h and t to remain adjacent"
    (is (= {:head {:x 3 :y 4}
            :tail {:x 4 :y 3} 
            :tail-visited #{{:x 4 :y 3}}} (move-up {:head {:x 3 :y 3}
                                                    :tail {:x 4 :y 3} 
                                                    :tail-visited #{{:x 4 :y 3}}}))))
  (testing "Move up - tail move required for h and t to remain adjacent"
    (is (= {:head {:x 4 :y 5}
            :tail {:x 4 :y 4}
            :tail-visited #{{:x 3 :y 3} {:x 4 :y 4}}} (move-up {:head {:x 4 :y 4}
                                                                :tail {:x 3 :y 3} 
                                                                :tail-visited #{{:x 3 :y 3}}})))))

(deftest test-adjacency
  (testing "Test whether two points are adjacent"
    (is (true? (not (adjacent? {:x 3 :y 3} {:x 4 :y 5}))))
    (is (true? (not (adjacent? {:x 3 :y 3} {:x 4 :y 1}))))
    (is (true? (adjacent? {:x 3 :y 3} {:x 2 :y 4})))
    (is (true? (adjacent? {:x 3 :y 3} {:x 3 :y 4})))
    (is (true? (adjacent? {:x 3 :y 3} {:x 4 :y 4})))
    (is (true? (adjacent? {:x 3 :y 3} {:x 4 :y 3})))
    (is (true? (adjacent? {:x 3 :y 3} {:x 4 :y 2})))
    (is (true? (adjacent? {:x 3 :y 3} {:x 3 :y 2})))
    (is (true? (adjacent? {:x 3 :y 3} {:x 2 :y 2})))
    (is (true? (adjacent? {:x 3 :y 3} {:x 2 :y 3})))))

(def sample-moves
  [{:dir :R :steps 4}
   {:dir :U :steps 4}
   {:dir :L :steps 3}
   {:dir :D :steps 1}
   {:dir :R :steps 4}
   {:dir :D :steps 1}
   {:dir :L :steps 5}
   {:dir :R :steps 2}])

(deftest test-example
  (testing "Testing problem example"
    (is (= 13 (simulate sample-moves initial-state)))))

(deftest test-part-1
  (testing "Testing part 1"
    (is (= 6503 (part-1)))))
