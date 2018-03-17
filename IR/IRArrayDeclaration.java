package IR;
import IR.Temp.*;
import AST.*;
import Type.*;
public class IRArrayDeclaration extends IRInstruction {

	public Temp var;
	public int size;


	public IRArrayDeclaration(Temp var, IntegerLiteral size){
		this.var = var;
		this.size = size.i;
	}

	public String toString(){
		ArrayType t = (ArrayType)var.type;
		
		String s = var.toString() + " := NEWARRAY " + Temp.IRType(t.t) + size;
		return(s);
	}

}
