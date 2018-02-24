package AST;
import java.util.Vector;
import Type.*;
public class Program extends ASTNode {
	Vector<Function> functionList;
	
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

	public Type accept(Visitor v){
		return(v.visit(this));
	}


}

