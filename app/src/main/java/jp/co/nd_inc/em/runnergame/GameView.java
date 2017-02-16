package jp.co.nd_inc.em.runnergame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class GameView extends View {
    private static final String TAG = "GameView";

    private ScheduledExecutorService scheduledExecutorService;
    private Paint paint;

    // ゲームオブジェクト達
    private Ground ground;
    private Player player;
    private Score score;


    // ゲーム領域の幅（ピクセル）
    static final float WIDTH = 800f;

    // ゲーム領域の高さ（ピクセル）
    static final float HEIGHT = 480f;

    // フレームの間隔（ミリ秒）
    private static final long INTERVAL = 20;

    // 画面サイズに合わせるための縮尺
    private float mScale = 0;

    public GameView(Context context) {
        super(context);

        paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setTextSize(50);

        // ゲームオブジェクトの生成
        ground = new Ground(context);
        player = new Player(context, ground);
        score = new Score();

        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
                    @Override
                    public void run() {
                        // ゲームオーバー状態なら描画しない
                        if (player.gameover()) {
                            return;
                        }

                        postInvalidate();
                    }
                }, 0, INTERVAL, TimeUnit.MILLISECONDS);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

       if (mScale == 0) {
            calcScale();
        }

        canvas.scale(mScale, mScale);
        canvas.clipRect(0, 0, WIDTH, HEIGHT);

        // ゲームオブジェクトの描画
        ground.draw(canvas);
        player.draw(canvas);
        score.draw(canvas);
    }

    private void calcScale() {
        float scaleX = getWidth() / WIDTH;
        float scaleY = getHeight() /  HEIGHT;
        mScale = (scaleX > scaleY) ? scaleY : scaleX;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (player.gameover()) {
                    player.reStart();
                    break;
                }
                player.touchDown();
                break;
            case MotionEvent.ACTION_UP:
                player.touchUp();
                break;
        }
        return true;
    }
}
