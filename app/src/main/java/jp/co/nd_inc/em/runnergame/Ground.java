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

        positionX = 800f;
    }

    void draw(Canvas canvas) {
        positionX -= 0.1;

        canvas.drawBitmap(bitmap, positionX, 0, null);
    }
}
