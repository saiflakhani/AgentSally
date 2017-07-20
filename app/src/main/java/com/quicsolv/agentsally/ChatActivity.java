package com.quicsolv.agentsally;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AbsListView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import com.quicsolv.agentsally.arrayAdapters.ChatAdapter;
import com.quicsolv.agentsally.pojo.MessageAttributes;

import java.util.ArrayList;

public class ChatActivity extends Activity {

    ImageView btnSend;
    ListView lVMessages;
    EditText eTtypeMessage;
    ChatAdapter chatAdapter;
    ArrayList<MessageAttributes> chatList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_chat);
        chatAdapter = new ChatAdapter(this, R.layout.chat_right_balloon,chatList);
        getIds();
        setOnClickListeners();

    }


    private void getIds() {
        btnSend = (ImageView) findViewById(R.id.btnSendMsg);
        lVMessages = (ListView) findViewById(R.id.lVtexts);
        eTtypeMessage = (EditText) findViewById(R.id.eTtypeMessage);
    }

    private void setOnClickListeners() {
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage();
            }
        });
        lVMessages.setTranscriptMode(AbsListView.TRANSCRIPT_MODE_ALWAYS_SCROLL);
        lVMessages.setAdapter(chatAdapter);

    }


    public void sendMessage() {
        String messageText = eTtypeMessage.getText().toString();

        MessageAttributes newMessage = new MessageAttributes(true, messageText);
        chatList.add(newMessage);
        chatAdapter.notifyDataSetChanged();
        eTtypeMessage.setText("");
        MessageAttributes replyMessage = new MessageAttributes(false,messageText);
        chatList.add(replyMessage);
        chatAdapter.notifyDataSetChanged();
        //scrollListViewToBottom();

    }

    /*private void scrollListViewToBottom() {
        lVMessages.post(new Runnable() {
            @Override
            public void run() {
                // Select the last row so it will scroll into view...
                lVMessages.setSelection(lVMessages.getCount() - 1);
            }
        });
    }*/

}
