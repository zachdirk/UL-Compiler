package IR;
import IR.Temp.*;
import AST.*;
public class IRArrayAssignment extends IRInstruction {

	public Temp arr;
	public Temp index;
	public Temp expr;


	public IRArrayAssignment(Temp arr, Temp index, Temp expr){
		this.arr = arr;		
		this.index = index;
		this.expr = expr;
	}

	public String toString(){
		String s = arr.toString() + "[" + index.toString() + "]" + " := " + expr.toString();
		return(s);
	}

}
