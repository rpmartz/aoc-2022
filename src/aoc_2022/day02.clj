(ns aoc-2022.day02
  (:require [clojure.string :as string]
            [clojure.core.match :refer [match]]))

(def letter-map {"A" :rock
                 "B" :paper
                 "C" :scissors
                 "X" :rock
                 "Y" :paper
                 "Z" :scissors})

(def choice-score {:rock 1
                   :paper 2
                   :scissors 3})

(def result-score {:win 6
                   :draw 3
                   :loss 0})

(def letters-to-outcomes {"X" :loss
                          "Y" :draw
                          "Z" :win})

(defn determine-choice-for-outcome [op-choice outcome]
  (cond
    (= :rock op-choice) (cond (= :win outcome) :paper
                              (= :loss outcome) :scissors
                              (= :draw outcome) :rock)
    (= :paper op-choice) (cond (= :win outcome) :scissors
                               (= :loss outcome) :rock
                               (= :draw outcome) :paper)
    (= :scissors op-choice) (cond (= :win outcome) :rock
                                  (= :loss outcome) :paper
                                  (= :draw outcome) :scissors)))

(defn judge-result [mine theirs]
  (match [mine theirs]
    [:rock :rock] :draw
    [:rock :paper] :loss
    [:rock :scissors] :win
    [:paper :rock] :win
    [:paper :paper] :draw
    [:paper :scissors] :loss
    [:scissors :rock] :loss
    [:scissors :paper] :win
    [:scissors :scissors] :draw))

(defn score-round [choices]
  (let [my-choice (get letter-map (second (string/split choices #"\s")))
        opp-choice (get letter-map (first (string/split choices #"\s")))
        result (judge-result my-choice opp-choice)]
    (+
     (get choice-score my-choice)
     (get result-score result))))

(defn score-round-desired-result [input]
  (let [opp-choice (get letter-map (first (string/split input #"\s")))
        desired-outcome (get letters-to-outcomes (second (string/split input #"\s")))]
    (+ (get result-score desired-outcome) (get choice-score (determine-choice-for-outcome opp-choice desired-outcome)))))

(defn part-1 []
  (let [rounds (string/split (slurp "resources/day02.txt") #"\n")]
    (reduce + (map score-round rounds))))

(defn part-2 []
  (let [rounds (string/split (slurp "resources/day02.txt") #"\n")]
    (reduce + (map score-round-desired-result rounds))))


(do
  (part-1) ; 8890
  (part-2)) ; 10238