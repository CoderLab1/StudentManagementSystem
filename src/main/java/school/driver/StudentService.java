package school.driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import school.entity.Student;

public class StudentService {
	   private static String url = "jdbc:postgresql://localhost:5432/school?user=postgres&password=123";
	   private static Connection con;
	   
	   static {
		   try {
			Class.forName("org.postgresql.Driver");
			con = DriverManager.getConnection(url);
			System.out.println("DataBase Connected");
		   } catch (ClassNotFoundException e) {
			e.printStackTrace();
		   } catch (SQLException e) {
			e.printStackTrace();
		}
	   }
	   
	   public int save(Student st) {
		   	int res=0;
		   	
		   	String sql = "Insert into student values(?,?,?)";
		   	try (PreparedStatement pstm = con.prepareStatement(sql)) {
				
		   		pstm.setInt(1, st.getId());
		   		pstm.setString(2, st.getName());
		   		pstm.setInt(3, st.getAge());
		   		
		   		res= pstm.executeUpdate();
		   		
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		   	
		   	
		   	return res;
	   }
	   
	   
}
