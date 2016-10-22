package jp.co.nd_inc.em.runnergame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.method.BaseKeyListener;
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

    // フレームの間隔（ミリ秒）
    private static final long INTERVAL = 16;

    // 画面サイズに合わせるための縮尺
    private float mScale = 0;


    private Player player;
    private Ground ground;
    private Background background;
    private Score score;


    public GameView(Context context) {
        super(context);

        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
                    @Override
                    public void run() {
                        postInvalidate();
                    }
                }, 0, INTERVAL, TimeUnit.MILLISECONDS);

//        // 各ゲームオブジェクトを生成
//        player = new Player(context, R.drawable.player);
//        ground = new Ground(context, R.drawable.ground);
//        background = new Background(context, R.drawable.background);
//        score = new Score(context, R.drawable.score);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (mScale == 0) {
            calcScale();
        }

        canvas.scale(mScale, mScale);
        canvas.clipRect(0, 0, WIDTH, HEIGHT);

//        // 各ゲームオブジェクトを描画
//        player.draw(canvas);
//        ground.draw(canvas);
//        background.draw(canvas);
//        score.draw(canvas);
    }

    private void calcScale() {
        float scaleX = getWidth() / WIDTH;
        float scaleY = getHeight() /  HEIGHT;
        mScale = (scaleX > scaleY) ? scaleY : scaleX;
    }

}
