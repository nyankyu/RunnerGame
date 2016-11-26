package jp.co.nd_inc.em.runnergame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;


class Ground {
    private Bitmap block;
    private int[] level = {1,1,2,2,3,3,4,4,4};
    private float offsetX;

    Ground(Context context) {
        block = BitmapFactory.decodeResource(context.getResources(), R.drawable.block);
        offsetX = 0;
    }

    void draw(Canvas canvas) {
        offsetX += 5;
        if (offsetX > 100) {
            offsetX = 0;

            int top = level[0];
            System.arraycopy(level, 1, level, 0, level.length-1);

            if (Utility.lotteryMachine(0.1f)) {
                level[level.length - 1] = -1;
            } else {
                level[level.length - 1] = 1;
            }
        }

        for (int i = 0; i < level.length; i++) {
            if (level[i] == -1) continue;

            float x = 100 * i - offsetX;
            float y = GameView.HEIGHT - 100 * level[i];
            canvas.drawBitmap(block, x, y, null);
        }
    }
}
