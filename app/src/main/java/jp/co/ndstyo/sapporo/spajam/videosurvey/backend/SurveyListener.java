package jp.co.ndstyo.sapporo.spajam.videosurvey.backend;

import android.support.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public class SurveyListener {

    public static final String DEMO_CINEMA_1 = "cinema_id_a";

    public static final String DEMO_CINEMA_2 = "cinema_id_b";

    public static final String DEMO_CINEMA_3 = "cinema_id_c";

    public static final String DEMO_BRANCH_1 = "branch_id";

    public static final String DEMO_CHOICE_1 = "a";

    public static final String DEMO_CHOICE_2 = "b";

    public static final String DEMO_STATUS_vote = "vote";

    public static final String DEMO_STATUS_next = "end";

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

    public List<User> findMatchingUsers(String movieId, final String userName, final MatchingCallback callback) {
        List<User> users = new ArrayList<>();

        database.child("vote").child(movieId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                Map<String, Set<String>> summary = new HashMap<>();

                Set<String> targetUserKey = new HashSet<>();
                for (DataSnapshot branch : dataSnapshot.getChildren()) {
                    for (DataSnapshot choice : branch.getChildren()) {
                        String key = branch.getKey() + "-" + choice.getKey();
                        for (DataSnapshot item : choice.getChildren()) {
                            String n = item.child("userName").getValue(String.class);
                            if (n.equals(userName)) {
                                targetUserKey.add(key);
                            } else {
                                if (!summary.containsKey(key)) {
                                    summary.put(key, new HashSet<String>());
                                }
                                Set<String> targets = summary.get(key);
                                targets.add(n);
                            }
                        }
                    }
                }
                Set<String> users = null;
                for (String key : targetUserKey) {
                    if (users == null) {
                        users = summary.get(key);
                    } else {
                        users.retainAll(summary.get(key));
                    }
                }

                // usersが無課金の場合はnullになるので、matchUserを探さずに抜ける
                List<User> matchUsers = new ArrayList<>();

                if( users != null ) {
                    for (String user : users) {
                        matchUsers.add(new User(user));
                    }
                }

                callback.callback(matchUsers);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return users;
    }
}
