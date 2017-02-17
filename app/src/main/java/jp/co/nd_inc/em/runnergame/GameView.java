package jp.co.nd_inc.em.runnergame;

import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.View;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


public class GameView extends View {
    private static final String TAG = "GameView";

    // ゲームオブジェクト達
    private Ground ground;
    private Player player;
    private Score score;

    private boolean gameover;

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

        // ゲームオブジェクトの生成
        ground = new Ground(context);
        player = new Player(context, ground, new Callback() {
            @Override
            public void gameover() {
                gameover = true;
            }
        });
        score = new Score(context);

        gameover = true;

        Executors.newSingleThreadScheduledExecutor()
                .scheduleAtFixedRate(new Runnable() {
                    @Override
                    public void run() {
                        // ゲームオーバー状態なら描画しない
                        if (gameover) {
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
                if (gameover) {
                    gameover = false;

                    restart();
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

    private void restart() {
        ground.init();
        player.init();
        score.init();
    }

    interface Callback {
        void gameover();
    }
}
