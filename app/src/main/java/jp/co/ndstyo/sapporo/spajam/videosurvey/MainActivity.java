package jp.co.ndstyo.sapporo.spajam.videosurvey;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.List;

import jp.co.ndstyo.sapporo.spajam.videosurvey.backend.MatchingCallback;
import jp.co.ndstyo.sapporo.spajam.videosurvey.backend.SurveyCallback;
import jp.co.ndstyo.sapporo.spajam.videosurvey.backend.SurveyListener;
import jp.co.ndstyo.sapporo.spajam.videosurvey.backend.User;
import jp.co.ndstyo.sapporo.spajam.videosurvey.backend.Vote;
import jp.co.ndstyo.sapporo.spajam.videosurvey.backend.VoteCallback;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
        Button view = findViewById(R.id.imageViewA);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Vote vote = new Vote("sasaki", 200);
                SurveyListener.getInstance().vote(SurveyListener.DEMO_CINEMA_1, SurveyListener.DEMO_BRANCH_1, SurveyListener.DEMO_CHOICE_1, vote);
            }
        });
        Button button = findViewById(R.id.imageViewB);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SurveyListener.getInstance().findMatchingUsers(SurveyListener.DEMO_CINEMA_1, "taro", new MatchingCallback() {
                    @Override
                    public void callback(List<User> matchingUsers) {
                        for (User user : matchingUsers) {
                            Log.i("Matching Result:", user.getUserName());
                        }
                    }
                });
            }
        });
    }
}
