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

	public String toString(){
		if (parameterList.isEmpty()){
			return "";
		}
		String s = "";
		FormalParameter fp = (FormalParameter)parameterList.get(0);
		s = fp.toString();
		for (int i = 1; i < parameterList.size(); i++){
			fp = (FormalParameter)parameterList.get(i);			
			s = s + ", " + fp.toString();
		}
		return s;
	}

}
