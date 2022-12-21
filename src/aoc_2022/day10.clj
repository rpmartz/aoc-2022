(ns aoc-2022.day10
  (:require clojure.set
            [clojure.string :as str]))

(def input (slurp "resources/day10.txt"))

(def instructions (str/split-lines input))

(defn parse-value [line]
  (parse-long (second (str/split line #" "))))

(defn run
  "Takes a sequence of textual instructions and returns a sequence
   of the register values at each clock cycle"
  [instructions]
  (loop [X 1
         [ins & instrs] instructions
         current-instruction-num-cycles 1
         register-values [X]]
    (cond
      (nil? ins) register-values
      (= "noop" ins) (recur X instrs 1 (conj register-values X))
      :else (cond ; this is an add instruction, so we either need to increment the instruction count or update the register
              (= 2 current-instruction-num-cycles) (recur (+ X (parse-value ins)) instrs 1 (conj register-values (+ X (parse-value ins)))) ; need to add current instruction's value to 
              :else (recur X (conj instrs ins) (inc current-instruction-num-cycles) (conj register-values X))))))


(def sum-indicies
  (map dec [20 60 100 140 180 220]))

(def values-at-cycles (run instructions))

(defn process-row [row]
  (loop [[sprite-loc & sprite-locs] row
         n 0
         res []]
    (if (nil? sprite-loc)
      res
      (recur sprite-locs (inc n) (conj res
                                       (if (<= (abs (- sprite-loc n)) 1)
                                         "#"
                                         "."))))))

(defn part-1 []
  (reduce + (map #(* (inc %) (nth values-at-cycles %)) sum-indicies)))

(defn part-2 []
  (loop [[row & rows] (partition 40 values-at-cycles)
         res []]
    (if (nil? row)
      res
      ; sprite-loc is midpoint of sprite 
      ; if sprite-loc is within 1 of n add # to result
      ; else add .  
      (recur rows (conj res (process-row row))))))

(partition 40 (part-2))

(map #(str/join "" %) (part-2))

(do
  (println (str "Part 1: " (part-1))))

