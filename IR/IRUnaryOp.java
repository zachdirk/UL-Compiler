package IR;
import IR.Temp.*;
public class IRUnaryOp extends IRInstruction {

	public Temp result;
	public Temp operand;
	public IRUnaryEnum op;

	public IRUnaryOp(Temp result, Temp operand, IRUnaryEnum operation){
		this.result = result;
		this.operand = operand;
		this.op = operation;
	}
	
	public String toString(){
		String oper = "";
		String type = Temp.IRType(operand.type);
		String s;
		switch(op){
			case NUMNEGATE:
				oper = type + "-";
				break;
			case BOOLNEGATE:
				oper = type + "!";
				break;
		}
		s = result.toString() + " := " +  oper + " " + operand.toString();
		return(s);
	}

}
