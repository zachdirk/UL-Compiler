package AST;
import java.util.Vector;
public class Block{

	Vector statements;

	public Block(){
		statements = new Vector();
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

	public void accept(Visitor v){
		v.visit(this);
	}
}
