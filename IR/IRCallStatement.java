package IR;
import java.util.Vector;
import IR.Temp.*;
public class IRCallStatement extends IRInstruction{

	public String func;
	public Vector<Temp> params;

	public IRCallStatement(String func, Vector<Temp> params){
		this.func = func;
		this.params = params;
	}

	public String toString(){
		String s = "CALL " + func + "(";
		if (params.size() > 0){
			s = s + params.get(0).toString();
		}
		for (int i = 1; i < params.size(); i++){
			s = s + " " + params.get(i).toString(); 
		}
		s = s + ")";
		return s;
	}

}
