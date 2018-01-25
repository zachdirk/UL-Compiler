package AST;
import java.util.Vector;
import Type.*;
public class PrintVisitor implements Visitor {
	public PrintVisitor(){

	}
	public void visit(AddExpression e){
		System.out.println(e.expr1 + " + " + e.expr2);	
	}
	public void visit(ArrayType t){
		System.out.println(t + "[]");	
	}
	//public void visit(ArrayAssignment e){}
	public void visit(AssignmentStatement s){
		System.out.println(s.id + " = " + s.expr + ";");
	}
	//public void visit(Block e){}
	//public void visit(BooleanLiteral e){}
	public void visit(BooleanType t){
		System.out.println(t);
	}
	public void visit(CharType t){
		System.out.println(t);
	}
	//public void visit(Declaration e){}
	public void visit(EqualityExpression e){
		System.out.println(e.expr1 + " == " + e.expr2);
	}
	//public void visit(ExpressionList e){}
	//public void visit(ExpressionStatement e){}
	//public void visit(FloatLiteral e){}
	public void visit(FloatType t){
		System.out.println(t);
	}
	public void visit(FormalParameter p){
		System.out.println(p.t + " " + p.id.id);
	}
	public void visit(FormalParameterList p){
		System.out.println("(" + p + ")");
	}
	public void visit(FunctionBody f){
		System.out.println("{\n" + f + "\n}");	
	}
	//public void visit(FunctionCall s){}
	public void visit(FunctionDeclaration f){
		System.out.print(f.t + " " + f.id);
		f.fpl.accept(this);	
	}
	public void visit(Function f){
		f.fd.accept(this);
		f.fb.accept(this);
	}
	public void visit(Identifier id){
		System.out.println(id.id);
	}
	//public void visit(IdentifierValue id){}
	//public void visit(IfStatement s){}
	//public void visit(IntegerLiteral i){}
	public void visit(IntegerType t){
		System.out.println(t);
	}
	public void visit(LessThanExpression e){
		System.out.println(e.expr1 + " <= " + e.expr2);
	}
	public void visit(MultExpression e){
		System.out.println(e.expr1 + " * " + e.expr2);
	}
	public void visit(ParenExpression e){
		System.out.println("( " + e.expr + " )");
	}
	public void visit(PrintStatement s){
		System.out.println(s);
	}
	public void visit(PrintLnStatement s){
		System.out.println(s);
	}
	public void visit(Program p){
		Vector v = p.functionList;
		for (int i = 0; i < v.size(); i++){
			Function f = (Function)v.get(i);			
			f.accept(this);
		}
	}
	//public void visit(ReturnStatement s){}
	//public void visit(StringLiteral s){}
	public void visit(SubtractExpression e){
		System.out.println(e.expr1 + " - " + e.expr2);
	}
	public void visit(StringType t){
		System.out.println(t);
	}
	//public void visit(TypeNode t){}
	//public void visit(VariableAssignment s){}
	public void visit(VariableDeclaration s){
		System.out.println(s.t + " " + s.id);	
	}
	//public void visit(VariableDeclarationList s){}
	public void visit(VoidType t){
		System.out.println(t);
	}
	//public void visit(WhileStatement s){}
}
