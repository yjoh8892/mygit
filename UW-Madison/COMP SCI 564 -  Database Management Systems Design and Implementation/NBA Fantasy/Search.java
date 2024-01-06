import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Search extends JFrame {
  PlayerStatsPG player = new PlayerStatsPG();
  OffDefStats stats = new OffDefStats();
  private JLabel name = new JLabel("Name:   ");
  private JLabel team = new JLabel("Team:   ");
  private JLabel years = new JLabel("Year range:   ");
  private JLabel transition = new JLabel(" to ");
  private JLabel born = new JLabel("Born After ");
  private JLabel ppgl = new JLabel("PPG:");
  private JLabel apgl = new JLabel("APG:");
  private JLabel orpgl = new JLabel("ORPG:");
  private JLabel fgpl = new JLabel("FG%:");
  private JLabel tppl = new JLabel("3P%:");
  private JLabel ftpl = new JLabel("FT%:");
  private JLabel stll = new JLabel("STLPG:");
  private JLabel blkl = new JLabel("BLKPG:");
  private JLabel drpl = new JLabel("DRPG:");
  private JLabel tovl = new JLabel("TOVPG:");
  private JLabel expl = new JLabel("(Members of 50/40/90 club)");
  private JLabel features = new JLabel("Special Features:");
  private JButton enter1 = new JButton("Search Players");
  private JButton enter2 = new JButton("Search Seasons");
  private JButton enter3 = new JButton("Best Shooters");
  private JButton enter4 = new JButton("Best Defenders");
  private JButton enter5 = new JButton("Best Playmakers");

  public Search() {
    JTextField nameText = new JTextField(20);
    JTextField teamText = new JTextField(3);
    JTextField ppg = new JTextField(5);
    JTextField apg = new JTextField(5);
    JTextField orpg = new JTextField(5);
    JTextField fgp = new JTextField(5);
    JTextField tpp = new JTextField(5);
    JTextField ftp = new JTextField(5);
    JTextField bYear = new JTextField(5);
    JTextField eYear = new JTextField(5);
    JTextField birthYear = new JTextField(5);
    JTextField stl = new JTextField(5);
    JTextField blk = new JTextField(5);
    JTextField drp = new JTextField(5);
    JTextField tov = new JTextField(5);

    nameText.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        String s = e.getActionCommand();
        player.name = s;
      }
    });
    teamText.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        try {
          String s = e.getActionCommand();
          player.team = s;
          if (player.team.length() != 3)
            throw new Exception();
        } catch (Exception ex) {
          JOptionPane.showMessageDialog(new JFrame(), "Invalid Input!", "Dialog",
              JOptionPane.ERROR_MESSAGE);
        }
      }
    });
    ppg.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        try {
          String s = e.getActionCommand();
          stats.ppg = Double.valueOf(s);
          if (stats.ppg < 0)
            throw new Exception();
        } catch (Exception ex) {
          JOptionPane.showMessageDialog(new JFrame(), "Invalid Input!", "Dialog",
              JOptionPane.ERROR_MESSAGE);
        }
      }
    });
    apg.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        try {
          String s = e.getActionCommand();
          stats.apg = Double.valueOf(s);
          if (stats.apg < 0)
            throw new Exception();
        } catch (Exception ex) {
          JOptionPane.showMessageDialog(new JFrame(), "Invalid Input!", "Dialog",
              JOptionPane.ERROR_MESSAGE);
        }
      }
    });
    orpg.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        try {
          String s = e.getActionCommand();
          stats.orpg = Double.valueOf(s);
          if (stats.orpg < 0)
            throw new Exception();
        } catch (Exception ex) {
          JOptionPane.showMessageDialog(new JFrame(), "Invalid Input!", "Dialog",
              JOptionPane.ERROR_MESSAGE);
        }
      }
    });
    fgp.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        try {
          String s = e.getActionCommand();
          stats.fgp = Double.valueOf(s) / 100;
          if (stats.fgp < 0 || stats.fgp > 1)
            throw new Exception();
        } catch (Exception ex) {
          JOptionPane.showMessageDialog(new JFrame(), "Invalid Input!", "Dialog",
              JOptionPane.ERROR_MESSAGE);
        }
      }
    });
    tpp.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        try {
          String s = e.getActionCommand();
          stats.tpp = Double.valueOf(s) / 100;
          if (stats.tpp < 0 || stats.tpp > 1)
            throw new Exception();
        } catch (Exception ex) {
          JOptionPane.showMessageDialog(new JFrame(), "Invalid Input!", "Dialog",
              JOptionPane.ERROR_MESSAGE);
        }
      }
    });
    ftp.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        try {
          String s = e.getActionCommand();
          stats.ftp = Double.valueOf(s) / 100;
          if (stats.ftp < 0 || stats.ftp > 1)
            throw new Exception();
        } catch (Exception ex) {
          JOptionPane.showMessageDialog(new JFrame(), "Invalid Input!", "Dialog",
              JOptionPane.ERROR_MESSAGE);
        }
      }
    });
    bYear.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        try {
          String s = e.getActionCommand();
          player.begYear = Integer.valueOf(s);
          if (player.begYear > 2019 || player.begYear < 1975) {
            throw new Exception();
          }
        } catch (Exception ex) {
          JOptionPane.showMessageDialog(new JFrame(), "Invalid Input!", "Dialog",
              JOptionPane.ERROR_MESSAGE);
        }
      }
    });
    eYear.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        try {
          String s = e.getActionCommand();
          player.endYear = Integer.valueOf(s);
          if (player.endYear > 2023 || player.endYear < 1975) {
            throw new Exception();
          }
        } catch (Exception ex) {
          JOptionPane.showMessageDialog(new JFrame(), "Invalid Input!", "Dialog",
              JOptionPane.ERROR_MESSAGE);
        }
      }
    });
    birthYear.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        try {
          String s = e.getActionCommand();
          player.birthYear = Integer.valueOf(s);
          if (player.birthYear > 1997 || player.birthYear < 1913) {
            throw new Exception();
          }
        } catch (Exception ex) {
          JOptionPane.showMessageDialog(new JFrame(), "Invalid Input!", "Dialog",
              JOptionPane.ERROR_MESSAGE);
        }
      }
    });
    stl.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        try {
          String s = e.getActionCommand();
          stats.stlpg = Double.valueOf(s);
          if (stats.stlpg < 0)
            throw new Exception();
        } catch (Exception ex) {
          JOptionPane.showMessageDialog(new JFrame(), "Invalid Input!", "Dialog",
              JOptionPane.ERROR_MESSAGE);
        }
      }
    });
    blk.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        try {
          String s = e.getActionCommand();
          stats.blkpg = Double.valueOf(s);
          if (stats.blkpg < 0)
            throw new Exception();
        } catch (Exception ex) {
          JOptionPane.showMessageDialog(new JFrame(), "Invalid Input!", "Dialog",
              JOptionPane.ERROR_MESSAGE);
        }
      }
    });
    drp.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        try {
          String s = e.getActionCommand();
          stats.drpg = Double.valueOf(s);
          if (stats.drpg < 0)
            throw new Exception();
        } catch (Exception ex) {
          JOptionPane.showMessageDialog(new JFrame(), "Invalid Input!", "Dialog",
              JOptionPane.ERROR_MESSAGE);
        }
      }
    });
    tov.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        try {
          String s = e.getActionCommand();
          stats.tovpg = Double.valueOf(s);
          if (stats.tovpg < 0)
            throw new Exception();
        } catch (Exception ex) {
          JOptionPane.showMessageDialog(new JFrame(), "Invalid Input!", "Dialog",
              JOptionPane.ERROR_MESSAGE);
        }
      }
    });
    enter1.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        if (player.name.equals("")) {
          JOptionPane.showMessageDialog(new JFrame(), "Must enter name!", "Dialog",
              JOptionPane.ERROR_MESSAGE);
        }
        else {
          SearchQuery.searchPlayer(player);
        }
      }
    });
    enter2.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        SearchQuery.searchStats(stats);
      }
    });
    enter3.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        StoredProcedures.getShooters();
      }
    });
    enter4.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        StoredProcedures.getDefenders();
      }
    });
    enter5.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        StoredProcedures.getPlaymakers();
      }
    });
    
    // the NTpanel with the button and text
    JPanel NTpanel = new JPanel();
    NTpanel.setBorder(BorderFactory.createEmptyBorder(10, 100, 10, 100));
    NTpanel.setLayout(new FlowLayout());
    NTpanel.add(name);
    NTpanel.add(nameText);
    NTpanel.add(team);
    NTpanel.add(teamText);
    NTpanel.add(years);
    NTpanel.add(bYear);
    NTpanel.add(transition);
    NTpanel.add(eYear);
    NTpanel.add(born);
    NTpanel.add(birthYear);
    NTpanel.add(enter1);
    // offensive stats panel
    JPanel offPanel = new JPanel();
    offPanel.setBorder(BorderFactory.createEmptyBorder(10, 100, 10, 100));
    offPanel.setLayout(new FlowLayout());
    offPanel.add(ppgl);
    offPanel.add(ppg);
    offPanel.add(apgl);
    offPanel.add(apg);
    offPanel.add(orpgl);
    offPanel.add(orpg);
    offPanel.add(fgpl);
    offPanel.add(fgp);
    offPanel.add(tppl);
    offPanel.add(tpp);
    offPanel.add(ftpl);
    offPanel.add(ftp);
    // defensive stats panel
    JPanel defPanel = new JPanel();
    defPanel.setBorder(BorderFactory.createEmptyBorder(10, 100, 10, 100));
    defPanel.setLayout(new FlowLayout());
    defPanel.add(stll);
    defPanel.add(stl);
    defPanel.add(blkl);
    defPanel.add(blk);
    defPanel.add(drpl);
    defPanel.add(drp);
    defPanel.add(tovl);
    defPanel.add(tov);
    defPanel.add(enter2);

    JPanel specPanel = new JPanel();
    specPanel.setBorder(BorderFactory.createEmptyBorder(10, 100, 10, 100));
    specPanel.setLayout(new FlowLayout());
    specPanel.add(features);
    specPanel.add(enter3);
    specPanel.add(expl);
    specPanel.add(enter4);
    specPanel.add(enter5);

    // set up the frame and display it
    setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
    getContentPane().add(NTpanel);
    getContentPane().add(offPanel);
    getContentPane().add(defPanel);
    getContentPane().add(specPanel);

    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setTitle("Modern NBA Player Search (1980 - 2017)");
    pack();
    setVisible(true);
  }

  // create one Frame
  public static void main(String[] args) {
    new Search();
  }

}
