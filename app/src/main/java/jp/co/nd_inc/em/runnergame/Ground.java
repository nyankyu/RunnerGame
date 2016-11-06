package jp.co.nd_inc.em.runnergame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

class Ground {
    private Bitmap bitmap;

    private float positionX;

    Ground(Context context, int resId) {
        bitmap = BitmapFactory.decodeResource(context.getResources(), resId);

        positionX = GameView.WIDTH;
    }

    void draw(Canvas canvas) {
        positionX -= 10;

        if (positionX < 0) {
            positionX = GameView.WIDTH;
        }

        canvas.drawBitmap(bitmap, positionX, 0, null);
    }
}
