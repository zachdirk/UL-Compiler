package IR.Temp;
import Type.*;
public class Temp{
	public int n;
	public Type type;

	public Temp(int n, Type t){
		this.n = n;
		this.type = t;
	}

	public static String IRType(Type type){
		String s = "";
		if (type instanceof BooleanType)
			s = "Z";
		if (type instanceof CharType)
			s = "C";
		if (type instanceof IntegerType)
			s = "I";
		if (type instanceof FloatType)
			s = "F";
		if (type instanceof StringType)
			s = "U";
		if (type instanceof VoidType)
			s = "V";
		if (type instanceof ArrayType){
			ArrayType t2 = (ArrayType)type;			
			s = "A" + IRType(t2.t);
		}
		return s;
	}

	public String toString(){
		return "T" + n;
	}
}
