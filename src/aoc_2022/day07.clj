(ns aoc-2022.day07
  (:require [aoc-2022.common :as aoc]
            [clojure.string :as s]))

(defrecord Directory [name files subdirectores])
(defrecord File [name size])

(def lines (s/split-lines (slurp "resources/day07.txt")))

(defn is-cmd? [s] (s/starts-with? s "$"))

(defn is-dir? [s] (s/starts-with? s "dir"))

(defn handle-file
  "Takes a line of output representing a file and adds it to the 
   :files field of the current-dir argument.
   "
  [line current-dir]

  (let [components (s/split line #" ")
        size (Integer/parseInt (first components))
        filename (second components)]
    ; return updated directory object with new file info added
    (Directory. (:name current-dir) (conj (:files current-dir) (File. filename size)) (:subdirectores current-dir))))

(defn handle-dir
  "Takes a line of output representing a directory and adds it to the 
   :subdirectories field of the current-dir argument."
  [line current-dir]

  (let [name (first (s/split line #" "))]
    (Directory. (:name current-dir)
                (:files current-dir)
                (conj (:subdirectores current-dir) (Directory. name [] [])))))

(defn find-subdrectory [cwd subdir-name]
  (loop [x (first (:subdirectores cwd))
         xs (rest (:subdirectores cwd))]
    (cond (empty? xs) (throw (IllegalStateException. (str "Could not find " subdir-name " in " (:name cwd))))
          (= (:name x) subdir-name) x
          :else (recur (first xs) (rest xs)))))

(defn handle-cmd
  "Takes a line of output representing a command and updates the stack and curret
   directory"
  [line stack cwd]
  (if (= line "$ ls") {:stack stack :cwd cwd} ; cmd is `ls` and does not change position in directory tree
      (let [target-directory-name (last (s/split line #" "))]
        (if (= ".." target-directory-name)
          {:stack (drop 1 stack) :cwd (pop stack)}
          (if (nil? cwd)
            {:stack stack :cwd (Directory. target-directory-name [] [])} ;handle initial directory
            (let [subdir (find-subdrectory cwd target-directory-name)]
            ; push cwd onto stack and make cwd the subdir being switched to
              {:stack (conj stack cwd) :cwd subdir}))))))

(defn process-line
  "Updates the state of the stack and the current directory with the results of the current command.
   Returns map"
  [line stack cwd]
  (cond
    (is-dir? line) {:stack stack :cwd (handle-dir line cwd)} ; add a directory to subdirectory listing of cwd
    (is-cmd? line) (handle-cmd line stack cwd) ; navigate file tree
    ;; else line is listing a file
    :else {:stack stack :cwd (handle-file line cwd)})
  {:stack stack :cwd cwd})

(defn parse-output [command-list]
  (loop [cmd (first command-list)
         cmds (rest command-list)
         stack ()
         current-dir nil]
    (if (empty? cmds)
      stack
      (let [res (process-line cmd stack current-dir)]
        (recur (first cmds) (rest cmds) (get res :stack) (get res :cwd))))))


(parse-output lines)

(map (fn [line]
       (cond (is-cmd? line) (println (str line " is a command"))
             (is-dir? line) (println (str line "is a directory"))
             :else (println (str line " is a file")))) lines)

(handle-file "5334 woo.tar" (Directory. "testdir" [] []))

(conj (list "a" "b" "c" "d") "e")

(pop ["a" "b"])
