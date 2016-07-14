package jp.co.nd_inc.em.runnergame;

import android.content.Context;
import android.graphics.Canvas;
import android.view.SurfaceHolder;
import android.view.SurfaceView;


public class GameView extends SurfaceView
        implements SurfaceHolder.Callback {
    private static final String TAG = "GameView";

    // ゲーム領域の幅（ピクセル）
    private static final float WIDTH = 800f;

    // ゲーム領域の高さ（ピクセル）
    private static final float HEIGHT = 480f;

    // 画面サイズに合わせるための縮尺
    private float mScale;

    private SurfaceHolder mHolder;


    public GameView(Context context) {
        super(context);

        mHolder = getHolder();
        mHolder.addCallback(this);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        calcScale();

        Canvas canvas = mHolder.lockCanvas();
        if (canvas == null) return;

        canvas.scale(mScale, mScale);
        canvas.clipRect(0, 0, WIDTH, HEIGHT);

        // ===========================
        // ここに描画処理を書く





        // ===========================

        mHolder.unlockCanvasAndPost(canvas);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
    }

    private void calcScale() {
        float scaleX = getWidth() / WIDTH;
        float scaleY = getHeight() /  HEIGHT;
        mScale = (scaleX > scaleY) ? scaleY : scaleX;
    }
}
