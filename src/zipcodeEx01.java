import java.sql.Statement;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class zipcodeEx01 {

	public static void main(String[] args) {
		System.out.println("시작");
		
		String url = "jdbc:mysql://localhost:3306/project";
		String user = "root";
		String password = "123456";
		
		Connection conn = null;
		PreparedStatement pstmt = null;

		BufferedReader br = null;
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			System.out.println("드라이버 로딩 완료");
			
			conn = DriverManager.getConnection(url, user, password);
			System.out.println("데이터베이스 연결 완료");
			
			br = new BufferedReader(new FileReader("./zipcode_seoul_utf8_type2.csv"));
			String sql = "insert into zipcode values (?, ?, ?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			
			String msg = null;
			
			while((msg=br.readLine()) !=null) {
				String[] adrs = msg.split(","); 
				for(int i=0; i < adrs.length; i++) {
					pstmt.setString(i+1, adrs[i] );
				}
				pstmt.executeUpdate();
			}
			
			
			System.out.println("입력성공");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();	
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("[에러]"+e.getMessage());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("[에러]"+e.getMessage());
		} finally {
			//if(rs!=null) try {rs.close();} catch(SQLException e) {}
			if(pstmt!=null) try {pstmt.close();} catch(SQLException e) {}
			if(conn!=null) try {conn.close();} catch(SQLException e) {}
			if(br!=null) try {br.close();} catch(IOException e) {}
		}
		System.out.println("끝");
	
}
}
