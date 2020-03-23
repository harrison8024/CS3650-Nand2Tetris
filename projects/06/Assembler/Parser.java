import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Parser {
	public final ArrayList<String> instructions = new ArrayList<String>();
	public SymbolTable table;
	public Code code = new Code();
	public int current = 0;
	public int pc = -1;
	
	public Parser(String fileName) {
		String filePath = "../" + fileName + ".asm";
		String temp;
		try(BufferedReader br =  new BufferedReader(new FileReader(filePath))){
			while((temp = br.readLine()) != null) {
				instructions.add(temp);
			}	
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		table = new SymbolTable();
	}
	
	public void firstRound() throws Exception {
		advance(true);
	}
	
	public String secondRound() throws Exception {
		current = 0;
		return advance(false);
	}
	
	public Boolean hasMoreCommands() {
		//Check if there is another command
		return current < instructions.size();
	}
	
	public String advance(boolean isFirst) throws Exception {
		// reads next command
		String currentCommand;
		char type;
		String output = "";
		while (hasMoreCommands()) {
			currentCommand = instructions.get(current).trim();
			currentCommand = currentCommand.replaceAll("\\/\\/.*", "").trim();
			if(currentCommand.isEmpty()) {
				current++;
				continue;
			}
			type = commandType(currentCommand);
			
			// isFirst will only add entry
			switch(type) {
				case 'A':
					if(!isFirst) {
						String newSymbol = symbol(currentCommand, type);
						String binary = "";
						if(!isNumeric(newSymbol)) {
							if(table.contains(newSymbol)) {
								binary = intToBinary(table.getAddress(newSymbol));
							} else {
								binary = intToBinary(table.ramAddress);
								table.addEntry(binary, table.ramAddress++);
							}
						} else {
							binary = intToBinary(Integer.parseInt(newSymbol));
						}
						output += binary + "\r\n";
					} else {
						pc++;
					}
					break;
				case 'L':
					if(isFirst) {
						String newSymbol = symbol(currentCommand, type);
						table.addEntry(newSymbol, pc+1);
					}
					break;
				case 'C':
					if(!isFirst) {
						String d = dest(currentCommand);
						String c = comp(currentCommand);
						String j = jump(currentCommand);
						output += "111" + c + d + j + "\r\n";

					} else {
						pc++;
					}
					
			}
			current++;
			System.out.println("output:" + output);
		}
		return output;
	}
	
	public char commandType(String instruction) {
		// returns the current command type
		if(instruction.charAt(0) == '@') {
			return 'A';
		} else if(instruction.charAt(0) == '(') {
			return 'L';
		} else {
			return 'C';
		}
	}
	
	public String symbol(String instruction, char type) {
		//returns the symbol or decimal
		if(type == 'A') {
			return instruction.substring(1);
		} else if(type == 'L') {
			return instruction.replaceFirst("/^\\((.+)\\)$/" , "$1");
		}
		return "";
	}
	
	public String dest(String command) {
		// returns the dest mnemonic
		return code.dest(command);
	
	}
	
	public String comp(String command) throws Exception {
		//returns the comp menmonic
		return code.comp(command);
	}
	
	public String jump(String command) {
		//returns jump menmonic
		return code.jump(command);
	}
	
	public String intToBinary(int num) {
		String str = Integer.toString(num, 2);
		while(str.length() != 16) {
			str = "0" + str;
		}
		return str;
	}
	
	public boolean isNumeric(String strNum) {
	    if (strNum == null) {
	        return false;
	    }
	    try {
	        Integer.parseInt(strNum);
	    } catch (NumberFormatException nfe) {
	        return false;
	    }
	    return true;
	}
}

