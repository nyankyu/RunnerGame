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

        positionY = 250;

        drawCounter = 0;
        drawBitmap1 = true;

        status = Status.GROUND;
    }

    void draw(Canvas canvas) {
        drawCounter++;

        if (drawCounter > 10) {
            // フラグを反転
            drawBitmap1 ^= true;
            drawCounter = 0;
        }

        // 設置しているか？
        int groundPostion = ground.getLeftsidePosition();
        if (positionY + 100 >= groundPostion) {
            status = Status.GROUND;
        }

        // statusに合わせてpostionYを計算する。
        switch (status) {
            case GROUND:
                break;
            case JUMP:
                calcJumpPostion();
                break;
            case FREE:
                calcFreePostion();
                break;
        }

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

    }

    private void calcFreePostion() {

    }
}
