package IR;
import IR.Temp.*;
import AST.*;
public class IRExpressionAssignment extends IRInstruction {

	public Temp result;
	public Expression e;

	public IRExpressionAssignment(Temp result, Expression e){
		this.result = result;		
		this.e = e;
	}

	public String toString(){
		String s = result.toString() + " := " + e.toString();
		return(s);
	}

}
