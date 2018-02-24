package AST;
import java.util.Vector;
import Type.*;
public class PrintVisitor implements Visitor{
	
	private int indent;
	
	public PrintVisitor(){
		indent = 0;
	}
	
	private String tabs(){
		String s = "";
		for (int i = 0; i < indent; i++){
			s = s + "    ";
		}
		return(s);
	}	
	public Type visit(AddExpression e){
		System.out.print(e.expr1 + "+" + e.expr2);
		return null;	
	}
	public Type visit(ArrayType t){
		System.out.print(t);
		return null;
	}
	public Type visit(ArrayAssignment a){
		System.out.print("\n" + tabs());		
		a.id.accept(this);
		System.out.print("[");
		a.index.accept(this);
		System.out.print("]=");
		a.e.accept(this);
		System.out.print(";");
		return null;
	}
	public Type visit(ArrayReference a){
		System.out.print(a);
		return null;
	}
	public Type visit(AssignmentStatement a){
		System.out.print("\n" + tabs());
		a.id.accept(this);
		System.out.print("=");
		a.expr.accept(this);
		System.out.print(";");
		return null;
	}
	public Type visit(Block b){
		System.out.print(tabs() + "{");
		indent++;
		Statement s = null;
		for (int i = 0; i < b.size(); i++){
			s = b.elementAt(i);
			s.accept(this);
		}
		indent--;
		System.out.print("\n" + tabs() + "}");
		return null;
	}
	public Type visit(BooleanLiteral e){
		System.out.print(e);
		return null;
	}
	public Type visit(BooleanType t){
		System.out.print(t);
		return null;
	}
	public Type visit(CharacterLiteral e){
		System.out.print(e);
		return null;
	}
	public Type visit(CharType t){
		System.out.print(t);
		return null;
	}
	public Type visit(EqualityExpression e){
		System.out.print(e.expr1 + "==" + e.expr2);
		return null;
	}
	public Type visit(ExpressionStatement e){
		System.out.print("\n" + tabs());		
		e.e.accept(this);
		System.out.print(";");
		return null;
	}
	public Type visit(FloatLiteral e){
		System.out.print(e);
		return null;
	}
	public Type visit(FloatType t){
		System.out.print(t);
		return null;
	}
	public Type visit(FormalParameter p){
		p.t.accept(this);
		System.out.print(" ");
		p.id.accept(this);
		return null;
	}
	public Type visit(FormalParameterList p){
		System.out.print("(");
		Vector params = p.parameterList;
		FormalParameter fp;
		if (params.size() > 0){
			fp = (FormalParameter)params.get(0);
			fp.accept(this);
		}
		for (int i = 1; i < params.size(); i++){
			System.out.print(", ");
			fp = (FormalParameter)params.get(i);
			fp.accept(this);
		}
		System.out.print(")");
		return null;
	}
	public Type visit(FunctionBody f){
		System.out.print("\n{");
		indent++;
		Vector v = f.varDecls;
		if (v.size() > 0){
			VariableDeclaration vd = (VariableDeclaration)v.get(0);
			vd.accept(this);
		}
		for (int i = 1; i < v.size(); i++){
			VariableDeclaration vd = (VariableDeclaration)v.get(i);
			vd.accept(this);
		}
		if (v.size() > 0)		
			System.out.println(); //if there were vardecls we have to separate them from statements
		v = f.statements;
		if (v.size() > 0){
			Statement s = (Statement)v.get(0);
			s.accept(this);
		}
		for (int i = 1; i < v.size(); i++){
			Statement s = (Statement)v.get(i);
			s.accept(this);
		} 
		System.out.println("\n}");
		indent--;	
		return null;
	}
	public Type visit(FunctionCall f){
		f.id.accept(this);
		System.out.print("(");
		if (f.v.size() > 0) {
			Expression e = (Expression)f.v.get(0);
			e.accept(this);
		}
		for (int i = 1; i < f.v.size(); i++){
			System.out.print(",");
			Expression e = (Expression)f.v.get(i);
			e.accept(this);
			
		}
		System.out.print(")");
		return null;
	}
	public Type visit(FunctionDeclaration f){
		f.t.accept(this);
		System.out.print(" ");
		f.id.accept(this);
		System.out.print(" ");
		f.fpl.accept(this);	
		return null;
	}
	public Type visit(Function f){
		f.fd.accept(this);
		f.fb.accept(this);
		return null;
	}
	public Type visit(Identifier id){
		System.out.print(id.id);
		return null;
	}
	public Type visit(IdentifierValue id){
		System.out.print(id.id);
		return null;
	}
	public Type visit(IfStatement s){
		System.out.print("\n" + tabs() + "if (");
		s.condition.accept(this);
		System.out.print(")\n");
		s.b1.accept(this);
		if (s.b2 != null){
			System.out.print("\n" + tabs() + "else\n");
			s.b2.accept(this);
		}
		return null;
	}
	public Type visit(IntegerLiteral i){
		System.out.print(i);
		return null;
	}
	public Type visit(IntegerType t){
		System.out.print(t);
		return null;
	}
	public Type visit(LessThanExpression e){
		System.out.print(e.expr1 + "<" + e.expr2);
		return null;
	}
	public Type visit(MultExpression e){
		System.out.print(e.expr1 + "*" + e.expr2);
		return null;
	}
	public Type visit(ParenExpression e){
		System.out.print("(" + e.expr + ")");
		return null;
	}
	public Type visit(PrintStatement s){
		System.out.print("\n" + tabs() + "print ");
		s.e.accept(this);
		System.out.print(";");
		return null;
	}
	public Type visit(PrintLnStatement s){
		System.out.print("\n" + tabs() + "println ");
		s.e.accept(this);
		System.out.print(";");
		return null;
	}
	public Type visit(Program p){
		Vector v = p.functionList;
		Function f;		
		if (v.size() > 0){
			f = (Function)v.get(0);
			f.accept(this);
		}
		for (int i = 1; i < v.size(); i++){
			System.out.println();
			f = (Function)v.get(i);			
			f.accept(this);
		}
		return null;
	}
	public Type visit(ReturnStatement s){
		System.out.print("\n" + tabs() + "return");
		if (s.e != null){
			System.out.print(" ");
			s.e.accept(this);
		}
		System.out.print(";");
		return null;
	}
	public Type visit(StringLiteral s){
		System.out.print(s);
		return null;
	}
	public Type visit(SubtractExpression e){
		System.out.print(e.expr1 + "-" + e.expr2);
		return null;
	}
	public Type visit(StringType t){
		System.out.print(t);
		return null;
	}
	public Type visit(VariableDeclaration s){
		System.out.print("\n" + tabs());
		s.t.accept(this);
		System.out.print(" ");
		s.id.accept(this);
		System.out.print(";");
		return null;
	}
	public Type visit(VoidType t){
		System.out.print(t);
		return null;
	}
	public Type visit(WhileStatement s){
		System.out.print("\n" + tabs() + "while (");
		s.condition.accept(this);
		System.out.print(")\n");
		s.b.accept(this);
		return null;
	}
}
