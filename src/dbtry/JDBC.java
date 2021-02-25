package dbtry;

import java.sql.*;
public class JDBC {
    public static void main(String[] args) throws  Exception{

        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Sample","user","password");



        String sql = "INSERT INTO Message (content) VALUES (?)";
        PreparedStatement statement = con.prepareStatement(sql);

//        statement.setString(1,"7");
        statement.setString(1,"boys scoutwe");

        int rows = statement.executeUpdate();
        if(rows>0){
            System.out.println("A row has been updated!");
        }

//        Statement st = con.createStatement();

//        ResultSet resultSet = st.executeQuery("select * from Message");

//        while (resultSet.next()){
//            int id = resultSet.getInt("id");
//            String message = resultSet.getString("content");
//
//            System.out.println(id+" "+message);
//        }
        statement.close();
        con.close();
    }
}
