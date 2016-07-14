package jp.co.nd_inc.em.runnergame.game_object;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.preference.PreferenceManager;


public class Score {

    SharedPreferences preferences;
    long score;
    Paint paint;

    public Score(Context context) {
        score = 0;
        preferences = PreferenceManager.getDefaultSharedPreferences(context);
        paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setTextSize(35);
    }

    public void draw(Canvas canvas) {
        score++;

        canvas.drawText(String.format("%010d", score), 10, 40, paint);
    }

}
