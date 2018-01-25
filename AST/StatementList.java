package AST;
import Type.*;
import java.util.Vector;
public class StatementList extends ASTNode {
	
	Vector statementList;

	public StatementList(){
		statementList = new Vector();
	}

	public void addElement (FormalParameter f){
		statementList.addElement(f);
	}
	
	public Function elementAt (int index){
		return (Statement)statementList.elementAt(index);
	}
	
	public int size (){
		return statementList.size();
	}

	public void accept (Visitor v){
		v.visit(this);
	}

}
