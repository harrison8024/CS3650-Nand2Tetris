
public class Parser {
	private int A_COMMAND = 0;
	private int C_COMMAND = 1;
	private int L_COMMAND = 2;
	
	
	public Parser(String[] commands) {

	}
	
	public Boolean hasMoreCommnads() {
		//Check if there is another command
		return null;
	}
	
	public void advance() {
		// reads next command
		return;
	}
	
	public int commandType() {
		// returns the current command type
		return 0;
	}
	
	public String symbol() {
		//returns the symbol or decimal
		return null;
	}
	
	public String dest() {
		// returns the dest mnemonic
		return null;
	}
	
	public String comp() {
		//returns the comp menmonic
		return null;
	}
	
	public String jump() {
		//returns jump menmonic
		return null;
	}
}

