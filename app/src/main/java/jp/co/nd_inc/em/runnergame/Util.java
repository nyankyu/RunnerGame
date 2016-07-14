package jp.co.nd_inc.em.runnergame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.Random;

/**
 * Created by nao on 2016/05/09.
 */
public class Util {
    public static Bitmap readBitmap(Context context, String name) {
        int resID = context.getResources().getIdentifier(name, "drawable", context.getPackageName());
        return BitmapFactory.decodeResource(context.getResources(), resID);
    }

    public static boolean lotteryMachine(int rate) {
        int randNum = new Random().nextInt(100);
        return rate >= randNum;
    }
}
