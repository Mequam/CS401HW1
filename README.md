# CS401HW1

java networking practice

---

## About

this is a simple set of dictionary programs
in order to send and recive dictionary definitions
over a network

this program was created for homework one of class CS401 at SVSU

---

## Building

the repo has a fully functional makefile in order
to build the java programs

ensure that javac and "make tools" are installed on the system

*then simply run*

> make

---

## Running

In order to run the client and the server from the same terminal simply run

> make run

if you want the server client running on different computers the following commands will run the client and server code respectivly

> java Dak/CS401HW1/MainServer

> java Dak/CS401HW1/MainClient



> **NOTE: make sure to build the classes first** 

The client defaults to connecting to local host, if you want the client to connect to another machine, simply change the connection address in the client script before compiling.