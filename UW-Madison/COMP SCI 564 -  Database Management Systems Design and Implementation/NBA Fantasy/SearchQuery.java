import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class SearchQuery {

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

  public void simpleQuery(String sqlQuery) {
    System.out.println(sqlQuery);
    try {
      statement = connection.createStatement();
      resultSet = statement.executeQuery(sqlQuery);

      ResultSetMetaData metaData = resultSet.getMetaData();
      //int columns = metaData.getColumnCount();
      display = new Display();
      display.displayOutput(resultSet, metaData);
//      for (int i = 1; i <= columns; i++) {
//        System.out.print(metaData.getColumnName(i) + "\t");
//      }
//
//      System.out.println();
//
//      while (resultSet.next()) {
//
//        for (int i = 1; i <= columns; i++) {
//          System.out.print(resultSet.getObject(i) + "\t");
//        }
//        System.out.println();
//      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  } // end of simpleQuery method

  public static void searchStats(OffDefStats s) {
    SearchQuery demoObj = new SearchQuery();
    demoObj.Connection();

    String select = "select ";
    String from = " from offstats o, defstats d, players p";
    String where = " where ";
    String having = " having ";
    String order = " order by o.pts/o.games desc;";
    boolean have = false;

    if (s.ppg > 0) {
      select += "o.player, o.year, o.pts/o.games as ppg, ";
      where += "o.pts/o.games > " + String.valueOf(s.ppg);
    } else {
      select += "o.player, o.year, o.pts/o.games as ppg, ";
      where += "o.pts/o.games > 0" + String.valueOf(s.ppg);
    }
    if (s.apg > 0) {
      select += "o.ast/o.games as apg, ";
      where += " and o.ast/o.games > " + String.valueOf(s.apg);
    }
    if (s.orpg > 0) {
      select += "o.orb/o.games as orpg, ";
      where += " and o.orb/o.games > " + String.valueOf(s.orpg);
    }
    if (s.fgp > 0) {
      select += "o.fgp, ";
      where += " and o.fgp > " + String.valueOf(s.fgp);
    }
    if (s.tpp > 0) {
      select += "o.tpp, ";
      where += " and o.tpp > " + String.valueOf(s.tpp);
    }
    if (s.ftp > 0) {
      select += "o.ftt as ftp, ";
      where += " and o.ftt > " + String.valueOf(s.ftp);
    }
    if (s.stlpg > 0) {
      select += "d.stl/o.games as stlpg, ";
      where += " and d.stl/o.games > " + String.valueOf(s.stlpg);
    }
    if (s.blkpg > 0) {
      select += "d.blk/o.games as blkpg, ";
      where += " and d.blk/o.games > " + String.valueOf(s.blkpg);
    }
    if (s.drpg > 0) {
      select += "d.drb/o.games as drpg, ";
      where += " and d.drb/o.games > " + String.valueOf(s.drpg);
    }
    if (s.tovpg > 0) {
      select += "d.tov/o.games as tovpg, ";
      where += " and d.tov/o.games < " + String.valueOf(s.tovpg);
    }
    select = select.substring(0, select.length() - 2);
    where += " and o.ind = d.ind and o.player = p.name";
    if (have) {
      //having += ";";
      demoObj.simpleQuery(select + from + where + having + order);

    } else {
      //where += ";";
      demoObj.simpleQuery(select + from + where + order);
    }
  }

  public static void searchPlayer(PlayerStatsPG p) {
    SearchQuery demoObj = new SearchQuery();
    demoObj.Connection();

    String select = "select ";
    String from = " from offstats o, defstats d, players p, team t";
    String where = " where ";
    String having = "";
    boolean have = false;
    // add boolean check so that where stmt doesn't lead with "and"
    if (p.name != "") {
      select += "distinct o.player, ";
      where += ("o.player like \"" + p.name + "%\"");
    }
    if (p.team != "") {
      select += "o.team, t.conf, ";
      where += " and o.team = \"" + p.team + "\"";
    }
    if (p.begYear != -1) {
      // select += "o.year, ";
      where += " and o.year > \"" + p.begYear + "\"";
    }
    if (p.endYear != -1) {
      where += " and o.year < \"" + p.endYear + "\"";
    }
    if (p.birthYear != -1) {
      select += "p.born, ";
      where += " and p.born > \"" + p.birthYear + "\"";
    }
    // if (p.ppg != -1) {
    // select += "sum(o.pts)/sum(o.games) as ppg, ";
    // having += " sum(o.pts)/sum(o.games) > \"" + p.ppg + "\"";
    // }
    // if (p.apg != -1) {
    // select += "sum(o.ast)/sum(o.games) as apg, ";
    // having += " sum(o.ast)/sum(o.games) > \"" + p.apg + "\"";
    // }
    select = select.substring(0, select.length() - 2);
    where += " and o.ind = d.ind and o.player = p.name and o.team = t.name";
    if (have) {
      having += ";";
      demoObj.simpleQuery(select + from + where + having);

    } else {
      where += ";";
      demoObj.simpleQuery(select + from + where);
    }
  }
}


