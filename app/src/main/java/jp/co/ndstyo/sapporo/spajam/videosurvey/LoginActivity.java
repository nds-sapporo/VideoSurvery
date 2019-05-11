package jp.co.ndstyo.sapporo.spajam.videosurvey;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
                if( editText.getText().toString().equals("") == true ) {
                    Toast.makeText(getApplicationContext(), "名前を入力してください", Toast.LENGTH_SHORT).show();
                    return;
                }

                Intent intent = new Intent(LoginActivity.this, WaitActivity.class);
                String userName = editText.getText().toString();
                intent.putExtra("USERNAME", userName);
                intent.putExtra("MONEY", 1000);
                Toast.makeText(getApplicationContext(), "1000クレジットを取得しました", Toast.LENGTH_LONG).show();
                startActivity(intent);
                finish();
            }
        });
    }
}
