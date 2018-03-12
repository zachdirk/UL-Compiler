GNAME= ulGrammar
GSRC= $(GNAME).g

all: grammar compiler

grammar: $(GSRCS)
	java org.antlr.Tool -fo . $(GSRC) 

compiler: Visitor Type Environment AST IR
	javac Compiler.java -Xlint:unchecked

IR: Temp
	javac IR/*.java -Xlint:unchecked

Visitor: Environment AST
	javac Visitor/*.java -Xlint:unchecked

Type:
	javac Type/*.java -Xlint:unchecked

Environment:
	javac Environment/*.java -Xlint:unchecked

AST: IR 
	javac AST/*.java -Xlint:unchecked

Temp:
	javac IR/Temp/*.java -Xlint:unchecked

clean:
	rm *.class $(GNAME)*.java $(GNAME)__.g $(GNAME).tokens *~ Type/*.class Type/*~ AST/*.class AST/*~ Environment/*.class Environment/*~ Visitor/*.class Visitor/*~

 
