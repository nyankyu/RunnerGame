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
        int newLevel;
        int preIndex = level.length - 2;

        // 穴の左右は同じ高さ
        if (level[preIndex] == -1) {
            newLevel = level[preIndex - 1];
            return newLevel;
        }

        // 穴を空ける
        if (Utility.lotteryMachine(0.2f)) {
            newLevel = -1;
            return newLevel;
        }

        // 2つ連続で高さを変えない
        if (level[preIndex] != level[preIndex - 1]) {
            newLevel = level[preIndex];
            return newLevel;
        }

        // 確率に従って「1上がる」、「変わらない」、「1下がる」
        if (Utility.lotteryMachine(0.2f)) {
            // 変わらない
            newLevel = level[preIndex];
        } else if (Utility.lotteryMachine(0.5f /* 上がるか下がるかは半々 */)) {
            // 1上がる
            newLevel = level[preIndex] + 1;
        } else {
            // 1下がる
            newLevel = level[preIndex] + -1;
        }

        // 範囲外になっていたら修正
        if (newLevel == 0) {
            newLevel = 2;
        } else if (newLevel == 5) {
            newLevel = 3;
        }

        return newLevel;
    }

    public int getGroundPosition() {
        int currentLevel;
        if (offsetX < 35) {
            currentLevel = level[0];
        } else if (offsetX < 70){
            currentLevel = level[0] > level[1] ? level[0] : level[1];
        } else {
            currentLevel = level[1];
        }
        return (int) (GameView.HEIGHT - currentLevel * 100);
    }

}
