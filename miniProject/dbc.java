package miniProject;

import java.sql.*;
import java.util.ArrayList;

public class dbc {

    static Connection con;
    static Statement stmt;
    PreparedStatement stmt2 = null;
    public ResultSet rs;
    static String DBSelected = "moneytransfer";
    static String portSelect = "localhost:3306";
    static String url = "jdbc:mysql://" + portSelect + "/" + DBSelected;

    static void Connect_Db() throws SQLException {
        // System.out.println("Database Selected");
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
            // con.close();
            return 444;
        } catch (SQLException e) {
            System.out.println("1001 here is exeception" + e);
            return 1001;
        }
    }

    public boolean Login(String email, String Password) {
        try {
            stmt2 = con.prepareStatement("select count(*) from usersignup where email = ? and password = ?");
            stmt2.setString(1, email);
            stmt2.setString(2, Password);
            rs = stmt2.executeQuery();
            rs.next();
            if (rs.getInt(1) > 0) {
                // found the user
                return true;
            } else {
                // wrong password
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Exception at user emai verification \n" + e);
            // TODO: handle exception
        }
        return true;
    }

    public int checkEmail(String query2) {
        String query3 = "select count(*) from usersignup where email =?";
        try {
            stmt2 = con.prepareStatement(query3);
            stmt2.setString(1, query2);
            rs = stmt2.executeQuery();
            rs.next();
            if (rs.getInt(1) > 0) {
                // con.close();
                return 1002;// already there
            }
            // con.close();
            return 444;// not there
        } catch (SQLException e) {
            System.out.println("1002 here " + e + "\n" + query2);
            return 1002;
        }
    }

    public boolean matchId(int id, String pass, int amount) {
        try {
            stmt2 = con.prepareStatement(
                    "select count(*) from usersignup where id = ? and password = ?");
            stmt2.setInt(1, id);
            stmt2.setString(2, pass);
            rs = stmt2.executeQuery();
            rs.next();
            if (rs.getInt(1) > 0) {
                amount += showBal(Integer.toString(id));
                stmt2 = con.prepareStatement("update balance set bal = ? where id = ?");
                stmt2.setInt(1, amount);
                stmt2.setInt(2, id);
                if (stmt2.executeUpdate() == 1) {
                    insertTrancdetail(id, id, "Succe", "db");
                    return true;
                } else {
                    return false;
                }
            } else {
                // wrong password
                return false;
            }
        } catch (Exception e) {
            System.out.println("At check id with email " + e);
        }
        return false;
    }

    public boolean insertTrancdetail(int reciverId, int senderId, String msg, String mode) {
        try {
            stmt2 = con.prepareStatement("insert into transacdetail value(NULL,?,?,?,?)");
            stmt2.setInt(1, reciverId);
            stmt2.setInt(2, senderId);
            stmt2.setString(3, msg);
            stmt2.setString(4, "db");
            if (stmt2.executeUpdate() == 1) {
                System.out.println("Done and succesfull");
            } else {
                System.out.println("Fail in attemp");
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            // TODO: handle exception
        }
        return true;
    }

    public ArrayList getTransacDetail() {
        ArrayList a = new ArrayList<>();
        try {
            stmt2 = con.prepareStatement("select * from transacdetail where SenderId = 3");
            rs = stmt2.executeQuery();
            while (rs.next()) {
                System.out.println(" " + rs.getInt(1) + " " + rs.getInt(2));
            }
        } catch (Exception e) {
            System.out.println("Exception " + e.getMessage());
            // TODO: handle exception
        }
        return a;
    }

    public int showBal(String id) {
        String query = "select bal  from balance where id = ?";
        try {
            stmt2 = con.prepareStatement(query);
            stmt2.setString(1, id);
            rs = stmt2.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);// already there
            }
        } catch (Exception e) {
            System.out.println("In balance" + e);
            return 0;
            // TODO: handle exception
        }

        return 0;
    }

    public void showTranc() {

    }

    void Conn() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, "root", "Khizar@mysql");
            // System.out.println("Connection database...");
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

    // public static void main(String[] args) {
    // System.out.println(new dbc().getTransacDetail());

    // }
}
