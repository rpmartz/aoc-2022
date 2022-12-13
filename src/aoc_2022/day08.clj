(ns aoc-2022.day08
  (:require
   [clojure.string :as str]))

(def input (str/split-lines (slurp "resources/day08.txt")))

(def grid (mapv #(mapv parse-long (str/split % #"")) input))

(defn trees-to-left [i j grid]
  (let [row (get grid i)]
    (subvec row 0 j)))

(defn trees-to-right [i j grid]
  (let [row (get grid i)]
    (subvec row (inc j) (count row))))

(defn transpose-column [j grid]
  (let [numrows (dec (count (first grid)))]
    (loop [i 0
           resultvec []]
      (if (< numrows i)
        resultvec
        (recur (inc i) (conj resultvec (get-in grid [i j])))))))

(defn trees-above [i j grid]
  (let [column (transpose-column j grid)]
    (subvec column 0 i)))

(defn trees-below [i j grid]
  (let [column (transpose-column j grid)]
    (subvec column (inc i) (count column))))

(defn scan-from-edge [i j grid f]
  (let [tree-height (get-in grid [i j])]
    (every? #(< % tree-height) (f i j grid))))

(defn from-left? [i j grid]
  (scan-from-edge i j grid trees-to-left))

(defn from-right? [i j grid]
   (scan-from-edge i j grid trees-to-right))

(defn from-above? [i j grid]
   (scan-from-edge i j grid trees-above))

(defn from-below? [i j grid]
  (scan-from-edge i j grid trees-below))

(defn visible? [i j grid]
  (cond
    (= 0 i) true ; first row
    (= (- 1 (count grid)) i) true ; last row
    (= 0 j) true ; first column
    (= (- 1 (count (first grid))) j) true ; last column
    (or (from-left? i j grid)
        (from-right? i j grid)
        (from-above? i j grid)
        (from-below? i j grid)) true
    :else false))

(def all-coords (let [numrows (count grid)
                      numcols (count (first grid))]
                  (for [x (range numrows)
                        y (range numcols)]
                    [x y])))

(defn part-1 []
  (count (filter #(visible? (first %) (second %) grid) all-coords)))

(do
  (println (str "Part 1: " (part-1))))


