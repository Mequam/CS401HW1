#!/bin/bash
java Dak/CS401HW1/MainServer & #run the server in the background
java Dak/CS401HW1/MainClient $1
kill $(ps -ax | grep Dak/CS401HW1 | awk {'print $1'}) #stop the server from running