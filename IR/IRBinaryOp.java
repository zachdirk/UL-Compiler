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
	
}
