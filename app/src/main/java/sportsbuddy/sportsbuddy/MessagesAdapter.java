package sportsbuddy.sportsbuddy;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by s165700 on 4/18/2018.
 */

public class MessagesAdapter extends BaseAdapter {
    ArrayList<Message> messages;
    MessagingActivity messagingActivity;
    AppUser user;
    Bitmap userPic;
    Bitmap myBitmap;

    public MessagesAdapter(ArrayList<Message> messages, AppUser user, MessagingActivity messagingActivity) {
        this.user = user;
        this.messages = messages;
        this.messagingActivity = messagingActivity;
        String profPic = user.getprofilePicture();
        byte[] bytes = new byte[0];
        if (profPic != "") {
            try {
                bytes = Base64.decode(profPic,Base64.DEFAULT);
            } catch(Exception e) {
                e.getMessage();
            }
        }
        userPic = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
    }

    @Override
    public int getCount() {
        return messages.size();
    }

    @Override
    public Object getItem(int i) {
        return messages.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater layoutInflater = (LayoutInflater) messagingActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = new View(messagingActivity);
        if(messages.get(i).getSender().equals(user.getUID())){
            //The sending user is the other user
            view = layoutInflater.inflate(R.layout.message_item_other, null);
            TextView messageText = (TextView) view.findViewById(R.id.messageTextOther);
            ImageView profilePic = (ImageView) view.findViewById(R.id.messagePic);
            if (userPic == null) {

            } else {
                profilePic.setImageBitmap(userPic);
            }
            messageText.setText(messages.get(i).getMsgContent());
        } else {
            //The sending user is the current user
            view = layoutInflater.inflate(R.layout.message_item_current, null);
            TextView messageText = (TextView) view.findViewById(R.id.messageTextCurrent);
            ImageView profilePic = (ImageView) view.findViewById(R.id.messagePicCurrent);
            if (myBitmap == null) {

            } else {
                profilePic.setImageBitmap(myBitmap);
            }
            messageText.setText(messages.get(i).getMsgContent());
        }
        return view;
    }

    public void setMessages(ArrayList<Message> messages){
        this.messages = messages;
    }

    public void setMyPic(String myPic){
        myPic = user.getprofilePicture();
        byte[] bytes = new byte[0];
        if (myPic != "") {
            try {
                bytes = Base64.decode(myPic,Base64.DEFAULT);
            } catch(Exception e) {
                e.getMessage();
            }
        }
        myBitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
    }
}
