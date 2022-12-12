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

(s/join "/" (rest ["/" "zft" "zrtm" "vhdhn" "djfww" "zft"]))

(pathify ["/"])
(drop-last ["/"])

(reduce + (filter #(<= % 100000) directory-sizes))