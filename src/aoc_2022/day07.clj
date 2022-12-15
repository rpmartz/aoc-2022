(ns aoc-2022.day07
  (:require
   [clojure.string :as s]
   [clojure.core.match :refer [match]]))

(def lines (s/split-lines (slurp "resources/day07.txt")))

(defn pathify [dirs]
  (str "/" (s/join "/" (rest dirs))))

(defn update-sizemap-with-file [fsize sizemap dirstack]
  (loop [path dirstack
         hm sizemap]
    (if (empty? path)
      hm
      (recur (drop-last path) (merge-with + hm {(pathify path) fsize})))))

(def directory-sizes
  (reduce (fn [[sizemap dirstack] line]
            (match [(s/split line #" ")]
              [["$" "cd" "/"]] [sizemap ["/"]] ; add root to stack
              [["$" "cd" ".."]] [sizemap (pop dirstack)] ; pop dirr off stack
              [["$" "cd" dirname]] [sizemap (conj dirstack dirname)] ; add current dir to stack
              [(:or ["dir" _] ["$" "ls"])] [sizemap dirstack] ; ignore 
              [[filesize _]] [(update-sizemap-with-file (parse-long filesize) sizemap dirstack) dirstack])) [{} []] lines))

(defn part-1
  "Finds the sum of all the values of directories under 100000"
  []
  (reduce + (filter #(<= % 100000) (vals (first directory-sizes)))))

(defn part-2
  "Finds the size of the smallest directory whose deletion would ensure enough space for the update"
  []
  (let [space-used (get (first directory-sizes) "/")
        space-needed (- 30000000 (- 70000000 space-used))]
    (apply min (filter #(<= space-needed %) (vals (first directory-sizes))))))

(let [part-1-answer (part-1)
      part-2-answer (part-2)]
  (println (str "Part 1: " part-1-answer))
  (println (str "Part 2: " part-2-answer)))



