import java.sql.Statement;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InsertZipcodeEx01 {

	public static void main(String[] args) {
	
		BufferedReader br = null;
		BufferedWriter bw = null;

		try {
			String sentence = null;
			br = new BufferedReader(new FileReader("./zipcode_seoul_utf8_type2.csv"));
			bw = new BufferedWriter(new FileWriter("./zipcode_seoul_utf8_type2.sql"));
			
			while((sentence=br.readLine()) !=null) {
				String[] adrs = sentence.split(","); 
				String sql = String.format("insert into zipcode values ( '%s', '%s', '%s', '%s', '%s', '%s', %s);", adrs[0], adrs[1], adrs[2], adrs[3], adrs[4], adrs[5], adrs[6]);
				//System.out.println( sql );
				bw.write(sql);
				bw.newLine();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(br!=null) try {br.close();} catch(IOException e) {}
			if(bw!=null) try {bw.close();} catch(IOException e) {}	
		}
	
		System.out.println("끝");
	}
}

