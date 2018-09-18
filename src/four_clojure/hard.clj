(ns four-clojure.hard)


;; 138 Squares Squared

(defn split-number
  [n]
  (loop [x (list (rem n 10)) y (quot n 10)]
    (if (< 0 y)
      (recur (cons (rem y 10) x) (quot y 10))
      x)))

(defn digit-square
  [a b]
  (loop [x [] i a]
    (if (< b i)
      x
      (recur (into x (split-number i)) (* i i)))))

(defn square-string
  [n]
  (vector (repeat n (apply str (repeat n \space)))))


(defn diamond-array
  "get a diamond string"
  [n]
  (let [t (- (* 2 n) 1)]
    (loop [x 0 s []]
      (if (< x t)
        (recur (inc x)
               (conj s
                     (loop [y 0 r []]
                       (if (< y t)
                         (recur (inc y)
                                (conj r
                                      (if (< x n)
                                        (if (and (>= y (- n 1 x)) (<= y (+ (- n 1) x)))
                                          (if (== 0 (mod (- y (- n 1 x)) 2)) \* \space)
                                          \space)
                                        (if (or (<= y (- x n)) (>= y (+ n (- (- t 1) x))))
                                          \space
                                          (if (== 0 (mod (- x n y) 2)) \space \*)))))
                         r))))
        s))))

(comment
  (println (diamond-string 5)))


(def binary-array
  [[0 0 0 0 0]
   [0 0 0 0 0]
   [0 0 0 0 0]
   [0 0 0 0 0]
   [0 0 0 0 0]])

(defn print-binary-array
  [t]
  (print (reduce (fn [s a] (str s (reduce #(str %1 %2) "" a) "\n")) "" t)))

(defn update-binary-array
  [t [x y] v]
  (assoc t x (assoc (t x) y v)))

(defn next-pos
  [[x y] k s]
  (cond (= k 0) [(+ x s) (+ y s)]
        (= k 1) [(+ x s) (- y s)]
        (= k 2) [(- x s) (- y s)]
        (= k 3) [(- x s) (+ y s)]
        :else "error: the k is invalid !"))

(defn set-value
  [t [x y] k s v]
  (loop [r (update-binary-array t [x y] v) i 1]
    (if (< i s)
      (recur (update-binary-array r (next-pos [x y] k i) (+ v i)) (inc i))
      r)))

(defn set-array
  [n]
  (let [m (* n n)]
    (loop [t (diamond-array n)
           [x y] [(bit-shift-left (bit-shift-right (dec n) 1) 1) (dec n)]
           k 0
           s 1
           v 0]
      ;; (print-binary-array t)
      ;; (println "[x, y]:" [x y] ", k:" k ", s:" s ", v:" v)
      (if (< v m)
        (recur (set-value t [x y] k s v)
               (next-pos [x y] k s)
               (mod (inc k) 4)
               (if (== 0 (rem k 2)) s (inc s))
               #_(let [a (- m v s) b (if (== 0 (rem k 2)) s (inc s))]
                   (if (< a b) a b))
               (+ v s))
        t))))


(defn get-square
  [x]
  (cond (< x 2) [x x]
        (< x 5) [2 4]
        (< x 10) [3 9]
        :else (loop [k 4 t (* k k)]
                (if (<= x t) [k t] (recur (inc k) (+ t k k 1))))))
