all:
	javac backEnd/*.java
	javac ShoppingCart.java

resetFiles:
	rm -f backEnd/wallet.txt
	rm -f backEnd/pocket.txt
	echo 30000 > backEnd/wallet.txt
	touch backEnd/pocket.txt

clean: resetFiles
	rm -f *.class
	rm -f backEnd/*.class
