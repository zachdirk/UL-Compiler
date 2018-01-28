#
GNAME= ulGrammar
GSRC= $(GNAME).g

all: grammar compiler

grammar: $(GSRCS)
	java org.antlr.Tool -fo . $(GSRC) 

compiler:
	javac *.java -Xlint:unchecked

clean:
	rm *.class $(GNAME)*.java $(GNAME)__.g $(GNAME).tokens *~
	rm Type/*.class Type/*~
	rm AST/*.class AST/*~


 
