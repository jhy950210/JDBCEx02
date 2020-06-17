import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertZipcodeEx02 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BufferedReader br = null;
		
		String url = "jdbc:mysql://localhost:3306/project";
		String user = "root";
		String password = "123456";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection(url, user, password);
			
			String sql = "insert into zipcode value(?, ?, ?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			
			br = new BufferedReader(new FileReader("./zipcode_seoul_utf8_type2.csv"));
			
			String sentence = null;
			int count = 0;
			while((sentence = br.readLine()) != null) {
				String[] addresses = sentence.split(",");
				
				pstmt.setString(1, addresses[0]);
				pstmt.setString(2, addresses[1]);
				pstmt.setString(3, addresses[2]);
				pstmt.setString(4, addresses[3]);
				pstmt.setString(5, addresses[4]);
				pstmt.setString(6, addresses[5]);
				pstmt.setString(7, addresses[6]);
				
				pstmt.executeUpdate();
				count++;
			}
			
			System.out.println("결과 : " + count);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("[에러] " + e.getMessage());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("[에러] " + e.getMessage());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("[에러] " + e.getMessage());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("[에러] " + e.getMessage());
		} finally {
			if(br!=null) try {br.close();} catch(IOException e) {}
			if(pstmt!=null) try {pstmt.close();} catch(SQLException e) {}
			if(conn!=null) try {conn.close();} catch(SQLException e) {}
		}
		
	}

}
