package miniProject;

import java.sql.*;

public class dbc {

    static Connection con;
    static Statement stmt;
    public ResultSet rs;
    static String DBSelected = "moneytransfer";
    static String portSelect = "localhost:3306";
    static String url = "jdbc:mysql://" + portSelect + "/" + DBSelected;

    static void Connect_Db() throws SQLException {
        System.out.println("Database Selected");
    }

    public int Insert_User_Sign(String name, String email, String city, String password, int age) {
        if (checkEmail(email) == 1002)// check if email exits
            return 1001;
        String query = "INSERT INTO usersignup (name, email, age, city, password) VALUE ('" + name + "','" + email
                + "'," + age + ",'" + city + "','"
                + password
                + "')";
        try {
            stmt.executeUpdate(query);
            return 444;
        } catch (SQLException e) {
            System.out.println("1001" + e);
            return 1001;
        }
    }

    private int checkEmail(String query2) {
        String query = "select count(*) from usersignup where email = \"" + query2 + "\"";
        try {
            rs = stmt.executeQuery(query);
            rs.next();
            if (rs.getInt(1) > 0)
                return 1002;// already there
            return 444;// not there
        } catch (SQLException e) {
            System.out.println("1002 here " + e + "\n" + query);
            return 1002;
        }
    }

    void Conn() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, "root", "Khizar@mysql");
            System.out.println("Connection database...");
            stmt = con.createStatement();
            Connect_Db();
        } catch (SQLException e) {
            System.out.println("Connection fail... \n" + e);
        } catch (ClassNotFoundException e) {
            System.out.println("Connection fail... \n file not found \n" + e);
        }

    }

    public dbc() {
        Conn();
    }
}
