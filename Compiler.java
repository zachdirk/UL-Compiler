/*
 * Compiler.java
 *
 * A starting place for the unamed language compiler for CSC 435/535
 *
 */

import org.antlr.runtime.*;
import java.io.*;
import java.util.Vector;
import AST.*;
import Type.*;
import Visitor.*;
import IR.*;
import IR.Temp.*;
import Codegen.*;
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
		boolean pretty_print = false;
		boolean optimization = false;
		boolean print_ir = false;
		boolean print_j = false;
		PrintVisitor print = new PrintVisitor();
		SemanticVisitor semantic = new SemanticVisitor();
		TempVisitor temp = new TempVisitor();
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
				for (String s: args){
					if (s.equals("-pp"))
						pretty_print = true;
					if (s.equals("-o"))
						optimization = true;
					if (s.equals("-ir"))
						print_ir = true;
					if (s.equals("-j"))
						print_j = true;
				}
			}
			try{
				if (pretty_print)
					program.acceptPrint(print);
				program.acceptSemantic(semantic);
			}catch (SemanticException e){
				System.out.println(e);
				System.exit(1);
			}
			String fname = args[0];
			if (fname.endsWith(".ul"))
				fname = fname.substring(0, fname.lastIndexOf('.'));
			IRProgram ir = program.acceptTemp(temp, fname);
			if (print_ir)			
				System.out.println(ir);
			JFile file = new JFile(ir);
			if (print_j)
				file.print();
		}
	}
}
