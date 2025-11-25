package school.driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
		int res = 0;

		String sql = "Insert into student values(?,?,?)";
		try (PreparedStatement pstm = con.prepareStatement(sql)) {

			pstm.setInt(1, st.getId());
			pstm.setString(2, st.getName());
			pstm.setInt(3, st.getAge());

			res = pstm.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}

	public int update(Student stu) {
		int res = 0;

		String sql = "Update student set name=?,age=? where id=?";
		try (PreparedStatement pstm = con.prepareStatement(sql)) {
			pstm.setString(1, stu.getName());
			pstm.setInt(2, stu.getAge());
			pstm.setInt(3, stu.getId());

			res = pstm.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return res;
	}

	public Student fetch(int id) {
		String sql = "SELECT id, name, age FROM student WHERE id = ?";
		try (PreparedStatement pstm = con.prepareStatement(sql)) {
			pstm.setInt(1, id);
			try (ResultSet rs = pstm.executeQuery()) {
				if (rs.next()) {
					Student st = new Student();
					st.setId(rs.getInt("id"));
					st.setName(rs.getString("name"));
					st.setAge(rs.getInt("age"));
					return st;
				} else {
					return null;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public int delete(int idD) {
		int res = 0;
		String sql = "delete from student where id=?";
		try (PreparedStatement pstm = con.prepareStatement(sql)) {
			pstm.setInt(1, idD);
			
			res = pstm.executeUpdate();


		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

}
