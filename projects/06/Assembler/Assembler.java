
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Assembler {

	public static void main(String[] args) throws Exception {
		String fileName = args[0];
		Parser ps = new Parser(fileName);

		ps.firstRound();
		System.out.println("First Round");
		
		String output = ps.secondRound();
		System.out.println("Second Round");
		
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
