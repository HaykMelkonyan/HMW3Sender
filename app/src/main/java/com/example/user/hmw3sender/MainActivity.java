package com.example.user.hmw3sender;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private int sendInt;
    private String sendString;
    TextView intTxtView;
    TextView stringTxtView;
    public  static  final int RESULT_CODE=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sendInt=111;
        sendString="First String";
        intTxtView= (TextView)findViewById(R.id.intTxtView);
        stringTxtView=(TextView)findViewById(R.id.stringTextView);
        intTxtView.setText(String.valueOf(sendInt));
        stringTxtView.setText(sendString);

        Button btn= (Button)findViewById(R.id.shareButton);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent();//getPackageManager().getLaunchIntentForPackage("com.example.user.hmw3reciever");
                startIntent.setPackage("com.example.user.hmw3reciever");
                startIntent.putExtra("intValue", sendInt);
                startIntent.putExtra("stringValue", sendString);
                startIntent.setAction(Intent.ACTION_SEND_MULTIPLE);
                startIntent.setType("text/plain");
                startActivityForResult(startIntent,RESULT_CODE);
            }
        });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RESULT_CODE) {
            if (resultCode == RESULT_OK) {
                sendInt= data.getIntExtra("intValue",-1);
                sendString=data.getStringExtra("stringValue");

            }
        }
        intTxtView.setText(String.valueOf(sendInt));
        stringTxtView.setText(sendString);
    }
}
