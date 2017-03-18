package jp.co.nd_inc.em.runnergame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;


class Player {
    private Bitmap bitmap1;
    private Bitmap bitmap2;

    private int positionY;

    private int drawCounter;
    private boolean drawBitmap1;
    private int jumpingTime;
    private int velocity;

    private enum Status {GROUND, JUMP, FREE}

    private Status status;

    private Ground ground;

    private SoundPool soundPool;
    private int jumpSound;

    private GameView.Callback callback;

    Player(Context context, Ground ground, GameView.Callback callback) {
        this.ground = ground;
        this.callback = callback;

        bitmap1 = BitmapFactory.decodeResource(context.getResources(), R.drawable.player1);
        bitmap2 = BitmapFactory.decodeResource(context.getResources(), R.drawable.player2);

        init();

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            soundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        } else {
            soundPool = new SoundPool.Builder()
                    .setMaxStreams(1)
                    .build();
        }
        jumpSound = soundPool.load(context, R.raw.se_jump, 1);
    }

    void draw(Canvas canvas) {
        drawCounter++;

        selectStatus();

        // ブロックに衝突したらゲームオーバー
        if (ground.clash(positionY)) {
            callback.gameover();
        }

        // statusに合わせてpostionYを計算する。
        switch (status) {
            case GROUND:
                break;
            case JUMP:
                jumpingTime++;
                velocity = -10;
                calcJumpPosition();
                break;
            case FREE:
                velocity = 10;
                calcFreePosition();
                break;
        }

        drawBitmap(canvas);
    }

    private void drawBitmap(Canvas canvas) {
        // 描画する画像の選択
        if (drawCounter > 10) {
            // フラグを反転
            drawBitmap1 ^= true;
            drawCounter = 0;
        }

        // 画像を描画
        if (drawBitmap1) {
            canvas.drawBitmap(bitmap1, 0, positionY, null);
        } else {
            canvas.drawBitmap(bitmap2, 0, positionY, null);
        }
    }

    private void selectStatus() {

        if (status == Status.JUMP) {
            // ジャンプしている時間が閾値を超えたらジャンプやめ
            if (jumpingTime >= 30) {
                status = Status.FREE;
                jumpingTime = 0;
            }

            return;
        }

        // 接地しているか？
        int groundPosition = ground.getPositionUnderPlayer();
        if (positionY >= groundPosition) {
            positionY = groundPosition;
            status = Status.GROUND;
        } else {
            status = Status.FREE;
        }
    }

    void touchDown() {
        // 接地していなければ何もしない
        if (status != Status.GROUND) {
            return;
        }

        // ジャンプ開始
        status = Status.JUMP;
        jumpingTime = 0;
        velocity = -10;
        soundPool.play(jumpSound, 1.0f, 1.0f, 1, 0, 1.0f);
        positionY -= 1;
    }

    void touchUp() {
        status = Status.FREE;
    }

    private void calcJumpPosition() {
        positionY += velocity;
    }

    private void calcFreePosition() {
        positionY += velocity;
    }

    void init() {
        positionY = 0;
        velocity = 10;
        drawCounter = 0;
        drawBitmap1 = true;
        status = Status.FREE;
    }
}
