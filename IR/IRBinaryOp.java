package IR;
import IR.Temp.*;
public class IRBinaryOp extends IRInstruction {

	public Temp result;
	public Temp left;
	public Temp right;
	public IRBinaryEnum op;

	public IRBinaryOp(Temp result, Temp left, Temp right, IRBinaryEnum op){
		this.result = result;
		this.left = left;
		this.right = right;
		this.op = op;
	}
	
	public String toString(){
		String oper = "";
		String type = "";
		String s;
		switch(op){
			case ADD:
				type = Temp.IRType(result.type);
				oper = type + "+";
				break;
			case MULTIPLY:
				type = Temp.IRType(result.type);
				oper = type + "*";
				break;
			case SUBTRACT:
				type = Temp.IRType(result.type);
				oper = type + "-";
				break;
			case LESS:
				type = Temp.IRType(left.type);
				oper = type + "<";
				break;
			case EQUALS:
				type = Temp.IRType(left.type);
				oper = type + "==";
				break;
		}
		s = result.toString() + " := " + left.toString() + " " + oper + " " + right.toString();
		return(s);
	}

}
