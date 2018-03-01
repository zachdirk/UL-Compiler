/*
 * Compiler.java
 *
 * A starting place for the unamed language compiler for CSC 435/535
 *
 */

import org.antlr.runtime.*;
import java.io.*;
import AST.*;
import Type.*;
public class Compiler {
	public static void main (String[] args) throws Exception {
		ANTLRInputStream input = null;

		if (args.length == 0 ) {
			System.out.println("Usage: Test filename.ul");
			return;
		}
		else {
			try{
				input = new ANTLRInputStream(new FileInputStream(args[0]));
			} catch(FileNotFoundException e){
				System.out.println("File \"" + args[0] + "\" not found. Exiting.");
				System.exit(1);
			}
		}

		// The name of the grammar here is "ulNoActions",
		// so ANTLR generates ulNoActionsLexer and ulNoActionsParser
		ulGrammarLexer lexer = new ulGrammarLexer(input);
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		ulGrammarParser parser = new ulGrammarParser(tokens);
		Program program = null;
		Visitor print = new PrintVisitor();
		Visitor semantic = new SemanticVisitor();
		try {
			program = parser.program();
		}
		catch (RecognitionException e )	{
			// A lexical or parsing error occured.
			// ANTLR will have already printed information on the
			// console due to code added to the grammar.  So there is
			// nothing to do here.
		}
		catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		}
		if (program != null){
			if (args.length > 1) {
				if (args[1].equals("-pp")){
					program.accept(print);
				}
			}
			try{
				program.accept(semantic);
			}catch (SemanticException e){
				System.out.println(e);
				System.exit(1);
			}
		}
	}
}
