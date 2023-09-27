package com.example.openaichatbox;

import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ViewHolder> {
    List<ChatMessage> messageList;
    public ChatAdapter(List<ChatMessage> messageList){
        this.messageList = messageList;
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        // get reference to the view of the list item
        public LinearLayout rightChatView;
        public LinearLayout leftChatView;
        public TextView queryText;
        public TextView responseText;
        public ViewHolder(View itemView){
            super(itemView);
            rightChatView = itemView.findViewById(R.id.rightChatView);
            leftChatView = itemView.findViewById(R.id.leftChatView);
            queryText = (TextView) itemView.findViewById(R.id.queryTextView);
            responseText= (TextView) itemView.findViewById(R.id.responseTextView);
        }
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // inflate the layout to recycle view by creating instance of view holder
        View chatView = LayoutInflater.from(parent.getContext()).inflate(R.layout.query_response_item, parent, false);
        ViewHolder viewHolder =  new ViewHolder(chatView);
        return viewHolder;
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // bind view holder with data
        // data will be fetch through api
        ChatMessage chatMessage = messageList.get(position);
        if(chatMessage.getRole().equals(ChatMessage.SENT_BY_ME)){
//            message sent by me
            holder.leftChatView.setVisibility(View.GONE);
            holder.rightChatView.setVisibility(View.VISIBLE);
            holder.queryText.setText(chatMessage.getContent());
        }else{
//            message sent by bot
            holder.rightChatView.setVisibility(View.GONE);
            holder.leftChatView.setVisibility(View.VISIBLE);
            holder.responseText.setText(chatMessage.getContent());
        }
    }
    @Override
    public int getItemCount() {
        return messageList.size();
    }


}
