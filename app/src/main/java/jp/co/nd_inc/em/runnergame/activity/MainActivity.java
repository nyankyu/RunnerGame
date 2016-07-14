package jp.co.nd_inc.em.runnergame.activity;

import android.app.Activity;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import jp.co.nd_inc.em.runnergame.GameView;
import jp.co.nd_inc.em.runnergame.R;

public class MainActivity extends Activity {

    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // フルスクリーン表示の設定
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        // BGM用音楽ファイルを読み込み
        mediaPlayer = MediaPlayer.create(this, R.raw.bgm);
        mediaPlayer.setLooping(true);

        // ゲームViewを設置
        setContentView(new GameView(this));
    }

    @Override
    protected void onResume() {
        super.onResume();

        mediaPlayer.start();
    }

    @Override
    protected void onPause() {
        super.onPause();

        mediaPlayer.stop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        mediaPlayer.release();
    }
}
