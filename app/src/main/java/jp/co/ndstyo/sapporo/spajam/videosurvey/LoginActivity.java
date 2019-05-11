package jp.co.ndstyo.sapporo.spajam.videosurvey;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity  extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button button = (Button)findViewById(R.id.ButtonOK);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // input check
                EditText editText = (EditText) findViewById(R.id.editTextName);
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                String userName = editText.getText().toString();
                intent.putExtra("USERNAME", userName);
                startActivity(intent);
            }
        });
    }
}
