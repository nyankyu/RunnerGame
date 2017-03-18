package jp.co.nd_inc.em.runnergame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

/**
 * Created by nao on 2017/03/18.
 */

class Background {
    private Bitmap image;

    Background(Context context) {
        image = BitmapFactory.decodeResource(context.getResources(), R.drawable.background);
    }

    void draw(Canvas canvas) {
        canvas.drawBitmap(image, 0, 0, null);
    }
}
