package AST;
import java.util.Vector;
public class FunctionCall extends Expression{
	Identifier id;
	Vector v;

	public FunctionCall(Identifier id, Vector v){
		this.id = id;
		this.v = v;
	}
	public void accept(Visitor v){
		v.visit(this);
	}
}
