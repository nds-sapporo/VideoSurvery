package jp.co.ndstyo.sapporo.spajam.videosurvey.backend;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.UUID;

public class SurveyListener {

    public static final String DEMO_CINEMA_1 = "cinema_id_a";

    public static final String DEMO_BRANCH_1 = "branch_id";

    public static final String DEMO_CHOICE_1 = "a";

    public static final String DEMO_CHOICE_2 = "b";

    private DatabaseReference database;

    private static SurveyListener instance;

    private SurveyListener() {
        database = FirebaseDatabase.getInstance().getReference();
    }

    public static SurveyListener getInstance() {
        if (instance == null) {
            instance =new SurveyListener();
        }
        return instance;
    }

    public void vote(String movieId, String branchId, String choice, Vote vote) {
        String id = UUID.randomUUID().toString();
        database.child("vote").child(movieId).child(branchId).child(choice).child(id).setValue(vote);
    }

    public void setVoteChangeCallback(String movieId, String branchId, String choice, final VoteCallback callback) {
        database.child("vote").child(movieId).child(branchId).child(choice).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int count = 0;
                int sum = 0;
                for (DataSnapshot item : dataSnapshot.getChildren()) {
                    count++;
                    sum += item.child("money").getValue(int.class);
                }
                callback.callback(count, sum);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void setCinemaStatusChangeCallback(String movieId, final SurveyCallback callback) {
        database.child("cinema").child(movieId).child("status").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                callback.callback(dataSnapshot.getValue(String.class));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
