(ns aoc-2022.day07-test
  (:require [clojure.test :refer :all]
            [aoc-2022.day07 :refer :all]))


(deftest is-dir-test
  (testing "Testing Directories"
    (is (not (is-dir? "")))
    (is (is-dir? "dir pbmcnpzfis"))
    ))
