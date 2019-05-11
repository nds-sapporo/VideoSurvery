package jp.co.ndstyo.sapporo.spajam.videosurvey;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import jp.co.ndstyo.sapporo.spajam.videosurvey.backend.SurveyCallback;
import jp.co.ndstyo.sapporo.spajam.videosurvey.backend.SurveyListener;
import jp.co.ndstyo.sapporo.spajam.videosurvey.backend.Vote;
import jp.co.ndstyo.sapporo.spajam.videosurvey.backend.VoteCallback;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        final String userName = intent.getStringExtra("USERNAME");

        SurveyListener listener = SurveyListener.getInstance();
        listener.setCinemaStatusChangeCallback(SurveyListener.DEMO_CINEMA_1, new SurveyCallback() {
            @Override
            public void callback(String status) {
                Log.i("Status Changed", status);
            }
        });
        listener.setVoteChangeCallback(SurveyListener.DEMO_CINEMA_1, SurveyListener.DEMO_BRANCH_1, SurveyListener.DEMO_CHOICE_1, new VoteCallback() {
            @Override
            public void callback(int count, int money) {
                Log.i("Vote Summary", "[" + SurveyListener.DEMO_CHOICE_1 + "] count:" + count + ", money:" + money);
            }
        });
        listener.setVoteChangeCallback(SurveyListener.DEMO_CINEMA_1, SurveyListener.DEMO_BRANCH_1, SurveyListener.DEMO_CHOICE_2, new VoteCallback() {
            @Override
            public void callback(int count, int money) {
                Log.i("Vote Summary", "[" + SurveyListener.DEMO_CHOICE_2 + "] count:" + count + ", money:" + money);
            }
        });
        ImageView view = findViewById(R.id.imageViewA);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Vote vote = new Vote( userName, 100);
                SurveyListener.getInstance().vote(SurveyListener.DEMO_CINEMA_1, SurveyListener.DEMO_BRANCH_1, SurveyListener.DEMO_CHOICE_1, vote);
            }
        });
    }
}
