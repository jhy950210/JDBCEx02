import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.regex.Pattern;

public class DBSearchEx01 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// 1. 데이터베이스 읽어오기
		// prepareStatement - select 쿼리문
		// scanner 
		
		System.out.println("시작");
		
		Scanner scan = new Scanner(System.in);
		
		String url = "jdbc:mysql://localhost:3306/project";
		String user = "root";
		String password = "123456";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection(url, user, password);
			
			String sql = "select * from zipcode where dong like ?";
			pstmt = conn.prepareStatement(sql);
			//
		
			System.out.print("동 이름 > ");
			String dongName = scan.nextLine();
			
			while(!dongName.equals("exit")) {
				if(!Pattern.matches("^[ㄱ-ㅎ가-힣]*$", dongName)) {
					System.out.println("한글을 입력하세요.");
				} else if(dongName.length()<2) {
					System.out.println("2자 이상 입력하세요.");
				} else {
				
					pstmt.setString(1, dongName + "%");
					rs = pstmt.executeQuery();
					
					while(rs.next()) {
						System.out.print(rs.getString("zipcode") + "\t");
						System.out.print(rs.getString("sido") + "\t");
						System.out.print(rs.getString("gugun") + "\t");
						System.out.print(rs.getString("dong") + "\t");
						System.out.print(rs.getString("ri") + "\t");
						System.out.print(rs.getString("bunji") + "\t");
						System.out.println(rs.getString("seq"));
					}
				}
				System.out.print("동 이름 > ");
				dongName = scan.nextLine();
			}
			
			scan.close();
			System.out.println("끝");
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("[에러] " + e.getMessage());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("[에러] " + e.getMessage());
		} finally {
			if(rs!=null) try {rs.close();} catch(SQLException e) {}
			if(pstmt!=null) try {pstmt.close();} catch(SQLException e) {}
			if(conn!=null) try {conn.close();} catch(SQLException e) {}
		}
	
	}

}
