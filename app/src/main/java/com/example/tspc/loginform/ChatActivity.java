package com.example.tspc.loginform;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ChatActivity extends AppCompatActivity {
    static  final String MY_PREF ="new pref";



    ListView chatlist;
    EditText msg;
    Button  sendButtn;
    String uEmail;
    DatabaseReference ref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);



        chatlist = findViewById(R.id.chatlist);
        msg = findViewById( R.id.msg);
        sendButtn=findViewById(R.id.sendButton);

        Intent intent = getIntent();
        uEmail = intent.getStringExtra("email");

        ref = FirebaseDatabase.getInstance().getReference();

        sendButtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!msg.getText().toString().equals("")){

                    MyText myTexts = new MyText(uEmail,msg.getText().toString());
                    ref.child("ChatData").push().setValue(myTexts);
                    msg.setText("");
                }
            }
        });
        ChatAdapter adapter = new ChatAdapter(this,R.layout.single_row,ref);
        chatlist.setAdapter(adapter);

    }
}

