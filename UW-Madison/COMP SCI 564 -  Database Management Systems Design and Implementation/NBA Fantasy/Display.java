import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Display {
  JFrame f;
  JTable j;
  public Display () {
    f = new JFrame();
  }
  
  public void displayOutput(ResultSet rs, ResultSetMetaData rsmd) {
    f.setTitle("Result (* = Hall of Fame)");
    try {
      int columns = rsmd.getColumnCount();
      String[] columnNames = new String[columns];
      for (int i = 1; i <= columns; i++) {
        columnNames[i-1] = rsmd.getColumnName(i);
        System.out.print(rsmd.getColumnName(i) + "\t");
      }

      System.out.println();
      ArrayList<String[]> rows = new ArrayList<String[]>();
      while (rs.next()) {
        String[] curRow = new String[columns];
        for (int i = 1; i <= columns; i++) {
          curRow[i-1] = rs.getObject(i).toString();
          System.out.print(rs.getObject(i) + "\t");
        }
        rows.add(curRow);
        System.out.println();
      }
      String[][] data = new String[rows.size()][columns];
      for (int r = 0; r < data.length; r++) {
        for (int c = 0; c < columns; c++) {
          data[r][c] = (rows.get(r))[c];
        }
      }
      j = new JTable(data, columnNames);
      j.setBounds(30, 40, 200, 300);
      JScrollPane sp = new JScrollPane(j);
      f.add(sp);
      // Frame Size
      f.setSize(500, 200);
      // Frame Visible = true
      f.setVisible(true);
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}
