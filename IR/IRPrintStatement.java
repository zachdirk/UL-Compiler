package IR;
import IR.Temp.*;
import Type.*;
public class IRPrintStatement extends IRInstruction {

	public Temp expr;

	public IRPrintStatement(Temp expr){
		this.expr = expr;
	}

	public String toString(){
		Type t = expr.type;
		String type = Temp.IRType(t);
		String s = "PRINT" + type + " " + expr.toString();
		return(s);
	}

}
