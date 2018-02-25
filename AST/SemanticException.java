package AST;
public class SemanticException extends RuntimeException{

	String msg;	
		
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
