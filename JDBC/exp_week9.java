import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Collections;

public class exp_week9{
	public static void main(String[] args)
	{
		String sql_create = "create table record2 (depart_id int, plan_id int, workload int, primary key (depart_id));";
		String sql_insert = "insert into record2 values (1,2,3), (2,5,2);";
		String sql_delete = "delete from record2;";
		String sql_insert2 = "insert into record2 (select id, floor(random()*499+1), floor(random()*49+1) from generate_series(1,3000) as t(id));";
		String sql_query2 = "select * from max_avg_workload();";
		String sql_query = "select plan_id, avg(workload) as avg_workload from record2 group by plan_id;";

		try{
			Class.forName("org.postgresql.Driver");
			Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Emgyplan", "postgres", "980126");
			System.out.println("Connection success;");

			Statement stmt = con.createStatement();
			ResultSet re = stmt.executeQuery(sql_query);
			ArrayList<Integer> list_plan_id = new ArrayList<Integer>();
			ArrayList<Float> list_avg_workload = new ArrayList<Float>();
			
			int id;
			float avg_workload;
			float max_avg_workload = 0;
			while (re.next())
			{
				id = re.getInt("plan_id");
				list_plan_id.add(id);
				avg_workload = re.getFloat("avg_workload");
				list_avg_workload.add(avg_workload);
				// System.out.println("plan_id:"+id+"\tworkload:"+avg_workload);

				if (avg_workload >= max_avg_workload)
				{
					max_avg_workload = avg_workload;
				}
					
			}
			
			System.out.println("Result:");
			for (int i=0;i<list_plan_id.size();i++)
			{
				if (list_avg_workload.get(i) == max_avg_workload)
					System.out.println("plan_id:"+list_plan_id.get(i)+"\tworkload:"+list_avg_workload.get(i));
			}

			// System.out.println("Table record2 creation success;");
			// System.out.println("Table record2 insertion success;");
			// System.out.println("Table record2 delete success;");


			// Statement stmt = con.createStatement();
			// ResultSet re = stmt.executeQuery(sql_query2);
			// System.out.println("Result:");
			// int id;
			// float avg_workload;
			// while(re.next())
			// {
			// 	id = re.getInt("plan_id2");
			// 	avg_workload = re.getFloat("avg_workload2");
			// 	System.out.println("plan_id:"+id+"\tavg_workload:"+avg_workload);
			// }







			


			con.close();

		}
		catch (Exception e){
			e.printStackTrace();
		}
	}
}
