package jp.co.nd_inc.em.runnergame.game_object;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.media.AudioManager;
import android.media.SoundPool;
import android.util.Log;

import jp.co.nd_inc.em.runnergame.R;
import jp.co.nd_inc.em.runnergame.Util;

public class Player {
    private static final String TAG = "Player";

    private Bitmap[] mBitmap;
    private int mFrameCounter;
    private int mCurrentIndex;

    private float mPositionY;
    private float mVelocity; // 速度
    private float mAcceleration; // 加速度

    private boolean mJumping;

    private float mGroundY;
    private SoundPool mSoundPool;
    private int mJumpSound;

    private static final float BITMAP_SIZE = 100;

    private static final float GRAVITY = 1.4f;
    private static final float JUMP_POWER = -2.3f;
    private static final float MAX_V = 18;


    public Player(Context context) {
        // ビットマプをロード
        mBitmap = new Bitmap[2];
        mBitmap[0] = Util.readBitmap(context, "player1");
        mBitmap[1] = Util.readBitmap(context, "player2");

        mFrameCounter = 0;

        mCurrentIndex = 0;
        mPositionY = 300;
        mVelocity = 0;
        mAcceleration = 0;

        mJumping = false;

        mSoundPool = new SoundPool(3, AudioManager.STREAM_MUSIC, 0);
        mJumpSound = mSoundPool.load(context, R.raw.se_jump, 1);
    }

    public void draw(Canvas canvas) {
        // 物理演算
        mVelocity -= mAcceleration;
        mPositionY -= mVelocity;

        // ジャンプ力上限
        if (mVelocity > MAX_V) {
            mAcceleration = GRAVITY;
        }

        // 接地
        if (mPositionY >= mGroundY) {
            mPositionY = mGroundY;
            mVelocity = 0;
            mAcceleration = 0;
            mJumping = false;
        } else {
            if (!mJumping) {
                mJumping = true;
                mAcceleration = GRAVITY;
            }
        }

        selectBitmap();

        canvas.drawBitmap(mBitmap[mCurrentIndex], 150, mPositionY - BITMAP_SIZE, null);

    }

    /**
     * 20フレーム毎に表示用ビットマプを変える。
     */
    private void selectBitmap() {
        mFrameCounter++;

        if (mFrameCounter < 20) {
            return;
        }

        mCurrentIndex = mCurrentIndex == 1 ? 0 : 1;
        mFrameCounter = 0;
    }

    public void upStart() {
        Log.i(TAG, "UP Start");

        if (mJumping) {
            return;
        }

        mJumping = true;
        mAcceleration = JUMP_POWER;

        mSoundPool.play(mJumpSound, 1.0f, 1.0f, 1, 0, 1.0f);
    }

    public void upEnd() {
        Log.i(TAG, "UP End");
        mAcceleration = GRAVITY;
    }

    public void setGroundY(float y) {
        mGroundY = y;
    }

}



