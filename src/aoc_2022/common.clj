(ns aoc-2022.common
  (:require [clojure.string :as str]))

(defn str->int [s]
  (. Integer parseInt s))

(defn lines
  "Reads all of the lines in the file located at `path`"
  [path]
  (str/split-lines (slurp path)))

(defn pnt
  "Returns a map representing a 2d coordinate"
  [x y]
  {:x x :y y})

(defn ints [s]
  (re-seq #"-?[0-9]+" s))

(defn two-dim-grid [numrows numcols]
  (for [x (range numrows)
        y (range numcols)]
    [x y]))

(defn manhattan-distance
  "Calculates the Manhattan distance between two points.
   Expects points in [x y] format"
  [p1 p2]
  (let [x1 (first p1) 
        y1 (second p1)
        x2 (first p2) 
        y2 (second p2)]
    (+ (abs (- x1 x2)) (abs (- y1 y2)))))
