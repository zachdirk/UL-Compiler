package IR;
import IR.Temp.*;
import java.util.Vector;
public class IRFunction{
	public String name;
	public String signature;
	public Vector<IRInstruction> instructions;
	public TempList temps;

	public IRFunction(String name, String signature, Vector<IRInstruction> instructions, TempList temps){
		this.name = name;
		this.signature = signature;
		this.instructions = instructions;
		this.temps = temps;
	}

	public String toString(){
		String s = "FUNC " + name + " " + signature + "\n{\n";
		s = s + temps.toString();
		for (int i = 0; i < instructions.size(); i++){
			s = s + '\t' + instructions.get(i).toString() + ";\n";
		}
		s = s + "}";
		return s;
	}
}
