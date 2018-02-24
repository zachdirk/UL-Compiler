package AST;
import Type.*;
import java.util.Vector;
public class FormalParameterList extends ASTNode {
	
	Vector<FormalParameter> parameterList;

	public FormalParameterList(){
		parameterList = new Vector<FormalParameter>();
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


	public Type accept(Visitor v){
		return(v.visit(this));
	}

}
