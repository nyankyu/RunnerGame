package jp.co.nd_inc.em.runnergame;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;


class Score {
    private static final long INCREMENTATION = 1;
    private long score;
    private Paint paint;

    Score() {
        score = 0;

        paint = new Paint();
        paint.setColor(Color.GRAY);
        paint.setTextSize(50);
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
}
