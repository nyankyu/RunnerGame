package jp.co.nd_inc.em.runnergame.game_object;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;

import jp.co.nd_inc.em.runnergame.Util;

public class Ground {
    private static final int TILE_SIZE = 100;
    private static final int FRAME_PAR_Y = 7;

    private Bitmap mTile;

    private int[] mTileHeight = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    private int mDiffX = 0;

    public Ground(Context context) {
        mTile = Util.readBitmap(context, "block");
    }

    public void draw(Canvas canvas) {
        for (int i = 0; i < mTileHeight.length; i++) {
            canvas.drawBitmap(
                    mTile,
                    TILE_SIZE * i - mDiffX,
                    400 - (TILE_SIZE * mTileHeight[i]),
                    null);
        }

        mDiffX += FRAME_PAR_Y;

        if (mDiffX > TILE_SIZE) {
            mDiffX = 0;
            for (int i = 0; i < mTileHeight.length-1; i++) {
                mTileHeight[i] = mTileHeight[i+1];
            }

            setNextFrame();
        }
    }

    void setNextFrame() {
        // 直前が穴なら高さを変えない
        if (mTileHeight[8] == -1) {
            mTileHeight[9] = mTileHeight[7];
            return;
        }

        //
        if (mTileHeight[8] != mTileHeight[7]) {
            mTileHeight[9] = mTileHeight[8];
            return;
        }

        // 高さを変えない
        if (Util.lotteryMachine(30)) {
            mTileHeight[9] = mTileHeight[8];
            return;
        }

        // 穴
        if (Util.lotteryMachine(20)) {
            mTileHeight[9] = -1;
            return;
        }

        mTileHeight[9] = mTileHeight[8] +
                (Util.lotteryMachine(50) ? 1 : -1);

        if (mTileHeight[9] >= 4) {
            mTileHeight[9] = 3;
        } else if (mTileHeight[9] == -1) {
            mTileHeight[9] = 1;
        }
    }

    public float getY() {
        return (4 - mTileHeight[2] ) * TILE_SIZE;
    }
}
