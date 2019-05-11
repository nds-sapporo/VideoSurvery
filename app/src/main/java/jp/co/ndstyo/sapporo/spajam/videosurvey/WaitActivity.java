package jp.co.ndstyo.sapporo.spajam.videosurvey;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import jp.co.ndstyo.sapporo.spajam.videosurvey.backend.SurveyCallback;
import jp.co.ndstyo.sapporo.spajam.videosurvey.backend.SurveyListener;

public class WaitActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wait);

        Intent intent = getIntent();
        final String userName = intent.getStringExtra("USERNAME");
        final Integer money = intent.getIntExtra("MONEY", 0);
        final Integer campaign = intent.getIntExtra("CAMPAIGN", 0);

        // 映像ステータスに対するコールバックを待ち受ける
        SurveyListener listener = SurveyListener.getInstance();
        listener.setCinemaStatusChangeCallback(SurveyListener.DEMO_CINEMA_1, new SurveyCallback() {
            @Override
            public void callback(String status) {
                // 分岐選択画面へ遷移
                if( status.equals(SurveyListener.DEMO_STATUS_vote)) {
                    Intent intent = new Intent(WaitActivity.this, MainActivity.class);
                    intent.putExtra("USERNAME", userName);
                    intent.putExtra("MONEY", money);
                    intent.putExtra("CAMPAIGN", campaign);
                    startActivity(intent);
                    finish();
                }
            }
        });

        listener.setCinemaStatusChangeCallback(SurveyListener.DEMO_CINEMA_2, new SurveyCallback() {
            @Override
            public void callback(String status) {
                // 分岐選択画面へ遷移
                if( status.equals(SurveyListener.DEMO_STATUS_vote)) {
                    Intent intent = new Intent(WaitActivity.this, Main2Activity.class);
                    intent.putExtra("USERNAME", userName);
                    intent.putExtra("MONEY", money);
                    intent.putExtra("CAMPAIGN", campaign);
                    startActivity(intent);
                    finish();
                }
            }
        });

        listener.setCinemaStatusChangeCallback(SurveyListener.DEMO_CINEMA_3, new SurveyCallback() {
            @Override
            public void callback(String status) {
                // 分岐選択画面へ遷移
                if( status.equals(SurveyListener.DEMO_STATUS_vote)) {
                    Intent intent = new Intent(WaitActivity.this, Main3Activity.class);
                    intent.putExtra("USERNAME", userName);
                    intent.putExtra("MONEY", money);
                    intent.putExtra("CAMPAIGN", campaign);
                    startActivity(intent);
                    finish();
                }
            }
        });

        listener.setCinemaStatusChangeCallback("cinema_id_d", new SurveyCallback() {
            @Override
            public void callback(String status) {
                // 分岐選択画面へ遷移
                if( status.equals(SurveyListener.DEMO_STATUS_next)) {
                    Intent intent = new Intent(WaitActivity.this, EndActivity.class);
                    intent.putExtra("USERNAME", userName);
                    intent.putExtra("MONEY", money);
                    intent.putExtra("CAMPAIGN", campaign);
                    startActivity(intent);
                    finish();
                }
            }
        });
        listener.setCinemaStatusChangeCallback("cinema_id_e", new SurveyCallback() {
            @Override
            public void callback(String status) {
                // 分岐選択画面へ遷移
                if( status.equals(SurveyListener.DEMO_STATUS_next)) {
                    Intent intent = new Intent(WaitActivity.this, EndActivity.class);
                    intent.putExtra("USERNAME", userName);
                    intent.putExtra("MONEY", money);
                    intent.putExtra("CAMPAIGN", campaign);
                    startActivity(intent);
                    finish();
                }
            }
        });
        listener.setCinemaStatusChangeCallback("cinema_id_f", new SurveyCallback() {
            @Override
            public void callback(String status) {
                // 分岐選択画面へ遷移
                if( status.equals(SurveyListener.DEMO_STATUS_next)) {
                    Intent intent = new Intent(WaitActivity.this, EndActivity.class);
                    intent.putExtra("USERNAME", userName);
                    intent.putExtra("MONEY", money);
                    intent.putExtra("CAMPAIGN", campaign);
                    startActivity(intent);
                    finish();
                }
            }
        });
        listener.setCinemaStatusChangeCallback("cinema_id_g", new SurveyCallback() {
            @Override
            public void callback(String status) {
                // 分岐選択画面へ遷移
                if( status.equals(SurveyListener.DEMO_STATUS_next)) {
                    Intent intent = new Intent(WaitActivity.this, EndActivity.class);
                    intent.putExtra("USERNAME", userName);
                    intent.putExtra("MONEY", money);
                    intent.putExtra("CAMPAIGN", campaign);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }
}
