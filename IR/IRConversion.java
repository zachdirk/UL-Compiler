package IR;
import IR.Temp.*;
import Type.*;
public class IRConversion extends IRInstruction{

	public Temp var;
	public Temp result;
	public Type from;
	public Type to;


	public IRConversion(Temp var, Temp result, Type from, Type to){
		this.result = result;
		this.var = var;
		this.from = from;
		this.to = to;
	}

	public String toString(){
		String s = result.toString() + " := " + Temp.IRType(from) + "2" + Temp.IRType(to) + " " + var.toString();
		return(s);
	}

}
