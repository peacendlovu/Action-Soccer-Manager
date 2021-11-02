package com.example.asm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import org.jetbrains.annotations.NotNull;
import java.io.IOException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;





public class leaderboard_activity extends AppCompatActivity {
    TextView leaderboard;
    OkHttpClient client  = new OkHttpClient();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.leaderboard_layout);
        super.onCreate(savedInstanceState);
        leaderboard = (TextView)findViewById(R.id.Leaderboard);
        String url ="https://lamp.ms.wits.ac.za/home/s2096330/leaderboard.php";
        Request request = new Request.Builder()
                .url(url)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                if(response.isSuccessful()){
                    String myResponse = response.body().string();

                    leaderboard_activity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            leaderboard.setText(myResponse);
                        }
                    });
                }else {
                    //Request not successful
                }
            }
        });
    }


}
