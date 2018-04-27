

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileOperator {
	
	private final int xuser = 943;
	private final int xitem = 1682;
	private static final String filename="train_all_txt.txt";
	private static final String outfile="output.txt";
	private BufferedReader bufferedReader;
	
	public FileOperator() {
		
		try {
			this.bufferedReader = new BufferedReader(new FileReader(filename));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	
	public synchronized String readLine() throws IOException{
		
		String line=null;
		
		try {
			if ((line = bufferedReader.readLine()) != null) {
				return line;
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		
		return line;
	}
	
	
	
	
	
	
	
	public int[][] getData( ) {
		 
		
		int[][] matrix =new int[xuser+1][xitem+1];
		String rl=null;

		try {
			while ((rl = readLine()) != null) {
				String[] mdata = new String[3];
				mdata = rl.split("\\s+");
				matrix[Integer.parseInt(mdata[0])][Integer.parseInt(mdata[1])] = Integer.parseInt(mdata[2]);
				//System.out.println(data[0]);

			}
			
			
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return matrix;
	
		
		
	}
	
	
	public void outData(int[][] matrix) {
	
		try (FileWriter locFile = new FileWriter(outfile)) {
			System.out.println("Writting to File...");
			for(int i = 1; i <= xuser; i++) {
				for(int j = 1; j <= xitem; j++) {			    
					locFile.write(i + " " + j + " " + matrix[i][j] + "\n");
				}
			}
			
		} catch (IOException e) {
			System.err.println("Unable to write to File");
			System.exit(-1);
		} finally {
			System.out.println("\n File Write complete...");
		}
		
	}

	
	
	public void close() {
		

		try {
			bufferedReader.close();
		} catch (IOException e) {
			System.err.println("Closing error");
			e.printStackTrace();
		}
	
		
	}
	
	
}
