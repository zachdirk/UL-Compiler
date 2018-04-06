GNAME= ulGrammar
GSRC= $(GNAME).g

all: grammar compiler

grammar: $(GSRCS)
	java org.antlr.Tool -fo . $(GSRC) 

compiler: Visitor Type Environment AST IR Codegen
	javac Compiler.java -Xlint:unchecked

Visitor: Environment Codegen AST
	javac Visitor/*.java -Xlint:unchecked

Codegen: AST
	javac Codegen/*.java -Xlint:unchecked

AST: IR Type
	javac AST/*.java -Xlint:unchecked

IR: Temp
	javac IR/*.java -Xlint:unchecked

Type:
	javac Type/*.java -Xlint:unchecked

Environment:
	javac Environment/*.java -Xlint:unchecked

Temp:
	javac IR/Temp/*.java -Xlint:unchecked

clean:
	rm *.class $(GNAME)*.java $(GNAME)__.g $(GNAME).tokens *~ Type/*.class Type/*~ AST/*.class AST/*~ Environment/*.class Environment/*~ Visitor/*.class Visitor/*~ IR/*.class IR/*~ IR/Temp/*.class IR/Temp/*~ Codegen/*.class Codegen/*~

 
