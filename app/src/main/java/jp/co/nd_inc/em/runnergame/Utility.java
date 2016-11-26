package jp.co.nd_inc.em.runnergame;

/**
 * Created by nao on 2016/11/26.
 */

public class Utility {
    /**
     * 指定した確率でtrueを返す。
     * @param probability tureを返す確率
     * @return probabilityで指定した確率でtrue
     */
    public static boolean lotteryMachine(float probability) {
        double rand = Math.random();

        return rand < probability;
    }
}
