package IR;
import IR.Temp.*;
import AST.*;
public class IRAssignment extends IRInstruction {

	public Temp result;
	public Temp var;

	public IRAssignment(Temp result, Temp var){
		this.result = result;		
		this.var = var;
	}

	public String toString(){
		String s = result.toString() + " := " + var.toString();
		return(s);
	}

}
