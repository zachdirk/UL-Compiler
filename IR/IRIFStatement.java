package IR;
import IR.Temp.*;
public class IRIFStatement extends IRInstruction{

	public Temp t;
	public IRLabel l;

	public IRIFStatement(Temp t, IRLabel l){
		this.t = t;
		this.l = l;
	}

	public String toString(){
		return "IF " + t.toString() + " GOTO L" + l.label;
	}

}
