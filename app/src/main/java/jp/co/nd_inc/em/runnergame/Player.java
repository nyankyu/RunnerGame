package jp.co.nd_inc.em.runnergame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.Log;


public class Player {
    private Bitmap bitmap1;
    private Bitmap bitmap2;

    private int positionY;

    private int drawCounter;
    private boolean drawBitmap1;

    private enum Status {GROUND, JUMP, FREE};
    private Status status;

    private Ground ground;


    Player(Context context, Ground ground) {
        this.ground = ground;

        bitmap1 = BitmapFactory.decodeResource(context.getResources(), R.drawable.player1);
        bitmap2 = BitmapFactory.decodeResource(context.getResources(), R.drawable.player2);

        positionY = 0;

        drawCounter = 0;
        drawBitmap1 = true;

        status = Status.FREE;
    }

    void draw(Canvas canvas) {
        drawCounter++;

        if (drawCounter > 10) {
            // フラグを反転
            drawBitmap1 ^= true;
            drawCounter = 0;
        }

        // 接地しているか？
        int groundPostion = ground.getLeftsidePosition();
        if (positionY + 100 >= groundPostion) {
            positionY = groundPostion - 100;
            status = Status.GROUND;
        }

        // statusに合わせてpostionYを計算する。
        switch (status) {
            case GROUND:
                Log.d("Player", "status : ground");
                break;
            case JUMP:
                calcJumpPostion();
                Log.d("Player", "status : jump");
                break;
            case FREE:
                calcFreePostion();
                Log.d("Player", "status : free");
                break;
        }

        // 画像を描画
        if (drawBitmap1) {
            canvas.drawBitmap(bitmap1, 0, positionY, null);
        } else {
            canvas.drawBitmap(bitmap2, 0, positionY, null);
        }
    }

    public void touchDown() {
        Log.d("Play", "touchDown");

        if (status == Status.GROUND) {
            status = Status.JUMP;
        }
    }

    public void touchUp() {
        Log.d("Play", "touchUp");

        status = Status.FREE;
    }

    private void calcJumpPostion() {
        positionY -= 10;
    }

    private void calcFreePostion() {
        positionY += 10;
    }
}
