(ns aoc-2022.day15
  (:require
   [aoc-2022.common :as aoc]
   [clojure.string :as str]))

(def grid (aoc/two-dim-grid 4000000 4000000))

(defn parse-points
  "Expects a collection of length 4"
  [coords]
  (let [[sx sy cx cy] (map parse-long coords)]
    [[sx sy] [cx cy]]))

(def input
  (->> (slurp "resources/day15.txt")
       (str/split-lines)
       (map aoc/ints)))

(defn build-initial-state [coordinate-pairs]
  (loop [[pair & pairs] coordinate-pairs
         state {}]
    (println (str "pair is " pair))
    (if (nil? pair)
      state
      (let [scanner (first pair)
            beacon (last pair)]
        (recur pairs (-> state
                         (assoc scanner "S")
                         (assoc beacon "B")))))))

(def pairs (map parse-points input))

(defn part-1 [state]
  (loop [[pair & pairs] pairs]
    (if (empty? pair)
      (println "Done")
      (let [scanner (first pair)
            beacon (second pair)
            d (aoc/manhattan-distance scanner beacon)]
        (println (str "md between " scanner " and " beacon " is " d))
        ; now we have the distance to the nearest beacon from the scanner
        ; and need to "color" everything within that distance as not possible
        (recur pairs)))))

(part-1 pairs)

(build-initial-state pairs)

(aoc/manhattan-distance [2662540 1992627] [1562171 2000000])
