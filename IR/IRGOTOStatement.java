package IR;
public class IRGOTOStatement extends IRInstruction{

	public IRLabel l;

	public IRGOTOStatement(IRLabel l){
		this.l = l;
	}

	public String toString(){
		return "GOTO L" + l.label;
	}

}
