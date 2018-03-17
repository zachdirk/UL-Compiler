package IR;
import IR.Temp.*;
import Type.*;
public class IRPrintLnStatement extends IRInstruction {

	public Temp expr;

	public IRPrintLnStatement(Temp expr){
		this.expr = expr;
	}

	public String toString(){
		Type t = expr.type;
		String type = Temp.IRType(t);
		String s = "PRINTLN" + type + " " + expr.toString();
		return(s);
	}

}
