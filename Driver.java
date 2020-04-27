import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Random;

/*	This Program simulate the NBA 2020 playoffs which was sadly cancelled due to Covid-19 outbreak
 * 	The statistic used in this program is provided by NBA.com
 * 
 */
public class Driver {

	public static void main(String[] args) {
		NBAteams[] Eteams = new NBAteams[8];
		NBAteams[] Wteams = new NBAteams[8];

		// Eastern Conference Teams and Stats
		Eteams[0] = new NBAteams("Milwaukee Bucks", 55.3, 14.9, 51.7, 74.2, 0.815);
		Eteams[1] = new NBAteams("Toronto Raptors", 53.6, 14.4, 45.2, 80.0, 0.719);
		Eteams[2] = new NBAteams("Boston Celtics", 52.9, 13.6, 46.0, 80.1, 0.672);
		Eteams[3] = new NBAteams("Miami Heat", 54.9, 14.9, 44.5, 77.8, 0.631);
		Eteams[4] = new NBAteams("Indiana Pacers", 53.3, 13.1, 42.8, 78.7, 0.6);
		Eteams[5] = new NBAteams("Philadelphia 76ers", 53.0, 14.2, 45.5, 75.2, 0.6);
		Eteams[6] = new NBAteams("Brooklyn Nets", 51.5, 15.5, 48.5, 74.4, 0.469);
		Eteams[7] = new NBAteams("Orlando Magic", 50.3, 12.6, 44.5, 77.0, 0.462);
		// Western Conference Teams and Stats
		Wteams[0] = new NBAteams("Los Angeles Lakers", 54.8, 15.1, 46.1, 73.0, 0.778);
		Wteams[1] = new NBAteams("LA Clippers", 53.2, 14.8, 48.0, 79.2, 0.688);
		Wteams[2] = new NBAteams("Denver Nuggets", 53.2, 13.7, 44.3, 77.5, 0.662);
		Wteams[3] = new NBAteams("Utah Jazz", 55.2, 14.9, 45.1, 77.2, 0.641);
		Wteams[4] = new NBAteams("Oklahoma City Thunder", 53.4, 13.5, 42.7, 79.7, 0.625);
		Wteams[5] = new NBAteams("Houston Rockets", 53.9, 14.7, 44.9, 78.7, 0.625);
		Wteams[6] = new NBAteams("Dallas Mavericks", 54.8, 12.8, 47.0, 77.3, 0.597);
		Wteams[7] = new NBAteams("Memphis Grizzlies", 53.0, 15.3, 46.7, 76.1, 0.492);

//		NBAFINALS(Game(Eteams), Game(Wteams));

//		 simulate the 2020 NBA Finals for a 1000 times
		HashMap<NBAteams, Integer> unimap = new HashMap<>();
		for (int i = 0; i < 1000; i++) {
			NBAteams x = NBAFINALS(Game(Eteams), Game(Wteams));
			if (unimap.containsKey(x)) {
				unimap.put(x, unimap.get(x) + 1);
			} else {
				unimap.put(x, 1);
			}
		}
		List<Entry<NBAteams, Integer>> sortedUnimap = new LinkedList<Entry<NBAteams, Integer>>(unimap.entrySet());
		Collections.sort(sortedUnimap, new Comparator<Entry<NBAteams, Integer>>() {
			@Override
			public int compare(Entry<NBAteams, Integer> o1, Entry<NBAteams, Integer> o2) {
				return o2.getValue().compareTo(o1.getValue());
			}
		});
		System.out.println("\n\n\n\n\n\n\n\n\n\n");
		System.out.println(sortedUnimap);

	}

	public static void Finals1000() {

	}

	public static NBAteams Game(NBAteams[] E) {
		Random rand = new Random();
		NBAteams[] firstRoundWinners = new NBAteams[4];
		ArrayList<Integer> RoundRandom = new ArrayList<Integer>();
		RandomOpponentGenerate(RoundRandom, 8);
		int winsA = 0;
		int winsB = 0;
		int compete;
		// Quarterfinals
		for (int i = 0; i < 8; i++) {
			while (winsA != 4 && winsB != 4) {
				compete = rand.nextInt(E[RoundRandom.get(i)].toCow() + E[RoundRandom.get(i + 1)].toCow()) + 1;
				if (compete <= E[RoundRandom.get(i)].toCow()) {
					winsA++;
					System.out.println(E[RoundRandom.get(i)].toString() + " wins Game " + (winsA + winsB)
							+ " of the First Round of the 2020 NBA Playoffs");
				} else if (compete > E[RoundRandom.get(i)].toCow()) {
					winsB++;
					System.out.println(E[RoundRandom.get(i + 1)].toString() + " wins Game " + (winsA + winsB)
							+ " of the First Round of the 2020 NBA Playoffs");

				}
				if (winsA == 4) {
					System.out.println(E[RoundRandom.get(i)].toString()
							+ " wins the First Round of the 2020 NBA Playoffs and advanced to the Semifinals!!!");
					System.out.println(E[RoundRandom.get(i)].toString() + " " + winsA + " : " + winsB + " "
							+ E[RoundRandom.get(i + 1)].toString());
					firstRoundWinners[i / 2] = E[RoundRandom.get(i)];
				} else if (winsB == 4) {
					System.out.println(E[RoundRandom.get(i + 1)].toString()
							+ " wins the First Round of the 2020 NBA Playoffs and advanced to the Semifinals!!!");
					System.out.println(E[RoundRandom.get(i + 1)].toString() + " " + winsB + " : " + winsA + " "
							+ E[RoundRandom.get(i)].toString());
					firstRoundWinners[i / 2] = E[RoundRandom.get(i + 1)];
				}
			}
			System.out.println("\n");
			winsA = 0;
			winsB = 0;
			i++;
		}
		// Semifinals
		return Semifinals(firstRoundWinners);
	}

