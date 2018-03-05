package AST;
import Temp.*;
import Visitor.*;
public class SemanticException extends RuntimeException{

	public String msg;	
		
	public SemanticException(){

	}
	public SemanticException(String msg, int lineNumber, int offset){
		this.msg = "Error:" + lineNumber + ": " + msg + " (" + lineNumber + ", " + offset + ")";	
	}
	public String getMessage(){
		return(msg);
	}
	public String toString(){
		return(msg);
	}
}
