package jp.co.nd_inc.em.runnergame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;


public class Player {
    private Bitmap bitmap1;
    private Bitmap bitmap2;

    private int positionY;



    Player(Context context) {
        bitmap1 = BitmapFactory.decodeResource(context.getResources(), R.drawable.player1);
        bitmap1 = BitmapFactory.decodeResource(context.getResources(), R.drawable.player2);

        positionY = 250;
    }

    void draw(Canvas canvas) {
        canvas.drawBitmap(bitmap1, 0, positionY, null);
    }
}
