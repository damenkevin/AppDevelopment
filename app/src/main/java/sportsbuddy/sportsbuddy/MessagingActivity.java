package sportsbuddy.sportsbuddy;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by s165700 on 4/17/2018.
 */

public class MessagingActivity extends Activity {
    private static AppUser appUser;
    private String conversationID;
    private DatabaseHandler databaseHandler = DatabaseHandler.getDatabaseHandler();
    private ArrayList<Message> messages = new ArrayList<Message>();
    private MessagesAdapter messagesAdapter;
    private  GridView messagesGrid;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.messages_layout);
        TextView friendName = (TextView) findViewById(R.id.friendNameText);
        final EditText messageText = (EditText) findViewById(R.id.messageToSendText);
        messagesGrid = (GridView) findViewById(R.id.messagesGrid);
        Button sendMessageButton = (Button) findViewById(R.id.buttonSendText);
        messagesAdapter = new MessagesAdapter(messages,appUser,this);
        databaseHandler.createConversation(appUser, this);
        databaseHandler.getMyPicture(this);
        messagesGrid.setAdapter(messagesAdapter);
        sendMessageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(messageText.getText().toString().trim().equals("")){
                    Toast.makeText(MessagingActivity.this, "You cannot send an empty message", Toast.LENGTH_SHORT).show();
                } else {
                    String messageContent = messageText.getText().toString().trim();
                    sendMessage(messageContent);
                    messageText.setText("");
                }
            }
        });

        friendName.setText(appUser.getName());
    }

    public static void setAppUser(AppUser _appUser){
        appUser = _appUser;
    }

    public void setConversationID(String conversationID){
        this.conversationID = conversationID;
        databaseHandler.getMessages(conversationID,appUser,this);
    }

    public void setMessages(ArrayList<Message> messages){
        this.messages = messages;
        messagesAdapter.setMessages(messages);
        messagesAdapter.notifyDataSetChanged();
        messagesGrid.smoothScrollToPosition(messagesAdapter.getCount());
    }

    private void arrageMessagesByDate(){

    }

    private void sendMessage(String messageContent){
        String sender = FirebaseAuth.getInstance().getCurrentUser().getUid();
        DateFormat df = new SimpleDateFormat("HH:mm");
        String time = df.format(Calendar.getInstance().getTime());
        Message message = new Message(messageContent,sender,time);
        databaseHandler.sendMessage(message, conversationID,appUser,this);

    }

    public void setMyPic(String pic){
        Log.e("Pic is set", pic );
        messagesAdapter.setMyPic(pic);
        messagesAdapter.notifyDataSetChanged();
        messagesGrid.smoothScrollToPosition(messagesAdapter.getCount());
    }
}
