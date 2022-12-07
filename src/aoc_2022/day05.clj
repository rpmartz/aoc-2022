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

(print-letters initial-stack-config)

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

(defn update-stack [stack-num item]
  (let [s (get initial-stack-config stack-num)]
    (.push s item)
    (assoc initial-stack-config stack-num s)))

(defn process-instruction [instruction]
  (let [num-to-move (get instruction :num)
        src-stack-num (get instruction :src)
        src-stack (get initial-stack-config src-stack-num)
        dst-stack-num (get instruction :dst)
        dst-stack (get instruction dst-stack-num)]

    (let [n num-to-move
          items (take n src-stack)]
      (loop [crates]))

    (assoc initial-stack-config src-stack-num (drop num-to-move src-stack))))



(defn update-positions [crates instruction]
  (let [n (get instruction :num)]))

(let [final-stacks (do
                     (loop [instruction (first (map parse-instruction raw-instructions))
                            instructions (rest (map parse-instruction raw-instructions))
                            crates initial-stack-config]
                       (if (empty? instructions)
                         initial-stack-config
                         (recur
                          (first instructions)
                          (rest instructions) (let [ss crates
                                                    num-to-move (get instruction :num)
                                                    src-stack-num (get instruction :src)
                                                    src-stack (get initial-stack-config src-stack-num)
                                                    dst-stack-num (get instruction :dst)
                                                    dst-stack (get instruction dst-stack-num)]
                                                
                                                (assoc ss src-stack-num (drop num-to-move src-stack))
                                                ss)))))]
                                                
  (do (print-letters final-stacks)))