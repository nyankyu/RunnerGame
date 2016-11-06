package jp.co.nd_inc.em.runnergame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

class Ground {
    private Bitmap bitmap;

    Ground(Context context, int resId) {
        bitmap = BitmapFactory.decodeResource(context.getResources(), resId);
    }

    void draw(Canvas canvas) {
        canvas.drawBitmap(bitmap, 0, 0, null);
    }
}
