package jp.co.nd_inc.em.runnergame;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // フルスクリーン表示の設定
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        // ゲームViewを設置
        setContentView(new GameView(this));
    }

}
