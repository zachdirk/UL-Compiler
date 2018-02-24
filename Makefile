GNAME= ulGrammar
GSRC= $(GNAME).g

all: grammar compiler

grammar: $(GSRCS)
	java org.antlr.Tool -fo . $(GSRC) 

compiler:
	javac *.java AST/*.java Environment/*.java Type/*.java -Xlint:unchecked

clean:
	rm *.class $(GNAME)*.java $(GNAME)__.g $(GNAME).tokens *~ Type/*.class Type/*~ AST/*.class AST/*~

 
