package miniProject;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

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
            stmt2 = con.prepareStatement("INSERT INTO usersignup (name, email, age, city, password) VALUE (?,?,?,?,?)");
            stmt2.setString(1, name);
            stmt2.setString(2, email);
            stmt2.setInt(3, age);
            stmt2.setString(4, city);
            stmt2.setString(5, password);
            int rs = stmt2.executeUpdate();
            // stmt.executeUpdate(query);
            if (rs > 0) {
                return 444;
            } else
                return 1001;
            // con.close();
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

    public boolean matchId(String email, String pass, int amount, String ccCode, int id) {
        try {
            System.out.println(email + "\t" + pass + "\t" + amount + "\t" + ccCode);
            int updateA = 0;
            stmt2 = con.prepareStatement(
                    "select count(*) from usersignup where email= ? and password = ?");
            stmt2.setString(1, email);
            stmt2.setString(2, pass);
            rs = stmt2.executeQuery();
            rs.next();
            System.out.println(rs.getInt(1));
            if (rs.getInt(1) > 0) {
                updateA = amount + showBal(ccCode);
                stmt2 = con
                        .prepareStatement("insert into `" + ccCode + "` value(NULL , 'Balance' , ? , ? , 0 ," + amount
                                + " , " + updateA + ")");
                stmt2.setString(1, email);
                stmt2.setInt(2, id);
                // stmt2.setInt(3, updateA);
                try {
                    if (stmt2.executeUpdate() == 1) {
                        // insertTrancdetail(id, id, "Succe", amount);
                        return true;
                    } else {
                        System.out.println("107");
                        return false;
                    }
                } catch (Exception e) {
                    System.out.println("at 114" + e);
                }

            } else {
                System.out.println("111");
                // wrong password
                return false;
            }
        } catch (Exception e) {
            System.out.println("At check id with email " + e);
        }
        return false;
    }

    public boolean insertTrancdetail(int reciverId, int senderId, String msg, int amount) {
        try {
            stmt2 = con.prepareStatement("insert into transacdetail value(NULL,?,?,?,?,?,?)");
            stmt2.setInt(1, reciverId);
            stmt2.setInt(2, senderId);
            stmt2.setString(3, msg);
            if (reciverId == senderId) {
                stmt2.setString(4, "cr");
            } else {
                stmt2.setString(4, "db");
            }
            stmt2.setInt(5, amount);
            stmt2.setString(6, "cr");
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

    public ArrayList<ArrayList<String>> getTransacDetail(String ccCode) {
        ArrayList<ArrayList<String>> arr = new ArrayList<ArrayList<String>>();
        if (!checkTable(ccCode)) {
            System.out.println("Table Find error");
            arr.add(new ArrayList<String>(Arrays.asList("null")));
        }
        try {
            stmt2 = con.prepareStatement("select * from `" + ccCode + "`");
            rs = stmt2.executeQuery();
            String temp;
            while (rs.next()) {
                String[] ar = { Integer.toString(rs.getInt("recordId")), rs.getString("remark"), rs.getString("Rname"),
                        Integer.toString(rs.getInt("towhom")), Integer.toString(rs.getInt("Debit")),
                        Integer.toString(rs.getInt("Credit")), Integer.toString(rs.getInt("balance")) };

                temp = "" + rs.getInt("recordId") + "\t" + rs.getString("remark") + "\t" + rs.getString("Rname")
                        + "\t" + rs.getInt("towhom") + "\t" + rs.getInt("Debit") + "\t" + rs.getInt("Credit") + "\t"
                        + rs.getInt("balance");
                arr.add(new ArrayList<String>(Arrays.asList(ar)));
            }
        } catch (Exception e) {
            System.out.println("Exception here in get Transaction detail" + e);
            arr.add(new ArrayList<String>(Arrays.asList("null")));

        }
        return arr;
    }

    public String getName(int ID) {
        try {
            stmt2 = con.prepareStatement("select name from usersignup where id = ?");
            stmt2.setInt(1, ID);
            rs = stmt2.executeQuery();
            if (rs.next()) {
                return rs.getString("name");
            }
        } catch (Exception e) {
            System.out.println("At get Name error");
            return "Error";
        }
        return "error";
    }

    public int getId(String email) {
        try {
            stmt2 = con.prepareStatement("select id from usersignup where email = ?");
            stmt2.setString(1, email);
            rs = stmt2.executeQuery();
            if (rs.next()) {
                return rs.getInt("id");
            }
        } catch (Exception e) {
            System.out.println("At getId " + e);
            return 0;
        }
        return 0;
    }

    public boolean checkTable(String ccCode) {
        try {
            stmt2 = con.prepareStatement("select count(*)  from recordtable where tables = ?");
            stmt2.setString(1, ccCode);
            rs = stmt2.executeQuery();
            rs.next();
            if (rs.getInt(1) > 0) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("inside checkTable \t\t" + e);
            return false;
        }
        return false;
    }

    public int showBal(String ccCode) {
        try {
            if (checkTable(ccCode)) {
                try {
                    PreparedStatement stmt3 = con
                            .prepareStatement("select * from `" + ccCode + "` ORDER BY recordId DESC LIMIT 1 ");
                    ResultSet rs1 = stmt3.executeQuery();
                    if (rs1.next()) {
                        return rs1.getInt("balance");
                    }
                } catch (Exception e) {
                    System.out.println("at balance fetch " + e);
                    return 0;
                }
            } else {
                System.out.println("Table not exixts");
                return 0;
            }
        } catch (Exception e) {
            System.out.println("error in finding table in record " + e);
            return 0;
        }
        return 0;
    }

    public boolean createTbale(String email, int age, int id) {
        String tableId = email;
        try {
            String query = "create table `" + tableId
                    + "` (recordId int(11) primary key AUTO_INCREMENT,";
            query += "remark varchar(20),";
            query += "Rname varchar(30), ";
            query += "towhom int(11) not null,";
            query += "Debit int(30),";
            query += "Credit int(30),";
            query += "balance int(30),";
            query += "FOREIGN KEY (towhom) REFERENCES usersignup(id)";
            query += ")";
            stmt2 = con.prepareStatement(query);
            int rs = stmt2.executeUpdate();
            if (rs == 0) {
                stmt2 = con.prepareStatement("insert into recordTable value(?)");
                stmt2.setString(1, tableId);
                rs = stmt2.executeUpdate();
                try {
                    stmt2 = con.prepareStatement(
                            "insert into `" + tableId + "` value(NULL , 'Balance' , ? , ? , 0 , 500,500 )");
                    stmt2.setString(1, email);// Recevier name Rname
                    stmt2.setInt(2, id); // id of his
                    System.out.println(id);
                    int rs2 = stmt2.executeUpdate();
                    if (rs2 == 1) {
                        return true;
                    } else {
                        return false;
                    }
                } catch (Exception e) {
                    System.out.println("in balance update at creation " + e);
                    return false;
                }
            }
            return false;
        } catch (Exception e) {
            System.out.println("herer exception in crate tabel " + e);
            return false;
        }
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

    // public void setccCode(String uEmial) {
    // String temp = "";
    // for (char c : uEmial.toCharArray()) {
    // char dot = '.';
    // if (c == dot || c == '@')
    // break;
    // else
    // temp += String.valueOf(c);
    // }
    // ccCode = temp + temp.length();
    // }

    // public static void main(String args[]) {
    // // System.out.println("asfdasf");
    // // System.out.println(new dbc().createTbale("KhizarShaiok", 19));
    // System.out.println(new dbc().showBal("safde"));
    // }
}
