(ns comm.wrapper
  (require [clojure.set :refer :all]
           [clojure.string :as str]
           [clojure.pprint :refer :all]
           )
  (use [clojure.string :only (split)])
  )

(import '(java.net ServerSocket Socket SocketException)
        '(java.io InputStreamReader OutputStreamWriter)
        '(clojure.lang LineNumberingPushbackReader))


;___ active socket is used as a global _____________
(def shrdlu-comms false)


(defn startup-server
  [port]
  (let [ss (new ServerSocket port)]
    (println "advertising " ss)
    (try (let [s (.accept ss)]
           (println "socket accepted " s)

           {:sock s
            :inp  (new LineNumberingPushbackReader
                       (new InputStreamReader (.getInputStream s)))
            :outp (new OutputStreamWriter (.getOutputStream s))
            }
           )
         (catch SocketException e))
    ))

(defn socket-write
  "low-level socket writer"
  [socket x]
  (binding [*out* (:outp socket)]
    (println x)
    ))


(defn socket-read
  "low-level socket reader"
  [socket]
  (read (:inp socket)))

(defn socket-input-waiting
  [socket]
  (.ready (:inp socket)))


;___ netlogo reading/writing _____________

(
  defn set-shrdlu-comms [port]
  (def shrdlu-comms (startup-server port)))

(defn startup [port]

  (set-shrdlu-comms port)

  )
(defn nlogo-send [txt]
  ;(println '** (and shrdlu-comms true) txt)
  (if shrdlu-comms (socket-write shrdlu-comms txt)))

(defn nlogo-read []
  (if shrdlu-comms (socket-read shrdlu-comms)))

(defn nlogo-io-waiting []
  (and shrdlu-comms (socket-input-waiting shrdlu-comms)))




(defn nlogo-send-exec [times]
  (nlogo-send (str "finrepl " times))
  ;(crt-rabbits times)
  )


(defn concat-all [coll]
  (join " " coll))


(defn n-logosend1
  [results]

  (nlogo-send '(startup))
  (def one (count [:cmds results]))
  (def two "finrepl")
  (prn (count (:cmds results)))
  (nlogo-send (list two one))
  ()
  (doall (map nlogo-send-exec (:cmds results)))
  (:cmds  results)

  )




;user=> (def s25 (startup-server 2222))
;advertising  #<ServerSocket ServerSocket[addr=0.0.0.0/0.0.0.0,port=0,localport=2225]>
;socket accepted  #<Socket Socket[addr=/152.105.17.36,port=55053,localport=2225]>
;#'user/s25
;user=> s25
;{:sock #<Socket Socket[addr=/152.105.17.36,port=55053,localport=2225]>, :inp #<LineNumberingPushbackReader clojure.lang.LineNumberingPushbackReader@176feac>, :outp #<OutputStreamWriter java.io.OutputStreamWriter@dc033a>}
;user=> (socket-read s25)
;test-message-1
;user=> (socket-read s25)
;test-message-2
;user=> (socket-write s25 "banana")
;nil


