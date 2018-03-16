package IR;
import IR.Temp.*;
public class IRConditionalJump extends IRInstruction{
	public Temp condition;
	public IRLabel label;
	public IRConditionalJump(Temp condition, IRLabel label){
		this.condition = condition;
		this.label = label;
	}

	public String toString(){
		return "IF " + condition.toString() + " GOTO " + label.toString();
	}

}
