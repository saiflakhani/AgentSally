package com.quicsolv.agentsally.arrayadapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.quicsolv.agentsally.R;
import com.quicsolv.agentsally.pojo.MessageAttributes;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Saif on 7/15/17.
 */

public class ChatAdapter extends ArrayAdapter<MessageAttributes>{

    Context context;
    private List<MessageAttributes> chatMessageList = new ArrayList<>();
    public ChatAdapter(Context context, int resource, List<MessageAttributes> chatMessageList) {
        super(context, resource,chatMessageList);
        this.context = context;
        this.chatMessageList = chatMessageList;
    }
    @Override
    public void add(MessageAttributes object) {
        chatMessageList.add(object);
        super.add(object);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        MessageAttributes currentMessage = getItem(position);
        View row = convertView;
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if(currentMessage.sending) {
            row = inflater.inflate(R.layout.chat_right_balloon, parent, false);
        }else{
            row = inflater.inflate(R.layout.chat_left_balloon,parent,false);
        }
        TextView chatToDisplay = (TextView)row.findViewById(R.id.msgr);
        chatToDisplay.setText(currentMessage.message);
        return row;
    }
}
