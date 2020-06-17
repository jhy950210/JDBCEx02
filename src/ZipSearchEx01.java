import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ZipSearchEx01 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		BufferedReader br = null;
        
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
        
		String url = "jdbc:mysql://localhost:3306/project";
		String user = "root";
		String password = "123456";
        
		// 입력값 검사
		String strDong = "";
        
		try {
			br = new BufferedReader( new InputStreamReader( System.in ) );
            
			while( true ) {
				System.out.print( "동이름을 입력해주세요(2자 이상) : " );
				strDong = br.readLine();
				if( strDong.length() < 2 ) {
					System.out.println( "잘못 입력되었습니다. 다시 입력해주세요" );
				} else {
					if( strDong.charAt( 0 )<0xAC00 || strDong.charAt( 0 )>0xD7A3 ) {
						System.out.println( "동이름의 첫자가 한글로 입력되어야 합니다. 다시 입력해주세요" );
					} else {
						break;
					}
				}
				System.out.println();
			}
		} catch( IOException e ) {
			// TODO Auto-generated catch block
			System.out.println( e.getMessage() );
		} finally {
			if( br != null ) try { br.close(); } catch( IOException e ) {}
		}

		try {
			Class.forName( "org.mariadb.jdbc.Driver" );
			conn = DriverManager.getConnection( url, user, password );
			stmt = conn.createStatement();

			String sql = "select * from zipcode where dong like '%" + strDong + "%'";
			rs = stmt.executeQuery( sql );
			while( rs.next() ) {
				System.out.printf( "[%s] %s %s %s %s %s%n", rs.getString( "zipcode" ), rs.getString( "sido" ), rs.getString( "gugun" ), rs.getString( "dong" ), rs.getString( "ri" ), rs.getString( "bunji" ) );
			}
		} catch( ClassNotFoundException e ) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch( SQLException e ) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if( rs != null ) try { rs.close(); } catch( SQLException e ) {}
			if( stmt != null ) try { stmt.close(); } catch( SQLException e ) {}
			if( conn != null ) try { conn.close(); } catch( SQLException e ) {}
		}
	}

}
