package jdbc;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Exercise_1 {
	public static void main(String[] args) {
			String d = "com.mysql.jdbc.Driver";
			String usr = "good";
			String password = "good";
			try {
				Class.forName(d);
				Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/JDBC", usr, password);
				Statement st = conn.createStatement();
				st.executeUpdate(createTable());
				st.executeUpdate(insert(1, "Rahul", "kumar", "singh", 775511));
				st.executeUpdate(update(1, "Akshay", "kumar", "patel",775511));
				st.executeUpdate(delete(1));
				conn.close();
			} catch (Exception e) {
				System.out.println(e);
			}

		}

		public static String insert(int authorId, String firstName, String middleName, String lastName, int phoneNumber) {
			return ("INSERT INTO Author VAlUES(" + String.valueOf(authorId) + ",\"" + firstName + "\",\"" + middleName
					+ "\",\"" + lastName + "\"," + String.valueOf(phoneNumber) + ");");
		}

		public static String delete(int authorID) {
			return ("DELETE FROM Author WHERE authorId =" + String.valueOf(authorID) + ";");
		}

		public static String update(int authorId, String firstName, String middleName, String lastName, int phoneNumber) {
			return ("UPDATE Author SET firstName=\"" + firstName + "\",middleName =\"" + middleName + "\",lastName =\""
					+ lastName + "\" WHERE authorId=" + String.valueOf(authorId) + ";");
		}

		public static String createTable() {
			String table = "    CREATE TABLE Author    \n" + "    (authorId int(10),    \n"
					+ "     firstName varchar(50),    \n" + "     middleName varchar(50),    \n"
					+ "     lastName varchar(50),  \n" + "     salary int(10),    \n" + "     PRIMARY KEY (authorId)    \n"
					+ "    );";
			return table;
		}
	}


