package IR;
public class IRLabel extends IRInstruction{

	public int label;

	public IRLabel(int label){
		this.label = label;
	}

	public String toString(){
		return "L" + label + ":";
	}

}
