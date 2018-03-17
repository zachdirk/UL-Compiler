package IR;
import IR.Temp.*;
import AST.*;
public class IRCallAssignment extends IRInstruction {

	public Temp result;
	public IRCallStatement func;

	public IRCallAssignment(Temp result, IRCallStatement func){
		this.result = result;		
		this.func = func;
	}

	public String toString(){
		String s = result.toString() + " := " + func.toString();
		return(s);
	}

}
