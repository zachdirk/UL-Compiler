package Codegen;
import IR.*;
import IR.Temp.*;
import Type.*;
import AST.*;
import java.util.Vector;
public class JFile {

	public IRProgram prog;
	public static int label;

	public JFile(IRProgram prog){
		this.prog = prog;
		label = 0;
	}
	
	public static char JType(Type t){
		if (t instanceof StringType || t instanceof ArrayType)
			return 'a';
		else if (t instanceof FloatType)
			return 'f';
		else if (t instanceof VoidType)
			return 'v';
		else
			return 'i'; 
	}

	public static String VarType(Type t){
		if (t instanceof StringType){
			return "Ljava/lang/String;";	
		} else if (t instanceof ArrayType){
			ArrayType t2 = (ArrayType)t;
			return "[" + JType(t2.t);
		} else {
			return Temp.IRType(t);
		} 
	}
	
	private void printIRAssignment(IRAssignment ir){
		Type t = ir.result.type;
		int n1 = ir.result.n;
		int n2 = ir.var.n;
		char c = 'a';
		if (t instanceof IntegerType){
			c = 'i';
		} else if (t instanceof FloatType) {
			c = 'f';
		} else if (t instanceof CharType){
			c = 'i';
		} 
		System.out.println("\t" + c + "load " + n2);
		System.out.println("\t" + c + "store " + n1);
	}
	private void printIRExpressionAssignment(IRExpressionAssignment ir){
		Type t = ir.result.type;
		int n = ir.result.n;
		Expression e = ir.e;
		
		char c = JType(t);
		String exp = e.toString();
		if (t instanceof CharType){
			exp = "" + (int)exp.charAt(1);
		} 

		System.out.println("\tldc " + exp);
		System.out.println("\t" + c + "store " + n);
	}

	private void printIRReturnStatement(IRReturnStatement ir){
		Temp tmp = ir.t;
		if (tmp == null){
			System.out.println("\treturn");
			return;
		}
		Type t = tmp.type;		
		int name = tmp.n;		
		char prefix = JType(t);
		System.out.println("\t" + prefix + "load " + name);
		System.out.println("\t" + prefix + "return");
	}
	private void printIRInstruction(IRInstruction ir){
		if (ir instanceof IRExpressionAssignment){
			printIRExpressionAssignment((IRExpressionAssignment) ir);
		} else if (ir instanceof IRAssignment){
			printIRAssignment((IRAssignment) ir);
		} else if (ir instanceof IRReturnStatement){
			printIRReturnStatement((IRReturnStatement) ir);
		}
	}

	private void printTempEntry(TempEntry tmp){
		String name = tmp.name;
		Temp temp = tmp.t;
		System.out.println("\t.var " + temp.n + " is " + name + " " + VarType(temp.type) + " from L_0 to L_1");
	}

	private void printIRFunction(IRFunction f){
		Vector<IRInstruction> instructions = f.instructions;
		TempList temps = f.temps;
		int numTemps = temps.numTemps();
		String functionName = f.name;
		String signature = f.jSignature;

		if (functionName.equals("main")){
			functionName = "__main__";
		}	
		System.out.println(".method public static " + functionName + signature);
		System.out.println("\t.limit locals " + numTemps);

		TempEntry tmp;
		for (int i = 0; i < numTemps; i++){
			tmp = temps.get(i);
			printTempEntry(tmp);
		}
		System.out.println("\t.limit stack 16"); //16 is arbitrary
		System.out.println("L_" + label++ + ":");
				IRInstruction ir;
		for (int i = 0; i < instructions.size(); i++){
			ir = (IRInstruction)instructions.get(i);
			printIRInstruction(ir);
		}
		System.out.println("L_" + label++ + ":");
		System.out.println(".end method");
		System.out.println();
	}

	private void printIRProgramHeader(){
		String programName = prog.name;
		System.out.println(".source " + programName + ".ir");
		System.out.println(".class public " + programName);
		System.out.println(".super java/lang/Object");
		System.out.println();
	}
	
	private void printIRProgramBoilerplate(){
		System.out.println("; BOILERPLATE ;");
		System.out.println();
		System.out.println(".method public static main([Ljava/lang/String;)V");
		System.out.println("\t.limit locals 1");
		System.out.println("\t.limit stack 4");
		System.out.println("\tinvokestatic test/__main__()V");
		System.out.println("\treturn");
		System.out.println(".end method");
		System.out.println();
		System.out.println(".method public <init>()V");
		System.out.println("\taload_0");
		System.out.println("\tinvokenonvirtual java/lang/Object/<init>()V");
		System.out.println("\treturn");
		System.out.println(".end method");
	}

	public void print(){
		Vector<IRFunction> functions = prog.functions;
		String programName = prog.name;
		printIRProgramHeader();
		IRFunction f;
		for (int i = 0; i < functions.size(); i++){
			f = (IRFunction)functions.get(i);
			printIRFunction(f);
		};
		printIRProgramBoilerplate();
	}
		
}
