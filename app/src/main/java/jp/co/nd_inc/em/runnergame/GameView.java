package jp.co.nd_inc.em.runnergame;

import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class GameView extends SurfaceView
        implements SurfaceHolder.Callback, Runnable {
    private static final String TAG = "GameView";

    // ゲーム領域の幅（ピクセル）
    private static final float WIDTH = 800f;

    // ゲーム領域の高さ（ピクセル）
    private static final float HEIGHT = 480f;

    // 画面サイズに合わせるための縮尺
    private float mScale;

    // フレームの間隔（ミリ秒）
    private static final long INTERVAL = 20;

    private SurfaceHolder mHolder;

    private ScheduledExecutorService mScheduledExecutorService;

    private GameLogic mGameLogic;



    public GameView(Context context) {
        super(context);

        mGameLogic = new GameLogic(context);

        mHolder = getHolder();
        mHolder.addCallback(this);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        calcScale();

        // INTERVALの間隔でフレームを描画する。
        mScheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        mScheduledExecutorService.scheduleAtFixedRate(this, 0, INTERVAL, TimeUnit.MILLISECONDS);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        mScheduledExecutorService.shutdown();
    }

    @Override
    public void run() {
//        Log.i(TAG, "run!");
        Canvas canvas;

        canvas = mHolder.lockCanvas();

        if (canvas == null) return;

        canvas.scale(mScale, mScale);
        canvas.clipRect(0, 0, WIDTH, HEIGHT);

        mGameLogic.draw(canvas);

        mHolder.unlockCanvasAndPost(canvas);
    }

    private void calcScale() {
        float scaleX = getWidth() / WIDTH;
        float scaleY = getHeight() /  HEIGHT;
        mScale = (scaleX > scaleY) ? scaleY : scaleX;
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int touchAction = event.getAction();

        switch (touchAction) {
            case MotionEvent.ACTION_DOWN:
                mGameLogic.onTouchDown();
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
                mGameLogic.onTouchUp();
                break;
        }

        return true;
    }

}