	public static NBAteams Semifinals(NBAteams[] E) {
		Random rand = new Random();
		NBAteams[] secondRoundWinners = new NBAteams[2];
		int winsA = 0;
		int winsB = 0;
		int compete;
		for (int i = 0; i < 4; i++) {
			while (winsA != 4 && winsB != 4) {
				compete = rand.nextInt(E[i].toCow() + E[i + 1].toCow()) + 1;
				if (compete <= E[i].toCow()) {
					winsA++;
					System.out.println(E[i].toString() + " wins Game " + (winsA + winsB)
							+ " of the Semifinals of the 2020 NBA Playoffs");
				} else if (compete > E[i].toCow()) {
					winsB++;
					System.out.println(E[i + 1].toString() + " wins Game " + (winsA + winsB)
							+ " of the Semifinals of the 2020 NBA Playoffs");

				}
				if (winsA == 4) {
					System.out.println(E[i].toString()
							+ " wins the Semifinals of the 2020 NBA Playoffs and advanced to the Conference Finals!!!");
					System.out.println(E[i].toString() + " " + winsA + " : " + winsB + " " + E[i + 1].toString());
					secondRoundWinners[i / 2] = E[i];
				} else if (winsB == 4) {
					System.out.println(E[i + 1].toString()
							+ " wins the Semifinals of the 2020 NBA Playoffs and advanced to the Conference Finals!!!");
					System.out.println(E[i + 1].toString() + " " + winsB + " : " + winsA + " " + E[i].toString());
					secondRoundWinners[i / 2] = E[i + 1];
				}
			}
			System.out.println("\n");
			winsA = 0;
			winsB = 0;
			i++;
		}
		return ConferenceFinals(secondRoundWinners);
	}

	public static NBAteams ConferenceFinals(NBAteams[] E) {
		Random rand = new Random();
		NBAteams[] finalRoundWinners = new NBAteams[1];
		int winsA = 0;
		int winsB = 0;
		int compete;
		while (winsA != 4 && winsB != 4) {
			compete = rand.nextInt(E[0].toCow() + E[1].toCow()) + 1;
			if (compete <= E[0].toCow()) {
				winsA++;
				System.out.println(E[0].toString() + " wins Game " + (winsA + winsB)
						+ " of the Conference Finals of the 2020 NBA Playoffs");
			} else if (compete > E[0].toCow()) {
				winsB++;
				System.out.println(E[1].toString() + " wins Game " + (winsA + winsB)
						+ " of the Conference Finals of the 2020 NBA Playoffs");

			}
			if (winsA == 4) {
				System.out.println(E[0].toString() + " has moved on to the NBA Finals!!!");
				System.out.println(E[0].toString() + " " + winsA + " : " + winsB + " " + E[1].toString());
				finalRoundWinners[0] = E[0];
			} else if (winsB == 4) {
				System.out.println(E[1].toString() + " has moved on to the NBA Finals!!!");
				System.out.println(E[1].toString() + " " + winsB + " : " + winsA + " " + E[0].toString());
				finalRoundWinners[0] = E[1];
			}
		}
		System.out.println("\n\n\n\n\n\n\n\n\n");
		return finalRoundWinners[0];
	}

	public static NBAteams NBAFINALS(NBAteams a, NBAteams b) {
		System.out.println("Ladies and Gentlemen, Welcome to the 2020 NBA finals between " + a.toString() + " and "
				+ b.toString() + ". Let's start!");
		Random rand = new Random();
		int winsA = 0;
		int winsB = 0;
		int compete;
		NBAteams[] finalwinnner = new NBAteams[1];
		while (winsA != 4 && winsB != 4) {
			compete = rand.nextInt(a.toCow() + b.toCow()) + 1;
			if (compete <= a.toCow()) {
				winsA++;
				System.out.println(a.toString() + " wins Game " + (winsA + winsB) + " of the 2020 NBA Finals");
			} else if (compete > a.toCow()) {
				winsB++;
				System.out.println(b.toString() + " wins Game " + (winsA + winsB) + " of the 2020 NBA Finals");

			}
			if (winsA == 4) {
				System.out.println(a.toString() + " is your NBA 2020 Final winner!!!!!!!");
				System.out.println(a.toString() + " " + winsA + " : " + winsB + " " + b.toString());
				finalwinnner[0] = a;
			} else if (winsB == 4) {
				System.out.println(b.toString() + " is your NBA 2020 Final winner!!!!!!!");
				System.out.println(b.toString() + " " + winsB + " : " + winsA + " " + a.toString());
				finalwinnner[0] = b;
			}
		}
		return finalwinnner[0];
	}

	public static void RandomOpponentGenerate(ArrayList<Integer> x, int y) {
		for (int i = 0; i < y; i++) {
			x.add(i);
		}
		Collections.shuffle(x);
	}

}
