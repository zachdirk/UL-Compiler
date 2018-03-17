package IR;
import IR.Temp.*;
import AST.*;
public class IRArrayReference extends IRInstruction {

	public Temp result;
	public Temp arr;
	public Temp index;

	public IRArrayReference(Temp result, Temp arr, Temp index){
		this.result = result;
		this.arr = arr;		
		this.index = index;
	}

	public String toString(){
		String s = result.toString() + " := " + arr.toString() + "[" + index.toString() + "]";
		return(s);
	}

}
