package test3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;

public class example {
	public static void main(String[] args) {
		String sql_create="create table eee(eeid int primary key,eeage int,dep_id int)";
		String sql_insert="insert into eee select num,floor(random()*10+15),floor(random()*30+1000) from generate_series(1,100) as t(num)";
		String sql_insert2="insert into eee values(?,?,?)";
		String sql_select="select * from eee where eeid<10";
		String sql_nest="select * from sele()";
		String sql_nonest="select dep_id,avg(eeage) from eee group by dep_id";

		
		try {
			Class.forName("org.postgresql.Driver");
			Connection con=DriverManager.getConnection
			("jdbc:postgresql://localhost:5432/test3","postgres","123456");
			//实际工作
//			Statement stmt=con.createStatement();
//	        stmt.executeUpdate(sql_insert);
//	        System.out.println("数据插入成功");	
//	        stmt.close();
			
//			PreparedStatement pstmt=con.prepareStatement(sql_insert2);
//			pstmt.setInt(1, 101);
//			pstmt.setInt(2, 20);
//			pstmt.setInt(3, 1001);
//			pstmt.executeUpdate();
//			pstmt.close();
			
			Statement stmt=con.createStatement();
	        ResultSet re=stmt.executeQuery(sql_nonest);
	        ArrayList<Integer> list1=new ArrayList<Integer>();
	        ArrayList<Float> list2=new ArrayList<Float>();
	        while(re.next())
	        {
		        int id=re.getInt("dep_id");
		        list1.add(id);
		        float age=re.getFloat("avg");
		        list2.add(age);
		        System.out.println("dep_id:"+id+"\tavg_age:"+age);
	        }
	        re.close();
	        stmt.close();
	        
	        float max_age=Collections.max(list2);
	        for(int i=0;i<list1.size();i++){
	        	if(list2.get(i)==max_age)
	        		System.out.println("dep_id:"+list1.get(i)+"\tmax_age:"+max_age);
	        }

			con.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	}

}
