package jdbc;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class Exercise_2 {
	public static void main(String[] args) {
				String d = "com.mysql.jdbc.Driver";
				String user = "good";
				String password = "good";
				String createBook = "CREATE TABLE book (ISBN int(10) AUTO_INCREMENT PRIMARY KEY,title varchar(50),price int(5));";
				String createAuthor = "CREATE TABLE author (id int(5) AUTO_INCREMENT PRIMARY KEY,name varchar(50));";
				String createBooAut = "CREATE TABLE bookauthor(author int(5),book int(10),FOREIGN KEY(author) REFERENCES author(id),FOREIGN KEY(book) REFERENCES book(ISBN));";
				try {
					Class.forName(d);
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/JDBC2", user, password);
					Statement s = con.createStatement();
					s.executeUpdate(createBook);
					s.executeUpdate(createAuthor);
					s.executeUpdate(createBooAut);

					// Exercise 1.3.1
					getTitles(s, "Rahul");
					// Exercise 1.3.2
					setDetails(s, "mastering in java", "Rahul", 450);
					// Exercise 1.3.1
					updatePrice(s, "Rahul", 990);
					con.close();
				} catch (Exception e) {
					System.out.println(e);
				}
			}

			// EXERCISE 1.3.1
			public static void getTitles(Statement stmt, String author) {
				String query = "SELECT book.title FROM bookauthor join book join author on bookauthor.book=book.ISBN and bookauthor.author=author.id WHERE author.name="
						+ author + ";";
				try {
					ResultSet rs = stmt.executeQuery(query);
					while (rs.next()) {
						System.out.println(rs.getString(0));
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			// EXERCISE 1.3.2
			public static void setDetails(Statement s, String title, String authorname, int price) {
				String q1 = "INSERT INTO author(name) VALUES (\"" + authorname + "\");";
				String q2 = "INSERT INTO book(title,price) VALUES (\"" + title + "\"," + String.valueOf(price) + ");";
				try {
					s.executeUpdate(q1);
					s.executeUpdate(q2);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			// EXERCISE 1.3.3
			public static void updatePrice(Statement stmt, String authorname, int price) {
				String sr = "UPDATE bookauthor join book join author on bookauthor.book=book.ISBN and bookauthor.author=author.name SET book.price = "
						+ String.valueOf(price) + " WHERE author.name=\"" + authorname + "\";";
				try {
					stmt.executeUpdate(sr);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}


