(ns aoc-2022.day09
  (:require [aoc-2022.common :as aoc]
            [clojure.string :as str]))

(def input (aoc/lines "resources/day09.txt"))

(defn parse-move [move]
  (let [[dir steps] (str/split move #" ")]
    {:dir (keyword dir) :steps (aoc/str->int steps)}))

; map to track position of head and tail, as well as visited nodes
(def initial-state {:head {:x 0 :y 0}
                    :tail {:x 0 :y 0}
                    :head-visited #{}
                    :tail-visited #{}})

(defn move-right [state]
  (let [{head :head
         tail :tail
         hvisited :head-visited
         tvisited :tail-visited} state 
        ]
    {:head {:x (inc (get head :x)) :y (get head :y)}
     :tail head
     :head-visited (conj hvisited {:x (inc (get head :x)) :y (get head :y)})
     :tail-visited (conj tvisited head)}))


(defn perform-move
  "Updates `state` based on the result of `move`
   
   move is a dictionary with a :dir and :steps
   state matches the structure of `initial-state
   "
  [{dir :dir n :steps} move
   state])

(defn part-1 []
  (let [moves (map parse-move input)]))


(conj #{"a" "b" "c"} "d")

(do
  (println (part-1)))