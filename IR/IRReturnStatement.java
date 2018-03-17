package IR;
import IR.Temp.*;
public class IRReturnStatement extends IRInstruction{

	public Temp t;

	public IRReturnStatement(Temp t){
		this.t = t;
	}

	public String toString(){
		if (t == null)
			return "RETURN";
		else 
			return "RETURN " + t.toString();
	}

}
