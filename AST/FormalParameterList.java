package AST;
import Type.*;
import java.util.Vector;
import IR.Temp.*;
import Visitor.*;
public class FormalParameterList extends ASTNode {
	
	public Vector<FormalParameter> parameterList;

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
