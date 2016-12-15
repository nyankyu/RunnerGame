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

        // 描画位置を少しずつずらす
        offsetX += 5;

        // 1ブロック分ずれたら
        if (offsetX > 100) {
            offsetX = 0;

            // 先頭に向けて1要素シフト
            System.arraycopy(level, 1, level, 0, level.length - 1);

            // 最後の要素に新たなブロックを追加
            int newLevel = makeNewLevel();
            level[level.length - 1] = newLevel;
        }

        // 全てのブロックを描画する
        for (int i = 0; i < level.length; i++) {
            // 穴
            if (level[i] == -1) continue;

            float x = 100 * i - offsetX;
            float y = GameView.HEIGHT - 100 * level[i];

            canvas.drawBitmap(block, x, y, null);
        }
    }

    private int makeNewLevel() {
        int newLevel = 1;

        // 穴
        if (Utility.lotteryMachine(0.2f)) {
            newLevel = -1;
            return newLevel;
        }

        return newLevel;
    }

}
