GNAME= ulGrammar
GSRC= $(GNAME).g

all: grammar compiler

grammar: $(GSRCS)
	java org.antlr.Tool -fo . $(GSRC) 

compiler: Visitors Types Environments AST
	javac *.java -Xlint:unchecked

Visitors:
	javac Visitor/*.java -Xlint:unchecked

Types:
	javac Type/*.java -Xlint:unchecked

Environments:
	javac Environment/*.java -Xlint:unchecked

AST:
	javac AST/*.java -Xlint:unchecked


clean:
	rm *.class $(GNAME)*.java $(GNAME)__.g $(GNAME).tokens *~ Type/*.class Type/*~ AST/*.class AST/*~ Environment/*.class Environment/*~ Visitor/*.class Visitor/*~

 
