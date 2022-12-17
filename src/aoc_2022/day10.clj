(ns aoc-2022.day10
  (:require clojure.set
            [clojure.string :as str]))

(def initial-state
  {0 {:items [98 89 52]
      :op (fn [x] (* x 2))
      :test (fn [x] (= 0 (mod x 5)))
      :truthy-dest 6
      :falsey-dest 1
      :inspections 0}
   1 {:items [57 95 80 92 57 78]
      :op (fn [x] (* x 13))
      :test (fn [x] (= 0 (mod x 2)))
      :truthy-dest 2
      :falsey-dest 6
      :inspections 0}
   2  {:items [82 74 97 75 51 92 83]
       :op (fn [x] (+ x 5))
       :test (fn [x] (= 0 (mod x 19)))
       :truthy-dest 7
       :falsey-dest 5
       :inspections 0}
   3 {:items [97, 88, 51, 68, 76]
      :op (fn [x] (+ x 6))
      :test (fn [x] (= 0 (mod x 7)))
      :truthy-dest 0
      :falsey-dest 4
      :inspections 0}
   4 {:items [63]
      :op (fn [x] (+ x 1))
      :test (fn [x] (= 0 (mod x 17)))
      :truthy-dest 0
      :falsey-dest 1
      :inspections 0}
   5 {:items [94 91 51 63]
      :op (fn [x] (+ x 4))
      :test (fn [x] (= 0 (mod x 13)))
      :truthy-dest 0
      :falsey-dest 4
      :inspections 3}
   6 {:items [61 54 94 71 74 68 98 83]
      :op (fn [x] (+ x 2))
      :test (fn [x] (= 0 (mod x 3)))
      :truthy-dest 2
      :falsey-dest 7
      :inspections 0}
   7 {:items [90 56]
      :op (fn [x] (* x x))
      :test (fn [x] (= 0 (mod x 11)))
      :truthy-dest 3
      :falsey-dest 5
      :inspections 0}})