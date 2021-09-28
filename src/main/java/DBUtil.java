import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class DBUtil {

    private static final String url = "jdbc:postgresql://localhost:5432/postgres";
    private static final String user = "postgres";
    private static final String password = "dflbvljvf";
    private static Connection con;
    private static Statement stmt;
    private static ResultSet rs;
    private Scanner in;
    private ConsoleDialog cd;

    DBUtil() {
        this.in = new Scanner(System.in);
        cd = new ConsoleDialog();
        try {
            con = DriverManager.getConnection(url, user, password);
            stmt = con.createStatement();
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }

    }

    public static Properties createPropsShort(String database, String user, String password) {
        Properties props = new Properties();
        props.put("database", database);
        props.put("user", user);
        props.put("password", password);
        return props;
    }

    public static Connection getConnections(String db, Properties props) throws SQLException {
        Connection conn = DriverManager.getConnection(db, props);
        System.out.println(conn);
        return conn;
    }

    public void createTable() {
        try {
            String StrForCreateTable = "create table IF NOT EXISTS ";
            String NameDB = cd.getNameTable();
            StrForCreateTable += NameDB;
            StrForCreateTable += " (ID SERIAL PRIMARY KEY, NameBooks VARCHAR(50), Author VARCHAR(50));";
            stmt.execute(StrForCreateTable);
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
    }

    public void addToDataBase() {
        String StrForInsert = "insert into books2 (NameBooks, Author) values (";
        try {
            while (true) {
                String BooksName = cd.getNameBook();
                String BooksAuthor = cd.getAuthor();
                if (BooksName == "" || BooksAuthor == "")
                    break;
                String StrForInput = StrForInsert + "'" + BooksName + "'" + "," + "'" + BooksAuthor + "'" + ")";
                stmt.execute(StrForInput);
            }
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
    }

    public void selectAllDataBase() {
        try {
            String SelectFromBooks = "select * from books2;";
            rs = stmt.executeQuery(SelectFromBooks);
            while (rs.next()) {
                System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3));
            }
            System.out.println();
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
    }

    public void selectDataBaseByCondition() {
        try {
            String SelectForID = "select * from books2 where id=";
            int index = cd.getIndexBook();
            SelectForID += index + "";
            rs = stmt.executeQuery(SelectForID);
            while (rs.next()) {
                System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3));
            }
            System.out.println();
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
    }

    public void deleteFromDataBase() {
        String delete = "DELETE FROM books2 WHERE id=";
        int index;

        try {
            while (true) {
                String del = delete;
                index = cd.getIndexBook();
                if (index >= 0) {
                    del += index + ";";
                    stmt.executeUpdate(del);
                } else break;
            }
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
        System.out.println();
    }

    public void endWorkWithDataBase() {
        try {
            if (con != null)
                con.close();
            if (stmt != null)
                stmt.close();
            if (rs != null)
                rs.close();
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
    }

    public void deleteTableFromDataBase() {
        try {
            con = DriverManager.getConnection(url, user, password);
            stmt = con.createStatement();
            String StrForCreateTable = "drop table ";
            String NameDB = cd.getNameTable();
            StrForCreateTable += NameDB;
            stmt.execute(StrForCreateTable);
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
    }

    public String getAuthorsNameFromTable() {
        int ID = cd.getIndexBook();
        String Author = "";

        try {
            String SelectForID = "select * from books2 where id=";
            SelectForID += ID + "";
            rs = stmt.executeQuery(SelectForID);
            if (rs.next())
                Author = rs.getString(3);
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
        return Author;
    }

    public String getBooksNameFromTable() {
        int ID = cd.getIndexBook();
        String Author = "";

        try {
            String SelectForID = "select * from books2 where id=";
            SelectForID += ID + "";
            rs = stmt.executeQuery(SelectForID);
            if (rs.next())
                Author = rs.getString(2);
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
        return Author;
    }
}