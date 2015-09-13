package co.jp.project.alice_kleio.flshky;

import java.math.BigDecimal;
import java.util.HashMap;
/*
 * スコア計算を行うためのクラス
 * */
public class ScoreMaker {
	private static String OK = "○";
	private static String NG = "×";
	private static double KISOTEN = 10.0;
	public static int score_make(HashMap<String, String> result) {
		// TODO Auto-generated method stub
		double score = 0;
		boolean bonus = false;
		double bairitsu = 1.0;
		int score_result = 0;
		if(!result.isEmpty()){
			for(int i = 1;i<result.size();i++){
				String e = result.get(String.valueOf(i));
//			for(Map.Entry<String, String> e:result.entrySet()){
				if(e.equals(OK)){
					if(bonus){
						if(bairitsu < 2.0){
							bairitsu = bairitsu + 0.1;
						}
					}
					bonus = true;
					score = score + (KISOTEN * bairitsu);
				}else if(e.equals(NG)){
					bonus = false;
					bairitsu = 1.0;
					score = score + 0;
				}else{
				
				}
			}
		}else{
			return 0;
		}
		BigDecimal bd = new BigDecimal(score);
		BigDecimal bd1 = bd.setScale(0, BigDecimal.ROUND_HALF_UP);
		score_result = bd1.intValue();
		return score_result;
	}

}
