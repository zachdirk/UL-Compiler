package IR.Temp;
import Type.*;
public class Temp{
	public int n;
	public Type type;

	public Temp(int n, Type t){
		this.n = n;
		this.type = t;
	}

	public String toString(){
		return "T" + n;
	}
}
