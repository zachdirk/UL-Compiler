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
		String type = Temp.IRType(left.type);
		String s;
		switch(op){
			case ADD:
				oper = type + "+";
				break;
			case MULTIPLY:
				oper = type + "*";
				break;
			case SUBTRACT:
				oper = type + "-";
				break;
			case LESS:
				oper = type + "<";
				break;
			case EQUALS:
				oper = type + "==";
				break;
		}
		s = result.toString() + " := " + left.toString() + " " + oper + " " + right.toString();
		return(s);
	}

}
