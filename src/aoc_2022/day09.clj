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
                    :head-visited #{{:x 0 :y 0}}
                    :tail-visited #{{:x 0 :y 0}}})
(defn move-laterally 
  "Updates `state` by applying the update function `f` to the `x` coordinate of the current position of head"
  [state f]
  (let [{head :head
         hvisited :head-visited
         tvisited :tail-visited} state
        updated-head-coord {:x (f (get head :x)) :y (get head :y)}]
    {:head updated-head-coord
     :tail head
     :head-visited (conj hvisited updated-head-coord)
     :tail-visited (conj tvisited head)})
  )

(defn move-right [state]
  (move-laterally state inc))

(defn move-left [state]
  (move-laterally state dec))

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