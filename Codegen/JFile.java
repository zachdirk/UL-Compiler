package Codegen;
import IR.*;
import IR.Temp.*;
import Type.*;
import AST.*;
import java.util.Vector;
public class JFile {

	public IRProgram prog;
	public int label;

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
			return "[" + VarType(t2.t);
		} else {
			return Temp.IRType(t);
		} 
	}
	
	private void printIRAssignment(IRAssignment ir){
		Type t = ir.result.type;
		int n1 = ir.result.n;
		int n2 = ir.var.n;
		char c = JType(t);
		System.out.println("\t" + c + "load " + n2);
		System.out.println("\t" + c + "store " + n1);
	}
	private void printIRCallAssignment(IRCallAssignment ir){
		printIRCallStatement(ir.func);
		Temp result = ir.result;
		System.out.println("\t" + JType(result.type) + "store " + result.n);
	}
	private void printIRExpressionAssignment(IRExpressionAssignment ir){
		Type t = ir.result.type;
		int n = ir.result.n;
		Expression e = ir.e;
		
		char c = JType(t);
		String exp = e.toString();
		if (t instanceof CharType){
			exp = "" + (int)exp.charAt(1);
		} else if (t instanceof BooleanType){
			if (exp.equals("TRUE"))
				exp = "1";
			else
				exp = "0";
		}
		System.out.println("\tldc " + exp);
		System.out.println("\t" + c + "store " + n);
	}
	
	private void printIRArrayAssignment(IRArrayAssignment ir){
		Temp arr = ir.arr;
		Temp index = ir.index;
		Temp expr = ir.expr;
		Type t = expr.type;
		char c = JType(t);
		System.out.println("\taload " + arr.n);
		System.out.println("\tiload " + index.n);
		System.out.println("\t" + c + "load " + expr.n);
		if (t instanceof CharType)
			System.out.println("\tcastore");
		else if (t instanceof BooleanType)
			System.out.println("\tbastore");
		else
			System.out.println("\t" + c + "astore");
		
	}

	private void printIRArrayDeclaration(IRArrayDeclaration ir){
		Temp var = ir.var;
		ArrayType at = (ArrayType)var.type;
		Type t = at.t;
		int size = ir.size;
		System.out.println("\tldc " + size);
		if (t instanceof IntegerType)
			System.out.println("\tnewarray int");
		else if (t instanceof BooleanType)
			System.out.println("\tnewarray boolean");
		else if (t instanceof CharType)
			System.out.println("\tnewarray char");
		else if (t instanceof FloatType)
			System.out.println("\tnewarray float");
		else if (t instanceof StringType)
			System.out.println("\tanewarray java/lang/String");
		System.out.println("\tastore " + var.n);
	}

	private void printIRArrayReference(IRArrayReference ir){
		Temp arr = ir.arr;
		Temp index = ir.index;
		Temp result = ir.result;
		Type t = result.type;
		char c = JType(t);
		System.out.println("\taload " + arr.n);
		System.out.println("\tiload " + index.n);
		if (t instanceof CharType)
			System.out.println("\tcaload");
		else if (t instanceof BooleanType)
			System.out.println("\tbaload");
		else
			System.out.println("\t" + c + "aload ");
		System.out.println("\t" + c + "store " + result.n);
	}

	private void printIRLabel(IRLabel ir){
		System.out.println("L" + ir.label + ":");
	}
	private void printIRCallStatement(IRCallStatement ir){
		String name = ir.func;
		Vector<Temp> params = ir.params;
		Type ret = ir.returnType;
		char c;	
		Temp tmp;
		String sig = "";
		for (int i = 0; i < params.size(); i++){
			tmp = (Temp)params.get(i);
			c = JType(tmp.type);
			sig = sig + VarType(tmp.type);
			System.out.println("\t" + c + "load " + tmp.n);
		}
		System.out.println("\tinvokestatic " + prog.name + "/" + name + "(" + sig + ")" + VarType(ret));
	}
	private void printIRIFStatement(IRIFStatement ir){
		IRLabel l = ir.l;		
		Temp tmp = ir.t;
		Type t = tmp.type;
		char c = JType(t);		
		int n = tmp.n;		
		int label = l.label;

		System.out.println("\t" + c + "load " + n);
		System.out.println("\tifne L" + label);
		
	}
	private void printIRGOTOStatement(IRGOTOStatement ir){
		IRLabel l = ir.l;
		System.out.println("\tgoto L" + l.label);
	}	
	private void printIRPrintStatement(IRPrintStatement ir){
		Temp expr = ir.expr;
		Type t = expr.type;
		int n = expr.n;
		char prefix = JType(t);
		String type = VarType(t);
		System.out.println("\tgetstatic java/lang/System/out Ljava/io/PrintStream;");
		System.out.println("\t" + prefix + "load " + n);
		System.out.println("\tinvokevirtual java/io/PrintStream/print(" + type + ")V");
	}
	private void printIRPrintLnStatement(IRPrintLnStatement ir){
		Temp expr = ir.expr;
		Type t = expr.type;
		int n = expr.n;
		char prefix = JType(t);
		String type = VarType(t);
		System.out.println("\tgetstatic java/lang/System/out Ljava/io/PrintStream;");
		System.out.println("\t" + prefix + "load " + n);
		System.out.println("\tinvokevirtual java/io/PrintStream/println(" + type + ")V");
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
	private void printIRConversion(IRConversion ir){
		Temp var = ir.var;
		Temp result = ir.result;
		Type from = ir.from;
		Type to = ir.to;
		char c1 = JType(from);
		char c2 = JType(to);
		System.out.println("\t" + c1 + "load " + var.n);
		if (to instanceof StringType){
			if (from instanceof IntegerType) {
				System.out.println("\tinvokestatic java/lang/Integer/toString(I)Ljava/lang/String;");
			} else if (from instanceof FloatType) {
				System.out.println("\tinvokestatic java/lang/Float/toString(F)Ljava/lang/String;");
			} else if (from instanceof BooleanType) {
				System.out.println("\tinvokestatic java/lang/Boolean/toString(Z)Ljava/lang/String;");
			} else if (from instanceof CharType) {
				System.out.println("\tinvokestatic java/lang/Character/toString(C)Ljava/lang/String;");
			}
		} else {
			System.out.println("\t" + c1 + "2" + c2);
		}
		System.out.println("\t" + c2 + "store " + result.n);
	}

	private void printIRBinaryOP(IRBinaryOp ir){
		Temp result = ir.result;
		Temp left = ir.left;
		Temp right = ir.right;
		IRBinaryEnum op = ir.op;
		char prefix = JType(result.type);
		int first = label++;
		int second = label++;
		if (left.type instanceof StringType){
			switch(op){
				case ADD:
					System.out.println("\tnew java/lang/StringBuffer");
					System.out.println("\tdup");
					System.out.println("\tinvokenonvirtual java/lang/StringBuffer/<init>()V");
					System.out.println("\taload " + left.n);
					System.out.println("\tinvokevirtual java/lang/StringBuffer/append(Ljava/lang/String;)Ljava/lang/StringBuffer;");
					System.out.println("\taload " + right.n);
					System.out.println("\tinvokevirtual java/lang/StringBuffer/append(Ljava/lang/String;)Ljava/lang/StringBuffer;");
					System.out.println("\tinvokevirtual java/lang/StringBuffer/toString()Ljava/lang/String;");
					break;
				case LESS:
					System.out.println("\taload " + left.n);
					System.out.println("\taload " + right.n);
					System.out.println("\tinvokevirtual java/lang/String/compareTo(Ljava/lang/String;)I");
					System.out.println("\tiflt L_" + first);
					System.out.println("\tldc 0");
					System.out.println("\tgoto L_" + second);
					System.out.println("L_" + first + ":");
					System.out.println("\tldc 1");
					System.out.println("L_" + second + ":");
					break;
				case EQUALS:
					System.out.println("\taload " + left.n);
					System.out.println("\taload " + right.n);
					System.out.println("\tinvokevirtual java/lang/String/compareTo(Ljava/lang/String;)I");
					System.out.println("\tifeq L_" + first);
					System.out.println("\tldc 0");
					System.out.println("\tgoto L_" + second);
					System.out.println("L_" + first + ":");
					System.out.println("\tldc 1");
					System.out.println("L_" + second + ":");
					break;
			}	
		} else {
			System.out.println("\t" + prefix + "load " + left.n);
			System.out.println("\t" + prefix + "load " + right.n);		
			switch(op){
				case ADD:
					System.out.println("\t" + prefix + "add");				
					break;
				case MULTIPLY:
					System.out.println("\t" + prefix + "mul");
					break;
				case SUBTRACT:
					System.out.println("\t" + prefix + "sub");
					break;
				case LESS:
					first = label++;
					second = label++;
					System.out.println("\t" + prefix + "sub");
					System.out.println("\tiflt L_" + first);
					System.out.println("\tldc 0");
					System.out.println("\tgoto L_" + second);
					System.out.println("L_" + first + ":");
					System.out.println("\tldc 1");
					System.out.println("L_" + second + ":");
					break;
				case EQUALS:
					first = label++;
					second = label++;
					System.out.println("\t" + prefix + "sub");
					System.out.println("\tifeq L_" + first);
					System.out.println("\tldc 0");
					System.out.println("\tgoto L_" + second);
					System.out.println("L_" + first + ":");
					System.out.println("\tldc 1");
					System.out.println("L_" + second + ":");
					break;
			}
		}
		System.out.println("\t" + prefix + "store " + result.n);
	}
	private void printIRUnaryOp(IRUnaryOp ir){
		Temp result = ir.result;
		Temp operand = ir.operand;
		IRUnaryEnum op = ir.op;
		switch(op){
			case NUMNEGATE:
				//I don't think the unnamed language actually uses this
				break;
			case BOOLNEGATE:		
				System.out.println("\tiload " + operand.n);
				System.out.println("\tldc 1");
				System.out.println("\tixor");
				System.out.println("\tistore " + result.n);
				break;
		}
	}
	private void printIRInstruction(IRInstruction ir){
		if (ir instanceof IRExpressionAssignment){
			printIRExpressionAssignment((IRExpressionAssignment) ir);
		} else if (ir instanceof IRAssignment){
			printIRAssignment((IRAssignment) ir);
		} else if (ir instanceof IRReturnStatement){
			printIRReturnStatement((IRReturnStatement) ir);
		} else if (ir instanceof IRLabel){
			printIRLabel((IRLabel) ir);
		} else if (ir instanceof IRGOTOStatement){
			printIRGOTOStatement((IRGOTOStatement) ir);
		} else if (ir instanceof IRPrintStatement){
			printIRPrintStatement((IRPrintStatement) ir);
		} else if (ir instanceof IRPrintLnStatement){
			printIRPrintLnStatement((IRPrintLnStatement) ir);
		} else if (ir instanceof IRUnaryOp){
			printIRUnaryOp((IRUnaryOp) ir);
		} else if (ir instanceof IRBinaryOp){
			printIRBinaryOP((IRBinaryOp) ir);
		} else if (ir instanceof IRConversion){
			printIRConversion((IRConversion) ir);
		} else if (ir instanceof IRIFStatement){
			printIRIFStatement((IRIFStatement) ir);
		} else if (ir instanceof IRCallAssignment){
			printIRCallAssignment((IRCallAssignment) ir);
		} else if (ir instanceof IRCallStatement){
			printIRCallStatement((IRCallStatement) ir);
		} else if (ir instanceof IRArrayDeclaration){
			printIRArrayDeclaration((IRArrayDeclaration) ir);
		} else if (ir instanceof IRArrayReference){
			printIRArrayReference((IRArrayReference) ir);
		} else if (ir instanceof IRArrayAssignment){
			printIRArrayAssignment((IRArrayAssignment) ir);
		}
	}



	private void printTempEntry(TempEntry tmp, int l1, int l2){
		String name = tmp.name;
		Temp temp = tmp.t;
		System.out.println("\t.var " + temp.n + " is " + name + " " + VarType(temp.type) + " from L_" + l1 + " to L_" + l2);
	}

	private void printIRFunction(IRFunction f){
		Vector<IRInstruction> instructions = f.instructions;
		TempList temps = f.temps;
		int numTemps = temps.numTemps();
		String functionName = f.name;
		String signature = f.jSignature;
		int l1 = label++;
		int l2 = label++;
		if (functionName.equals("main")){
			functionName = "__main__";
		}	
		System.out.println(".method public static " + functionName + signature);
		System.out.println("\t.limit locals " + numTemps);

		TempEntry tmp;
		for (int i = 0; i < numTemps; i++){
			tmp = temps.get(i);
			printTempEntry(tmp, l1, l2);
		}
		System.out.println("\t.limit stack 16"); //16 is arbitrary
		System.out.println("L_" + l1 + ":");
				IRInstruction ir;
		for (int i = 0; i < instructions.size(); i++){
			ir = (IRInstruction)instructions.get(i);
			printIRInstruction(ir);
		}
		System.out.println("L_" + l2 + ":");
		System.out.println(".end method");
		System.out.println();
	}

	private void printIRProgramHeader(){
		System.out.println(".source " + prog.name + ".ir");
		System.out.println(".class public " + prog.name);
		System.out.println(".super java/lang/Object");
		System.out.println();
	}
	
	private void printIRProgramBoilerplate(){
		System.out.println("; BOILERPLATE ;");
		System.out.println();
		System.out.println(".method public static main([Ljava/lang/String;)V");
		System.out.println("\t.limit locals 1");
		System.out.println("\t.limit stack 4");
		System.out.println("\tinvokestatic " + prog.name + "/__main__()V");
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
