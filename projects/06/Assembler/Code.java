import java.util.Hashtable;
import java.lang.StringBuilder;

public class Code {
	
	public Hashtable<String, String> jumpSet;
	public Hashtable<String, String> compSet;
	
	public Code() {
		jumpSet = new Hashtable<String, String>();
		compSet = new Hashtable<String, String>();
		
		//jump set
		jumpSet.put("JGT", "001");
		jumpSet.put("JEQ", "010");
		jumpSet.put("JGE", "011");
		jumpSet.put("JLT", "100");
		jumpSet.put("JNE", "101");
		jumpSet.put("JLE", "110");
		jumpSet.put("JMP", "111");
		
		//comp set
		compSet.put("0", "0101010");
		compSet.put("1", "0111111");
		compSet.put("-1", "0111010");
		compSet.put("D", "0001100");
		compSet.put("A", "0110000");
		compSet.put("M", "1110000");
		compSet.put("!D", "0001101");
		compSet.put("!A", "0110001");
		compSet.put("!M", "1110001");
		compSet.put("-D", "0001111");
		compSet.put("-A", "0110011");
		compSet.put("-M", "1110011");
		compSet.put("D+1", "0011111");
		compSet.put("A+1", "0110111");
		compSet.put("M+1", "1110111");
		compSet.put("D-1", "0001110");
		compSet.put("A-1", "0110010");
		compSet.put("M-1", "1110010");
		compSet.put("D+A", "0000010");
		compSet.put("D+M", "1000010");
		compSet.put("D-A", "0010011");
		compSet.put("D-M", "1010011");
		compSet.put("A-D", "0000111");
		compSet.put("M-D", "1000111");
		compSet.put("D&A", "0000000");
		compSet.put("D&M", "1000000");
		compSet.put("D|A", "0010101");
		compSet.put("D|M", "1010101");
		
	}
	
	public String dest(String mnemonic) {
		//returns the binary code of dest
		if(mnemonic.contains("=")) {
			String dest = mnemonic.split("=")[0].trim();
			char[] destChar = dest.toCharArray();
			String[] tempArr = new String[] {"0", "0", "0"};
			for(Character key : destChar) {
				switch(key) {
					case 'A':
						tempArr[0] = "1";
						break;
					case 'D':
						tempArr[1] = "1";
						break;
					case 'M':
						tempArr[2] = "1";
						break;
				}
			}
			return String.join("", tempArr);
		}
		return "000";
	}
	
	public String comp(String mnemonic) throws Exception {
		//returns the binary code of comp
		String comp = mnemonic;
		if(comp.contains("=")) {
			comp = comp.split("=")[1].trim();
		}
		if(comp.contains(";")) {
			comp = comp.split(";")[0].trim();
		}
		comp = comp.replaceAll(" ", "");
		if(compSet.containsKey(comp)) {
			return compSet.get(comp);
		} else if(comp.contains("+") || comp.contains("|") || comp.contains("&")){
			comp = new StringBuilder(comp).reverse().toString();
			if(compSet.containsKey(comp)) {
				return compSet.get(comp);
			}
		}
		throw new Exception("Illegal instruction");
	}
	
	public String jump(String mnemonic) {
		//returns the binary code of jump
		if(mnemonic.contains(";")) {
			String jump = mnemonic.split(";")[1].trim();
			return jumpSet.get(jump);
		}
		return "000";
	}
}
