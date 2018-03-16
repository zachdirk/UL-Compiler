package IR;
import java.util.Vector;
public class IRProgram{
	public String name;
	public Vector<IRFunction> functions;

	public IRProgram(String name){
		this.name = name;
		functions = new Vector<IRFunction>();
	}

	public void addElement (IRFunction f){
		functions.addElement(f);
	}
	
	public IRFunction elementAt (int index){
		return (IRFunction)functions.get(index);
	}
	
	public int size (){
		return functions.size();
	}
	
	public String toString(){
		String s = name + '\n';
		IRFunction f;
		for (int i = 0; i < functions.size(); i++){
			f = (IRFunction)functions.get(i);
			s = s + f.toString() + '\n';
		}
		return s;
	}
}
