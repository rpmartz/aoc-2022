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
                    :tail-visited #{{:x 0 :y 0}}})

(defn adjacent? [p1 p2]
  (and (>= 1 (abs (- (:x p1) (:x p2)))) (>= 1 (abs (- (:y p1) (:y p2))))))

(defn move-laterally
  "Updates `state` by applying the update function `f` to the `x` coordinate of the current position of head"
  [state f]
  (let [{head :head
         tail :tail
         tvisited :tail-visited} state
        updated-head-coord {:x (f (get head :x)) :y (get head :y)}
        updated-tail-coord (if (adjacent? tail updated-head-coord) tail head)]
    {:head updated-head-coord
     :tail updated-tail-coord
     :tail-visited (conj tvisited updated-tail-coord)}))

(defn move-vertically
  "Updates `state` by applying the update function `f` to the `x` coordinate of the current position of head"
  [state f]
  (let [{head :head tail :tail tvisited :tail-visited} state
        updated-head-coord {:x (get head :x) :y (f (get head :y))}
        updated-tail-coord (if (adjacent? tail updated-head-coord) tail head)]
    {:head updated-head-coord
     :tail updated-tail-coord
     :tail-visited (conj tvisited updated-tail-coord)}))

(defn move-right [state]
  (move-laterally state inc))

(defn move-left [state]
  (move-laterally state dec))

(defn move-up [state]
  (move-vertically state inc))

(defn move-down [state]
  (move-vertically state dec))

(def move-functions {:R move-right
                     :L move-left
                     :U move-up
                     :D move-down})

(defn perform-move [move state]
  (let [dir (:dir move)
        n (:steps move)
        fn (get move-functions dir)]
    (loop [n n
           state state]
      (if (= 0 n) state
          (recur (dec n) (fn state))))))

(defn simulate [move-list state]
  (loop [[move & moves] move-list
         state state]
    (if (nil? move) (count (:tail-visited state))
        (recur moves (perform-move move state)))))

(defn part-1 []
  (simulate (map parse-move input) initial-state))


(println (part-1))