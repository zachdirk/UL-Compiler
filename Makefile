GNAME= ulGrammar
GSRC= $(GNAME).g

all: grammar compiler

grammar: $(GSRCS)
	java org.antlr.Tool -fo . $(GSRC) 

compiler: 
	javac Compiler.java -Xlint:unchecked



clean:
	rm *.class $(GNAME)*.java $(GNAME)__.g $(GNAME).tokens *~ Type/*.class Type/*~ AST/*.class AST/*~ Environment/*.class Environment/*~ Visitor/*.class Visitor/*~ IR/*.class IR/*~ IR/Temp/*.class IR/Temp/*~ Codegen/*.class Codegen/*~

 
