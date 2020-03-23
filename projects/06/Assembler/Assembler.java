
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Assembler {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		String fileName = args[0];
		Parser ps = new Parser(fileName);

		ps.firstRound();
		
		String output = ps.secondRound();
		
		String filepath = "../" + fileName + ".hack";
		
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(filepath));
			bw.write(output);
			bw.close();
		} catch(IOException e){
			throw new IOException("Cannot write to file: " + filepath);
		}
	}

}
