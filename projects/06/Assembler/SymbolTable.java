import java.util.Hashtable;

public class SymbolTable {
	public Hashtable<String, Integer> table;
	public int ramAddress = 16;
	public SymbolTable() {
		table = new Hashtable<String, Integer>();
		table.put("SP", 0);
		table.put("LCL", 1);
		table.put("ARG", 2);
		table.put("THIS", 3);
		table.put("THAT", 4);
		table.put("RO", 0);
		table.put("R1", 1);
		table.put("R2", 2);
		table.put("R3", 3);
		table.put("R4", 4);
		table.put("R5", 5);
		table.put("R6", 6);
		table.put("R7", 7);
		table.put("R8", 8);
		table.put("R9", 9);
		table.put("R10", 10);
		table.put("R11", 11);
		table.put("R12", 12);
		table.put("R13", 13);
		table.put("R14", 14);
		table.put("R15", 15);
		table.put("SCREEN", 16384);
		table.put("KBD", 24576);
		
	}
	
	public void addEntry(String symbol, int address) {
		// adds the pair(symbol, address) to table
		table.put(symbol, address);
	}
	
	public Boolean contains(String symbol) {
		// does symbol table contain the given symbol?
		return table.containsKey(symbol);
	}
	
	public int getAddress(String symbol) {
		// returns the address associated with the symbol
		return table.get(symbol);
	}
}
