(ns four-clojure.hard)


;; 53 Longest Increasing Sub-Seq
(fn [coll]
  (let [{:keys [longest current]}
        (reduce (fn [{:keys [longest current target status] :as m} v]
                  (case status
                    :find (if (= v (inc target))
                            (assoc m :current [target v] :status :reduce :target v)
                            (assoc m :target v))
                    :reduce (if (= v (inc target))
                              (assoc m :current (conj current v) :target v)
                              (if (< (count longest) (count current))
                                (assoc m :longest current :current [] :status :find :target v)
                                (assoc m :current [] :status :find :target v)))
                    (assoc m :target v :status :find)))
                {:longest [] :current []}
                coll)]
    (if (< (count longest) (count current))
      current
      longest)))


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

(defn get-square
  [x]
  (cond (< x 2) [x x]
        (< x 5) [2 4]
        (< x 10) [3 9]
        :else (loop [k 4 t (* k k)]
                (if (<= x t) [k t] (recur (inc k) (+ t k k 1))))))

(defn print-binary-array
  [t]
  (print (reduce (fn [s a] (str s (reduce #(str %1 %2) "" a) "\n")) "" t)))

(defn update-square
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
  [t [x y] k s v g]
  (loop [r (update-square t [x y] (g v)) i 1]
    (if (< i s)
      (recur (update-square r (next-pos [x y] k i) (g (+ v i))) (inc i))
      r)))

(defn set-array
  [a b]
  (let [g (digit-square a b)
        i (count g)
        [n m] (get-square i)
        r (- (* n 2) 1)
        g (into g (repeat (- m i) \*))]
    (println m n r g)
    (loop [t (vec (repeat r (vec (repeat r \space))))
           [x y] [(bit-shift-left (bit-shift-right (dec n) 1) 1) (dec n)]
           k 0
           s 1
           v 0]
      (print-binary-array t)
      (println [x y] k s v)
      (if (< v m)
        (recur (set-value t [x y] k s v g)
               (next-pos [x y] k s)
               (mod (inc k) 4)
               (if (== 0 (rem k 2)) s (inc s))
               #_(let [a (- m v s) b (if (== 0 (rem k 2)) s (inc s))]
                   (if (< a b) a b))
               (+ v s))
        t))))

