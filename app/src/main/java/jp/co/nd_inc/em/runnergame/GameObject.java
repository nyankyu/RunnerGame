package jp.co.nd_inc.em.runnergame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

/**
 * Created by nao on 2016/09/17.
 */
public class GameObject {
    private int positionX;
    private int positionY;

    private Bitmap bitmap;

    public GameObject(Context context, int resId) {
        bitmap = BitmapFactory.decodeResource(context.getResources(), resId);
    }

    public void draw(Canvas canvas) {



        canvas.drawBitmap(bitmap, positionX, positionY, null);
        return;
    }
}
