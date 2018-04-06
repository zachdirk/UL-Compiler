package IR.Temp;
import Type.*;
import java.util.Vector;
public class TempList{

	public int numTemps;
	public Vector<TempEntry> temps;
	public boolean optimize;

	public TempList(){
		numTemps = 0;
		temps = new Vector<TempEntry>();
		optimize = false;
	}

	public Temp findTemp(String name){
		TempEntry e;
		for (int i = 0; i < numTemps; i++){
			e = temps.get(i);
			if (e.name.equals(name))
				return(e.t);
		}
		return(null);
	}

	public int numTemps(){
		return numTemps;
	}

	public Temp getTemp(Type t){
		Temp tmp = null;
 		TempEntry ent = null;
		if (optimize){
			//later
		} else {
			tmp = new Temp(numTemps++, t);
			ent = new TempEntry(tmp, false);
			temps.add(ent);
		}
			
		return tmp;
	}
	public Temp getTemp(Type t, TempClass cls, String name){
		Temp tmp = null;
 		TempEntry ent = null;
		if (optimize){
			//later
		} else {
			tmp = new Temp(numTemps++, t);
			ent = new TempEntry(tmp, false, cls, name);
			temps.add(ent);
		}
			
		return tmp;
	}
	public void returnTemp(Temp t){
		//later
	}
	public boolean isParamOrLocal(Temp t){
		int n = t.n;				
		TempEntry e;
		for (int i = 0; i < numTemps; i++){
			e = temps.get(i);
			if (e.t.n == n)
				if (e.cls == TempClass.PARAMETER || e.cls == TempClass.LOCAL)
					return true;
		}
		return false; 
	}
	
	public TempEntry get(int i){
		return (TempEntry)temps.get(i);
	}
	
	public String toString(){
		String s = "";
		for (int i = 0; i < temps.size(); i++)
			s = s + '\t' + temps.get(i).toString() + '\n';
		return(s);
	}

}
