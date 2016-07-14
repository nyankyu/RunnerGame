package jp.co.nd_inc.em.runnergame;

import android.content.Context;
import android.graphics.Canvas;

import jp.co.nd_inc.em.runnergame.game_object.Background;
import jp.co.nd_inc.em.runnergame.game_object.Ground;
import jp.co.nd_inc.em.runnergame.game_object.Player;
import jp.co.nd_inc.em.runnergame.game_object.Score;

class GameLogic {
    private Player mPlayer;
    private Ground mGround;
    private Score mScore;
    private Background mBackground;


    GameLogic(Context context) {
        mPlayer = new Player(context);
        mBackground = new Background(context);
        mGround = new Ground(context);
        mScore = new Score(context);
    }

    void draw(Canvas canvas) {
        mBackground.draw(canvas);
        mGround.draw(canvas);
        mScore.draw(canvas);

        mPlayer.setGroundY(mGround.getY());
        mPlayer.draw(canvas);

    }

    void onTouchDown() {
        mPlayer.upStart();
    }

    void onTouchUp() {
        mPlayer.upEnd();
    }

}
