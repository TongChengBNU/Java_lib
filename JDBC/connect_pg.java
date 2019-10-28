import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;


public class connect_pg{
	public static void main(String[] args)
	{
		String sql_create = "create table eee(eeid int primary key, eeage int, dep_id int)";
		String sql_insert = "insert into eee select num, floor(random()*10+15), floor(random()*30+1000) from generate_series(1,100) as t(num)";
		String sql_insert2 = "insert into eee values(?,?,?)";
		String sql_select = "select * from eee where eeid<10";

		try{
			Class.forName("org.postgresql.Driver");
			Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/JDBC_test", "postgres", "980126");

			Statement stmt = con.createStatement();
			// stmt.executeUpdate(sql_create);
			// System.out.println("Creation success;");
			// stmt.executeUpdate(sql_insert);
			// System.out.println("Insertion success;");

			// PreparedStatement pstmt = con.prepareStatement(sql_insert2);
			// pstmt.setInt(1,101);
			// pstmt.setInt(2,20);
			// pstmt.setInt(3,1001);
			// pstmt.executeUpdate();
			// pstmt.close();
			// System.out.println("Insertion success;");

			ResultSet re = stmt.executeQuery(sql_select);
			while(re.next())
			{
				int id = re.getInt("eeid");
				int age = re.getInt("eeage");
				int did = re.getInt("dep_id");
				System.out.println("eeid:"+id+"\teeage:"+age+"\tdep_id:"+did);
			}
			re.close();
			stmt.close();

			con.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
