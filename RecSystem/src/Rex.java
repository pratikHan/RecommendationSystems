
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Rex {

	public static Map<Integer, HashMap<Integer, Double>> map_corr = new HashMap<Integer, HashMap<Integer, Double>>();
	public static int user = 943;
	public int items = 1682;
	public static int[][] datamatrix = new int[944][1683];
	public static Map<Integer, List<Integer>> nearUser = new HashMap<Integer, List<Integer>>();
	public static int[][] outputMatrix = new int[944][1683];

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		FileOperator file = new FileOperator();
		Rex rec = new Rex();

		System.out.println("Build Model");
		datamatrix = file.getData();

		System.out.println("Train Model");
		rec.Fsimilarity();

		System.out.println("Predicting values..");

		Prediction pd = new Prediction();

		for (int i = 1; i <= user; i++) {

			pd.recommend(i, nearUser.get(i), datamatrix, map_corr, outputMatrix);

		}

		file.outData(outputMatrix);
	}

	/**
	 * Calculate the distance between two users based on the ratings on items This
	 * is implemented by using Pearson Coefficient Formula
	 **/

	public double calculatedistance(int[] xu, int[] yu) {

		double dmx = 0.0;
		double dmy = 0.0;
		double sxy = 0.0;
		double sx2 = 0.0;
		double sy2 = 0.0;

		for (int i = 1; i < xu.length; i++) {
			dmx += xu[i];
			dmy += yu[i];
		}

		dmx /= (xu.length - 1);
		dmy /= (yu.length - 1);

		for (int i = 1; i < xu.length; i++) {
			sxy += ((xu[i] - dmx) * (yu[i] - dmy));
			sx2 += Math.pow(xu[i] - dmx, 2.0);
			sy2 += Math.pow(yu[i] - dmy, 2.0);
		}
		return (sxy / (Math.sqrt(sx2 * sy2)));

	}

	/**
	 * Finds and sorts the list of users that have a minimum pearson Distance
	 */

	public void Fsimilarity() {

		// TODO Auto-generated method stub

		System.out.println("Findsimuser..");

		for (int i = 1; i <= user; i++) {
			List<Integer> closeNeig = new ArrayList<Integer>();
			HashMap<Integer, Double> muser = new HashMap<Integer, Double>();
			for (int j = 1; j <= user; j++) {
				if (i == j)
					continue;
				double usmCoef = 0.0;

				usmCoef = calculatedistance(datamatrix[i], datamatrix[j]);

				closeNeig.add(j);
				muser.put(j, usmCoef);
			}
			map_corr.put(i, muser);
			nearUser.put(i, closeNeig);
		}

	}
}
