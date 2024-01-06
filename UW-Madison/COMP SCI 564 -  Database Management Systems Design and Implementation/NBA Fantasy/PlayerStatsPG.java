
public class PlayerStatsPG {
  String name;
  String team;
  int begYear;
  int endYear;
  int birthYear;

  public PlayerStatsPG() {
    reset();
  }

  public void reset() {
    name = "";
    team = "";
    begYear = -1;
    endYear = -1;
    birthYear = -1;
  }
}
