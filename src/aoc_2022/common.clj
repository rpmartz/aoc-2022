(ns aoc-2022.common)


(defn str->int [s]
  (. Integer parseInt s))

(defn ints [s]
  (re-seq #"-?[0-9]+" s)
  )
