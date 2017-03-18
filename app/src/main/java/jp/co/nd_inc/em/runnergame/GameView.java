package jp.co.nd_inc.em.runnergame;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.NonNull;
import android.text.InputType;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


class GameView extends View {
    // ゲームオブジェクト達
    private Ground ground;
    private Player player;
    private Score score;
    private Background background;

    private boolean gameover;

    // ゲーム領域の幅（ピクセル）
    static final float WIDTH = 800f;

    // ゲーム領域の高さ（ピクセル）
    static final float HEIGHT = 480f;

    // フレームの間隔（ミリ秒）
    private static final long INTERVAL = 20;

    // 画面サイズに合わせるための縮尺
    private float mScale = 0;

    public GameView(final Context context) {
        super(context);

        // ゲームオブジェクトの生成
        ground = new Ground(context);
        score = new Score(context);
        background = new Background(context);
        player = new Player(context, ground, new Callback() {
            @Override
            public void gameover() {
                gameover = true;

                boolean highscore = score.saveHighscore();

                showGameOverDialog(context, highscore);
            }
        });

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

    private void showGameOverDialog(Context context, boolean highscore) {
        MaterialDialog.Builder builder =
                new MaterialDialog.Builder(context)
                        .title("ゲームオーバー")
                        .autoDismiss(false)
                        .positiveText("OK");

        if (highscore) {
            builder.content("ハイスコア更新!!!")
            .onPositive(new MaterialDialog.SingleButtonCallback() {
                @Override
                public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                    Log.d("OK", "OK");
                }
            })
            .inputType(InputType.TYPE_CLASS_TEXT)
            .input("ユーザー名", "", new MaterialDialog.InputCallback() {
                @Override
                public void onInput(MaterialDialog dialog, CharSequence input) {
                    Log.d("input", input.toString());
                    dialog.dismiss();
                }
            });
        } else {
            builder.onPositive(new MaterialDialog.SingleButtonCallback() {
                @Override
                public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                    dialog.dismiss();
                }
            });
        }
        builder.show();
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
        background.draw(canvas);
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
