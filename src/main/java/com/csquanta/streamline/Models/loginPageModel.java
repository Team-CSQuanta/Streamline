package com.csquanta.streamline.Models;
import com.csquanta.streamline.dbConnection;
import java.sql.*;

public class loginPageModel {
    Connection connection;
    public loginPageModel () {
        connection = dbConnection.connector();
        if (connection == null) {

            System.out.println("connection not successful");
            System.exit(1);}
    }

    public boolean isDbConnected() {
        try {
            return !connection.isClosed();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        }
    }
public boolean isLogin(String user,String email,String pass) throws SQLException{
    PreparedStatement preparedStatement = null;
    ResultSet resultSet=null;
    String query="select * from users where user_name=? and pass_word=? and email=?";
   try{
       preparedStatement =  connection.prepareStatement(query);
       preparedStatement.setString(1,user);
       preparedStatement.setString(2,email);
       preparedStatement.setString(3,pass);
       resultSet=preparedStatement.executeQuery();
      if(resultSet.next()){
          return true;
    }
      else{
    return false;
   }
    }
      catch(Exception e) {

       return false;

       } finally{
       preparedStatement.close();
       resultSet.close();
   }
    }
}
