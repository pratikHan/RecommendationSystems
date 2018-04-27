

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Prediction {

	private int items=1682;
	
	public void recommend(int idu, List<Integer> lst, int[][] matx,
			Map<Integer, HashMap<Integer, Double>> map_corr, int[][] opMatrix) {

	//	System.out.println("recommend");
		
		for (int i = 1; i <= items; i++) {
			if (matx[idu][i] == 0) {
				double lval = 0.0;
				double demval = 0.0;
				for (int j : lst) {
					if (matx[j][i] == 0)
						continue;
					lval += matx[j][i] * map_corr.get(idu).get(j);
					demval += Math.abs(map_corr.get(idu).get(j));
				}
				double simVal = lval / demval;
				simVal = Math.round(simVal);
				if (simVal < 1)
					simVal = 1;
				else if (simVal > 5)
					simVal = 5;
				opMatrix[idu][i] = (int) simVal;
			} else {
				opMatrix[idu][i] = matx[idu][i];
			}
		}
	}
	
}
