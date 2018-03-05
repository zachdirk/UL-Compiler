package AST;
import Type.*;
import java.util.Vector;
import Temp.*;
import Visitor.*;
public class Block{

	public Vector<Statement> statements;

	public Block(){
		statements = new Vector<Statement>();
	}
	public void addElement(Statement s){
		statements.addElement(s);
	}
	
	public Statement elementAt(int index){
		return (Statement)statements.get(index);
	}
	
	public int size(){
		return statements.size();
	}

	public void acceptPrint(PrintVisitor v){
		v.visit(this);
	}

	public Type acceptSemantic(SemanticVisitor v){
		return(v.visit(this));
	}

	public Temp acceptTemp(TempVisitor v){
		return(v.visit(this));
	}
}
