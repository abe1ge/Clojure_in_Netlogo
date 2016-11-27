# clojure_in_netlogo

FIXME: description

## Installation

Download from http://example.com/FIXME.

## Usage

FIXME: 
1. run main

    => advertising  #object[java.net.ServerSocket 0x143ef68b ServerSocket[addr=0.0.0.0/0.0.0.0,localport=2223]] 
2. Open netlogo 
3. enter localport number in netlogo port-num and hit connect

    => socket accepted  #object[java.net.Socket 0x4d56bcf4 Socket[addr=/152.105.99.227,port=1719,localport=2223]]
5. click setup
6. try in netlogo command center with 
7. 

    clj "2 + 4 5)
    the first number is the number of arguments, the second item the function you want to use and the rest is the arguments
9.

    $ java -jar clojure_in_netlogo-0.1.0-standalone.jar [args]

## Options

FIXME: listing of options this app accepts.



## Examples
;; use word to use clj multiple times
e.g.

    clj (word "2 + 2 "(clj "3 + 7 5 4"))
    => 18
    clj (word "1 first" (clj "2 split \"hello my name is\" #\" \""))
    => "hello"
...

### Bugs
becarful of what you ask for, 
its easy to unsync the read and write of both componenets so when you are asking for something, wait for a reply otherwise you could be waiting for ever.

also it hasn't happended to me yet but i suspect if used enough time, stack overflow is inevtable, don't know how to go about this
if u know the fix, do one of those pull push things with git hub 
...


## License

Copyright © 2016 abel (use as you please)
