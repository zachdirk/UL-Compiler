package IR;
import IR.Temp.*;
public class IRUnaryOp extends IRInstruction {

	public Temp result;
	public Temp operand;
	public IRUnaryEnum operation;

	public IRUnaryOp(Temp result, Temp operand, IRUnaryEnum operation){
		this.result = result;
		this.operand = operand;
		this.operation = operation;
	}
	
}
