import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

public class StoredProcedures {
  static final String databasePrefix = "nbaplayers";
  static final String netID = "root";
  static final String hostName = "localhost";
  static final String databaseURL =
      "jdbc:mysql://" + hostName + "/" + databasePrefix + "?autoReconnect=true&useSSL=false";
  static final String password = "Gsudhir#1"; // please enter your own password

  private Connection connection = null;
  private Statement statement = null;
  private ResultSet resultSet = null;
  private Display display = null;

  public void Connection() {

    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
      System.out.println("databaseURL" + databaseURL);
      connection = DriverManager.getConnection(databaseURL, netID, password);
      System.out.println("Successfully connected to the database");
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  } // end of Connection
  
  public void simpleStoreProcedure(String spName) {   
    try {
        statement = connection.createStatement();
        CallableStatement myCallStmt = connection.prepareCall("call "+spName +";");
        //myCallStmt.registerOutParameter(1,Types.BIGINT);
        myCallStmt.execute();
        resultSet = myCallStmt.getResultSet();
        ResultSetMetaData metaData = resultSet.getMetaData();
        display = new Display();
        display.displayOutput(resultSet, metaData);
    }
    catch (SQLException e) {
        e.printStackTrace();
    }
} // end of simpleQuery method
  
  public static void getShooters() {
    StoredProcedures sp = new StoredProcedures();
    sp.Connection();
    sp.simpleStoreProcedure("getShooters");
  }
  public static void getDefenders() {
    StoredProcedures sp = new StoredProcedures();
    sp.Connection();
    sp.simpleStoreProcedure("getDefenders");
  }
  public static void getPlaymakers() {
    StoredProcedures sp = new StoredProcedures();
    sp.Connection();
    sp.simpleStoreProcedure("getPlaymakers");
  }
}
