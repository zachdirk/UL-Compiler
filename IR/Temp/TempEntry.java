package IR.Temp;
import Type.*;
import Visitor.*;
public class TempEntry{
	public Temp t;
	public boolean inUse;
	public TempClass cls;
	public String name;

	public TempEntry(Temp t, boolean inUse){
		this(t, inUse, TempClass.TEMP, "");
	}

	public TempEntry(Temp t, boolean inUse, TempClass cls, String name){
		this.t = t;
		this.inUse = inUse;
		this.name = name;
		this.cls = cls;	
	}

	public String toString(){
		String s = "";
		if (!(cls == TempClass.TEMP)){
			if (cls == TempClass.PARAMETER)
				s = "TEMP " + t.n + ":" + Temp.IRType(t.type) + "\t[P(\"" + name + "\")];";
			else
				s = "TEMP " + t.n + ":" + Temp.IRType(t.type) + "\t[L(\"" + name + "\")];";
		} else {
			s = "TEMP " + t.n + ":" + Temp.IRType(t.type) + ";";
		}
		return(s);
	}
}
