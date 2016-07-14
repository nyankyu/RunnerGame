package jp.co.nd_inc.em.runnergame.game_object;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;

import jp.co.nd_inc.em.runnergame.Util;

/**
 * Created by nao on 2016/05/21.
 */
public class Background {
    private Bitmap mBitmap;

    public Background(Context context) {
        mBitmap = Util.readBitmap(context, "background");
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(mBitmap, 0f, 0f, null);
    }
}
