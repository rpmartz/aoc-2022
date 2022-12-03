(ns aoc-2022.day03
  (:require clojure.set
            [clojure.string :as string]))

; todo how to construct this?
(def letters-to-priorities
  {"a" 1
   "b" 2
   "c" 3
   "d" 4
   "e" 5
   "f" 6
   "g" 7
   "h" 8
   "i" 9
   "j" 10
   "k" 11
   "l" 12
   "m" 13
   "n" 14
   "o" 15
   "p" 16
   "q" 17
   "r" 18
   "s" 19
   "t" 20
   "u" 21
   "v" 22
   "w" 23
   "x" 24
   "y" 25
   "z" 26
   ;; could simplify this to lower and add 2
   "A" 27
   "B" 28
   "C" 29
   "D" 30
   "E" 31
   "F" 32
   "G" 33
   "H" 34
   "I" 35
   "J" 36
   "K" 37
   "L" 38
   "M" 39
   "N" 40
   "O" 41
   "P" 42
   "Q" 43
   "R" 44
   "S" 45
   "T" 46
   "U" 47
   "V" 48
   "W" 49
   "X" 50
   "Y" 51
   "Z" 52})

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
    (get letters-to-priorities common-letter)))

(defn score-group [group]
  (let [e1 (get-letter-set (first group))
        e2 (get-letter-set (second group))
        e3 (get-letter-set (last group))
        common-letter (first (clojure.set/intersection e1 e2 e3))]
    (get letters-to-priorities common-letter)))


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
  (part-1)
  (part-2))


