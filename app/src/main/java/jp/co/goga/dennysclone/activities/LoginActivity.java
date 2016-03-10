package jp.co.goga.dennysclone.activities;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.transition.Slide;
import android.transition.TransitionInflater;
import android.view.View;
import android.widget.ImageButton;

import jp.co.goga.dennysclone.R;

/**
 * Created by khanhtq on 3/10/16.
 */
public class LoginActivity extends AppCompatActivity {
    private ImageButton mCloseButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mCloseButton = (ImageButton) findViewById(R.id.close_login);
        mCloseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doFinish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        doFinish();
    }

    private void doFinish() {
        finish();
        overridePendingTransition(R.anim.stay, R.anim.slide_out);
    }
}
