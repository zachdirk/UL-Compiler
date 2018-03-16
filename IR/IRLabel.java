package IR;
public class IRLabel extends IRInstruction{

	public static int count = 0;
	public int label;

	public IRLabel(){
		label = count++;
	}

	public String toString(){
		return "L" + label + ":";
	}

}
