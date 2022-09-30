default: build
	echo '[*] program compiled sucessfully'
	echo
debug: remake run
	echo '[*] debug finished'
	echo
remake: clean build
	echo '[*] finished remake'
	echo
run: build
	echo '[*] running the program'
	echo
	java Dak/Dictionary/DictionaryLLTest
build:
	find . -name \*.java > to_build.txt
	javac @to_build.txt
clean:
	find . -name \*.class -delete
	rm to_build.txt