(ns comm.nexec
  (require [comm.wrapper :refer :all]
           [lib.numbers :refer :all])
  (use [clojure.string :only (split)])
  )

(defn call [^String nm & args]
  (when-let [fun (ns-resolve *ns* (symbol nm))]
    (apply fun args)
    )
  )


(defn arange []
  (let [cmd  (nlogo-read)
        arg1 (nlogo-read)
        arg2 (nlogo-read)
        ]
    (nlogo-send (call cmd arg1 arg2))
    (println "answer sent")

    ))

(defn reading []
  (loop [x (+ 1 (nlogo-read))
         result []]
    (if (= x 0)
      (do (println x)
          result)
      (recur (- x 1)
             (conj result (nlogo-read)))
      ))
  )

(defn exec []
  (let [x (reading)
        ]
    (apply call (first x) (rest x))
    )
  )


(defn online?
  []
  (if shrdlu-comms
    (do (println "is online")
        (exec)
        )
    (do (startup 2222)
        (println "connected to port 2222"))
    ))























;(defn listen [mes]
;     (arange)
;     (listen (nlogo-read))
;  )
;
;(defn light [mes]
;  (let [x mes]
;    (if (= x "stop")
;      (println x)
;      (arange)
;    )))
;(defn arange []
;  (let [cmd  (nlogo-read)
;        arg1 (nlogo-read)
;        arg2 (nlogo-read)
;        ]
;    (nlogo-send (call cmd arg1 arg2))
;    (light (nlogo-read))
;    ))