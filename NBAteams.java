
public class NBAteams {
	private String teamName;
	private int cow;

	public NBAteams(String teamName, double eFG, double tov, double reb, double ft, double wr) {
		this.teamName = teamName;
		this.cow = (int) Math.round(((((eFG) * 0.4) + ((reb) * 0.2) + ((ft) * 0.15) - ((tov) * 0.25))) + (wr * 50));
	}

	public String toString() {
		return this.teamName;
	}

	public int toCow() {
		return this.cow;
	}
}
