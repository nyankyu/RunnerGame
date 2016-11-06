package jp.co.nd_inc.em.runnergame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

class Ground {
    private Bitmap bitmap;

    private long positionX;

    Ground(Context context, int resId) {
        bitmap = BitmapFactory.decodeResource(context.getResources(), resId);

        positionX = 800;
    }

    void draw(Canvas canvas) {
        canvas.drawBitmap(bitmap, positionX--, 0, null);
    }
}
