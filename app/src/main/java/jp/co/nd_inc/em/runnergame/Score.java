package jp.co.nd_inc.em.runnergame;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;


class Score {
    private static final long INCREMENTATION = 1;
    private long score;
    private Paint paint;
    private SharedPreferences sp;

    Score(Context context) {
        score = 0;

        paint = new Paint();
        paint.setColor(Color.GRAY);
        paint.setTextSize(50);

        sp = context.getSharedPreferences("score", Context.MODE_PRIVATE);
    }

    void init() {
        score = 0;
    }

    void draw(Canvas canvas) {
        // スコアを加算
        score += INCREMENTATION;

        // 表示する文字列を作成
        String str = String.format("%010d", score);

        // 画面に描画
        canvas.drawText(str, 480, 40, paint);
    }

    boolean saveHighscore() {
        long highscore = sp.getLong("highscore", 0);

        if (highscore >= score) {
            return false;
        }

        // ハイスコアの更新
        Log.d("High Score", String.valueOf(score));
        sp.edit().putLong("highscore", score).commit();

        return true;
    }
}
