(ns aoc-2022.day05
  (:require clojure.set
            [clojure.string :as string]))

(defn print-letters
  "Prints the letter of the crate on the top of each stack"
  [stacks]
  (println (str
            (first (get stacks 1))
            (first (get stacks 2))
            (first (get stacks 3))
            (first (get stacks 4))
            (first (get stacks 5))
            (first (get stacks 6))
            (first (get stacks 7))
            (first (get stacks 8))
            (first (get stacks 9)))))

(defn drop-text [line]
  (let [move-dropped (string/replace line #"move " "")
        from-dropped (string/replace move-dropped #"from " "")
        to-dropped (string/replace from-dropped #"to " "")]
    to-dropped))

(def raw-instructions
  (string/split-lines (slurp "resources/day05.txt")))

(defn parse-instruction [ins]
  (let [parsed-instructions (string/split (drop-text ins) #" ")
        num-to-move (Integer/parseInt (first parsed-instructions))
        src (Integer/parseInt (second parsed-instructions))
        dst (Integer/parseInt (last parsed-instructions))]
    {:num num-to-move :src src :dst dst}))

(def initial-stack-config  {1 (list "N" "V" "C" "S")
                            2 (list "S" "N" "H" "J" "M" "Z")
                            3 (list "D" "N" "J" "G" "T" "C" "M")
                            4 (list "M" "R" "W" "J" "F" "D" "T")
                            5 (list "H" "F" "P")
                            6 (list "J" "H" "Z" "T" "C")
                            7 (list "Z" "L" "S" "F" "Q" "R" "P" "D")
                            8 (list "W" "P" "F" "D" "H" "L" "S" "C")
                            9 (list "Z" "G" "N" "F" "P" "M" "S" "D")})
