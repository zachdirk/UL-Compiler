package IR;
public class IRJump extends IRInstruction{

	public IRLabel label;

	public IRJump(IRLabel label){
		this.label = label;
	}

	public String toString(){
		return "GOTO " + label.toString();
	}	

}
