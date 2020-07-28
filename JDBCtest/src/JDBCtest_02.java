import java.sql.*;
import java.io.*;

public class JDBCtest_02{
	public static void main(String[] args) throws IOException
	{
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch(ClassNotFoundException ee)
		{
			System.err.println("error = " + ee.getMessage());
			System.exit(0);
		}
		Connection conn = null;
		String url = "jdbc:mysql://localhost/academicDB?serverTimezone=UTC";
		String id = "root";
		String pass = "twinsangel1996^^";
		Statement stmt = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "insert into test_02 value ( null, ?, ? )";
		
		try {
			conn = DriverManager.getConnection(url,id,pass);
			stmt = conn.createStatement();
			
			//			rs = stmt.executeQuery(query); //?이용한 query문은 prepare statement만 된다.
			pstmt = conn.prepareStatement(query);
		}
			catch(SQLException ee){
				System.err.println("error = " + ee.toString());
				System.exit(0);
			}
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		while(true)
		{
			System.out.print("1.입력  2.출력  3.종료 = ");
			int x = System.in.read() - 48;
			System.in.read();
			System.in.read();
			if(x == 1)
			{
				System.out.print("이 름 : ");
				String name = in.readLine();
				System.out.print("주민번호 : ");
				String jumin = in.readLine();
				try {
//					rs.updateString(1, name);
//					rs.updateString(2, jumin);
					
					pstmt.setString(1, name);
					pstmt.setString(2, jumin);
					pstmt.executeUpdate();
				}catch(SQLException ee){
					System.err.println("error = " + ee.toString());
					System.exit(0);					
				}
			}
			else if(x==2)
			{
				try {
					rs = stmt.executeQuery("select * from test_02");
					while(rs.next())
					{
						System.out.println(rs.getInt("num") + " : " + rs.getString("name") +", "+rs.getString("jumin"));
					}
					rs.close();
				}catch(SQLException ee){	
				}
			}
			else if (x==3) break;
			else System.err.println("잘못 입력!!");
		}
		try {
			conn.close();
		}catch(Exception ee)
		{
		
		}
	}
}