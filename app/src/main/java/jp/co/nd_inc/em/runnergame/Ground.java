package jp.co.nd_inc.em.runnergame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;


class Ground {
    private Bitmap block;
    private int[] level = {1,1,1,1,1,1,1,1,1};
    private float offsetX;

    Ground(Context context) {
        block = BitmapFactory.decodeResource(context.getResources(), R.drawable.block);
        offsetX = 0;
    }

    void draw(Canvas canvas) {
        offsetX += 5;

        for (int i = 0; i < level.length; i++) {
            float x = 100 * i - offsetX;
            float y = GameView.HEIGHT - 100 * level[i];
            canvas.drawBitmap(block, x, y, null);
        }
    }
}
