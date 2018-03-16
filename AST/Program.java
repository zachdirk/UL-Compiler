package AST;
import java.util.Vector;
import Type.*;
import IR.Temp.*;
import Visitor.*;
import IR.*;
public class Program extends ASTNode {
	public Vector<Function> functionList;
	
	public Program (){
		functionList = new Vector<Function>();
	}
	
	public void addElement (Function f){
		functionList.addElement(f);
	}
	
	public Function elementAt (int index){
		return (Function)functionList.get(index);
	}
	
	public int size (){
		return functionList.size();
	}

	public void acceptPrint(PrintVisitor v){
		v.visit(this);
	}

	public Type acceptSemantic(SemanticVisitor v){
		return(v.visit(this));
	}

	public IRProgram acceptTemp(TempVisitor v, String name){
		return(v.visit(this, name));
	}


}

