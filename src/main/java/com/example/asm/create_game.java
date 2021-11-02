package com.example.asm;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import java.util.HashMap;

public class create_game extends AppCompatActivity {


    String finalResult ;
    String HttpURL = "https://lamp.ms.wits.ac.za/home/s2096330/players.php";
    Boolean CheckEditText ;
    ProgressDialog progressDialog;
    HashMap<String,String> hashMap = new HashMap<>();
    http httpParse = new http();

    EditText player1,player2,player3,player4,player5,team;
    String  player1str ,player2str ,player3str ,player4str ,player5str, teamstr;
    Button submit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_game);

        //Assign Id'S
        team = (EditText)findViewById(R.id.addyourTeam);
        player1 = (EditText)findViewById(R.id.enterPlayer1);
        player2 = (EditText)findViewById(R.id.enterPlayer2);
        player3 = (EditText)findViewById(R.id.enterPlayer3);
        player4 = (EditText)findViewById(R.id.enterPlayer4);
        player5 = (EditText)findViewById(R.id.enterPlayer5);
        submit = (Button)findViewById(R.id.submit2);

        //Adding Click Listener on button.
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Checking whether EditText is Empty or Not
                CheckEditTextIsEmptyOrNot();

                if(CheckEditText){

                    // If EditText is not empty and CheckEditText = True then this block will execute.

                    UserRegisterFunction(player1str,player2str,player3str,player4str,player5str,teamstr);

                }
                else {

                    // If EditText is empty then this block will execute .
                    Toast.makeText(com.example.asm.create_game.this, "Please fill all form fields.", Toast.LENGTH_LONG).show();

                }


            }
        });

    }

    public void CheckEditTextIsEmptyOrNot(){

        player1str = player1.getText().toString();
        player2str = player2.getText().toString();
        player3str = player3.getText().toString();
        player4str = player4.getText().toString();
        player5str = player5.getText().toString();
        teamstr = team.getText().toString();
        if(TextUtils.isEmpty(player1str) || TextUtils.isEmpty(player2str) || TextUtils.isEmpty(player3str) || TextUtils.isEmpty(player4str) || TextUtils.isEmpty(player5str) || TextUtils.isEmpty(teamstr))
        {

            CheckEditText = false;

        }
                else {

            CheckEditText = true ;
        }

    }

    public void UserRegisterFunction(final String player1str, final String player2str, final String player3str, final String player4str, final String player5str, final String teamstr){

        class UserRegisterFunctionClass extends AsyncTask<String,Void,String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();

                progressDialog = ProgressDialog.show(com.example.asm.create_game.this,"Loading Data",null,true,true);
            }

            @Override
            protected void onPostExecute(String httpResponseMsg) {

                super.onPostExecute(httpResponseMsg);

                progressDialog.dismiss();

                Toast.makeText(com.example.asm.create_game.this,httpResponseMsg.toString(), Toast.LENGTH_LONG).show();

            }

            @Override
            protected String doInBackground(String... params) {

                hashMap.put("player1",params[0]);

                hashMap.put("player2",params[1]);

                hashMap.put("player3",params[2]);

                hashMap.put("player4",params[3]);

                hashMap.put("player5",params[4]);

                hashMap.put("team",params[5]);

                finalResult = httpParse.postRequest(hashMap, HttpURL);

                return finalResult;
            }
        }

        UserRegisterFunctionClass userRegisterFunctionClass = new UserRegisterFunctionClass();

        userRegisterFunctionClass.execute(player1str,player2str,player3str,player4str,player5str, teamstr);
    }

}
