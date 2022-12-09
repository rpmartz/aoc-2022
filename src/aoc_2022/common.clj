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