package com.hfad.mymessenger;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;

public class CreateMessageActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_message);
    }

    public void onSendMessage(View view){
        EditText mesText = (EditText) findViewById(R.id.text);
        String mes = mesText.getText().toString();
        /*Intent messageintent = new Intent(this,ResiverActivity.class);
        messageintent.putExtra(ResiverActivity.MESSAGE,mes);*/
        Intent messageintent = new Intent(Intent.ACTION_SEND);
        messageintent.setType("text/plain");
        messageintent.putExtra(Intent.EXTRA_TEXT,mes);
        String title = getString(R.string.chooser);
        Intent choosenIntent = Intent.createChooser(messageintent,title);
        startActivity(choosenIntent);
    }

}
