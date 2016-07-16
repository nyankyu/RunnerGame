package jp.co.nd_inc.em.runnergame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.View;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class GameView extends View {
    private static final String TAG = "GameView";

    private ScheduledExecutorService scheduledExecutorService;
    private Paint paint;

    // ゲーム領域の幅（ピクセル）
    private static final float WIDTH = 800f;

    // ゲーム領域の高さ（ピクセル）
    private static final float HEIGHT = 480f;

    // 画面サイズに合わせるための縮尺
    private float mScale;

    public GameView(Context context) {
        super(context);

        paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setTextSize(100);

        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.scheduleAtFixedRate(
                new Runnable() {
                    @Override
                    public void run() {
                        postInvalidate();
                    }
                }, 0, 20, TimeUnit.MILLISECONDS);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Log.i(TAG, "hoge");
        super.onDraw(canvas);

        canvas.clipRect(0, 0, 800, 480);
        canvas.drawText("hoge", 100, 100, paint);

    }

    private void calcScale() {
        float scaleX = getWidth() / WIDTH;
        float scaleY = getHeight() /  HEIGHT;
        mScale = (scaleX > scaleY) ? scaleY : scaleX;
    }
}
