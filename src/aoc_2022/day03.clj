(ns aoc-2022.day03
  (:require clojure.set
            [clojure.string :as string]))

(def letters
  "0abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ")

(defn score-letter [l]
  (.indexOf letters l))

(defn split-in-half [line]
  (let [n (count line)
        midpoint (/ n 2)
        first-half (subs line 0 midpoint)
        second-half (subs line midpoint n)]
    (list first-half second-half)))

(defn get-letter-set [s]
  (set (map str (char-array s))))

(def lines (string/split (slurp "resources/day03.txt") #"\n"))

(defn score-line [line]
  (let [halves (split-in-half line)
        s1 (get-letter-set (first halves))
        s2 (get-letter-set (second halves))
        common-letter (first (clojure.set/intersection s1 s2))]
    (score-letter common-letter)))

(defn score-group [group]
  (let [e1 (get-letter-set (first group))
        e2 (get-letter-set (second group))
        e3 (get-letter-set (last group))
        common-letter (first (clojure.set/intersection e1 e2 e3))]
    (score-letter common-letter)))


(defn part-1 []
  (reduce + (map score-line lines)))

(defn part-2 []
  (loop [xs lines
         acc 0]
    (if (empty? xs)
      acc
      (recur
       (drop 3 xs)
       (+ acc (score-group (take 3 xs)))))))

(do
  (println (part-1))
  (println (part-2)))