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

(defn break [mes]
  (split mes #":" )
  )

(defn arange []
  (let [cmd  (nlogo-read)
        arg1 (nlogo-read)
        arg2 (nlogo-read)
        ]
    (nlogo-send (call cmd arg1 arg2))

  ))

;(defn nread-check [mes]
;  (if (not= mes "stop")
;    (do (println "start")
;        ;(fmlogo mes)
;        ;(nlogo-send-exec 1)
;        (nlogo-send-chage mes)
;        (println "end")
;        (nread-check (nlogo-str))
;        )
;    (println "I am stoping" mes)
;    ))