package AST;
import Type.*;
import java.util.Vector;
public class FormalParameterList extends ASTNode {
	
	Vector parameterList;

	public FormalParameterList(){
		parameterList = new Vector();
	}

	public void addElement (FormalParameter f){
		parameterList.addElement(f);
	}
	
	public FormalParameter elementAt (int index){
		return (FormalParameter)parameterList.get(index);
	}
	
	public int size (){
		return parameterList.size();
	}

	public void accept (Visitor v){
		v.visit(this);
	}

}
