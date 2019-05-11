package jp.co.ndstyo.sapporo.spajam.videosurvey;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import jp.co.ndstyo.sapporo.spajam.videosurvey.backend.MatchingCallback;
import jp.co.ndstyo.sapporo.spajam.videosurvey.backend.SurveyCallback;
import jp.co.ndstyo.sapporo.spajam.videosurvey.backend.SurveyListener;
import jp.co.ndstyo.sapporo.spajam.videosurvey.backend.User;
import jp.co.ndstyo.sapporo.spajam.videosurvey.backend.Vote;
import jp.co.ndstyo.sapporo.spajam.videosurvey.backend.VoteCallback;

public class Main2Activity extends AppCompatActivity {

    Context context;

    // 現在のクレジット
    Integer money = 0;
    Integer campaign = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        context = this.getApplicationContext();

        Intent intent = getIntent();
        final String userName = intent.getStringExtra("USERNAME");
        money = intent.getIntExtra("MONEY", 0);
        campaign = intent.getIntExtra("CAMPAIGN", 0);

        // 現在のクレジットの表示
        TextView textViewMoney = findViewById(R.id.textViewMoney);
        textViewMoney.setText(String.valueOf(money));

        SurveyListener listener = SurveyListener.getInstance();

        // 映像ステータスに対するコールバック
        listener.setCinemaStatusChangeCallback(SurveyListener.DEMO_CINEMA_2, new SurveyCallback() {
            @Override
            public void callback(String status) {
                // 待ち受け画面へ遷移
                if( status.equals(SurveyListener.DEMO_STATUS_next)) {
                    Intent intent = new Intent(Main2Activity.this, WaitActivity.class);
                    intent.putExtra("USERNAME", userName);
                    intent.putExtra("MONEY", money);
                    intent.putExtra("CAMPAIGN", campaign);
                    startActivity(intent);
                    finish();
                }
            }
        });

        // 投票サマリに対するコールバック
        listener.setVoteChangeCallback(SurveyListener.DEMO_CINEMA_2, SurveyListener.DEMO_BRANCH_1, SurveyListener.DEMO_CHOICE_1, new VoteCallback() {
            @Override
            public void callback(int count, int money) {
                TextView textViewCountA = findViewById(R.id.textViewCountA);
                TextView textViewMoneyA = findViewById(R.id.textViewMoneyA);
                textViewCountA.setText(String.valueOf(count));
                textViewMoneyA.setText(String.valueOf(money));
            }
        });
        listener.setVoteChangeCallback(SurveyListener.DEMO_CINEMA_2, SurveyListener.DEMO_BRANCH_1, SurveyListener.DEMO_CHOICE_2, new VoteCallback() {
            @Override
            public void callback(int count, int money) {
                TextView textViewCountB = findViewById(R.id.textViewCountB);
                TextView textViewMoneyB = findViewById(R.id.textViewMoneyB);
                textViewCountB.setText(String.valueOf(count));
                textViewMoneyB.setText(String.valueOf(money));
            }
        });

        // 投票ボタン
        ImageView view1 = findViewById(R.id.imageViewA);
        view1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if( money >= 100 ) {
                    Vote vote = new Vote(userName, 100);
                    SurveyListener.getInstance().vote(SurveyListener.DEMO_CINEMA_2, SurveyListener.DEMO_BRANCH_1, SurveyListener.DEMO_CHOICE_1, vote);
                    money = money - 100;
                    // 現在のクレジットの表示
                    TextView textViewMoney = findViewById(R.id.textViewMoney);
                    textViewMoney.setText(String.valueOf(money));
                }
                else {
                    if (campaign == 0) {
                        new AlertDialog.Builder(Main2Activity.this)
                                .setTitle("クレジットが不足しています")
                                .setMessage("新規ユーザーには500クレジットプレゼントキャンペーン実施中！クレジットを受け取りますか？")
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        // OK button pressed
                                        money = money + 500;
                                        campaign = 1;
                                        // 現在のクレジットの表示
                                        TextView textViewMoney = findViewById(R.id.textViewMoney);
                                        textViewMoney.setText(String.valueOf(money));
                                        Toast.makeText(getApplicationContext(), "500クレジット取得しました", Toast.LENGTH_SHORT).show();                                    }
                                })
                                .setNegativeButton("Cancel", null)
                                .show();
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "クレジットが不足しています", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        ImageView view2 = findViewById(R.id.imageViewB);
        view2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if( money >= 100 ) {
                    Vote vote = new Vote(userName, 100);
                    SurveyListener.getInstance().vote(SurveyListener.DEMO_CINEMA_2, SurveyListener.DEMO_BRANCH_1, SurveyListener.DEMO_CHOICE_2, vote);
                    money = money - 100;
                    // 現在のクレジットの表示
                    TextView textViewMoney = findViewById(R.id.textViewMoney);
                    textViewMoney.setText(String.valueOf(money));
                }
                else {
                    if (campaign == 0) {
                        new AlertDialog.Builder(Main2Activity.this)
                                .setTitle("クレジットが不足しています")
                                .setMessage("新規ユーザーには500クレジットプレゼントキャンペーン実施中！クレジットを受け取りますか？")
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        // OK button pressed
                                        money = money + 500;
                                        campaign = 1;
                                        // 現在のクレジットの表示
                                        TextView textViewMoney = findViewById(R.id.textViewMoney);
                                        textViewMoney.setText(String.valueOf(money));
                                        Toast.makeText(getApplicationContext(), "500クレジット取得しました", Toast.LENGTH_SHORT).show();
                                    }
                                })
                                .setNegativeButton("Cancel", null)
                                .show();
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "クレジットが不足しています", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }
}
