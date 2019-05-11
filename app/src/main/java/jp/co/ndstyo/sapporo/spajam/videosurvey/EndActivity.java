package jp.co.ndstyo.sapporo.spajam.videosurvey;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import jp.co.ndstyo.sapporo.spajam.videosurvey.backend.MatchingCallback;
import jp.co.ndstyo.sapporo.spajam.videosurvey.backend.SurveyListener;
import jp.co.ndstyo.sapporo.spajam.videosurvey.backend.User;

public class EndActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end);

        Intent intent = getIntent();
        final String userName = intent.getStringExtra("USERNAME");


        SurveyListener.getInstance().findMatchingUsers(SurveyListener.DEMO_CINEMA_2, userName, new MatchingCallback() {
            @Override
            public void callback(List<User> matchingUsers) {

                // マッチングしたユーザーがいなければ終了
                if( matchingUsers.size() == 0 ) {
                    return;
                }

                // マッチングしたユーザー名をリストビューに表示する
                ListView listView = (ListView) findViewById(R.id.listViewMatchingUsers);
                ArrayList<User> list = (ArrayList<User>) matchingUsers;
                MyAdapter myAdapter = new MyAdapter(EndActivity.this);
                myAdapter.setFoodList(list);
                listView.setAdapter(myAdapter);
            }
        });

        SurveyListener.getInstance().findMatchingUsers(SurveyListener.DEMO_CINEMA_3, userName, new MatchingCallback() {
            @Override
            public void callback(List<User> matchingUsers) {

                // マッチングしたユーザーがいなければ終了
                if( matchingUsers.size() == 0 ) {
                    return;
                }

                // マッチングしたユーザー名をリストビューに表示する
                ListView listView = (ListView) findViewById(R.id.listViewMatchingUsers);
                ArrayList<User> list = (ArrayList<User>) matchingUsers;
                MyAdapter myAdapter = new MyAdapter(EndActivity.this);
                myAdapter.setFoodList(list);
                listView.setAdapter(myAdapter);
            }
        });

        Button button = (Button)findViewById(R.id.buttonFinish);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
