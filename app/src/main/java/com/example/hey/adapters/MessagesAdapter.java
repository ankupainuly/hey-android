package com.example.hey.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.hey.R;
import com.example.hey.databinding.ItemReceiveBinding;
import com.example.hey.databinding.ItemSendBinding;
import com.example.hey.models.Message;
import com.google.firebase.auth.FirebaseAuth;


import java.util.ArrayList;
public class MessagesAdapter extends RecyclerView.Adapter{

    Context context;
    ArrayList<com.example.hey.models.Message> messages;

    final int ITEM_SEND = 1;
    final int ITEM_RECEIVE = 2;

    public MessagesAdapter(Context context ,ArrayList<com.example.hey.models.Message> messages) {

        this.context = context;
        this.messages = messages;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == ITEM_SEND) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_send ,parent ,false);
            return new SendViewHolder(view);
        }
        else {
            View view = LayoutInflater.from(context).inflate(R.layout.item_receive ,parent ,false);
            return new ReceiveViewHolder(view);
        }
    }

    @Override
    public int getItemViewType(int position) {
        Message message = messages.get(position);

        if (FirebaseAuth.getInstance().getUid().equals(message.getSenderId())) {
            return ITEM_SEND;
        }
        else {
            return ITEM_RECEIVE;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        Message message = messages.get(position);
        if (holder.getClass().equals(SendViewHolder.class)) {
            SendViewHolder viewHolder = (SendViewHolder)holder;

            if (message.getImageUrl().equals("")) {
                viewHolder.binding.imageView2.setVisibility(View.GONE);
                viewHolder.binding.message.setVisibility(View.VISIBLE);


            }
            else{
                viewHolder.binding.imageView2.setVisibility(View.VISIBLE);
                viewHolder.binding.message.setVisibility(View.GONE);
                Glide.with(context)
                        .load(message.getImageUrl())
                        .placeholder(R.drawable.image)
                        .into(viewHolder.binding.imageView2);
            }

            viewHolder.binding.message.setText(message.getMessage());
        }
        else {
            ReceiveViewHolder viewHolder = (ReceiveViewHolder)holder;
            if (message.getImageUrl().equals("")) {
                viewHolder.binding.imageView2.setVisibility(View.GONE);
                viewHolder.binding.message.setVisibility(View.VISIBLE);
            }
            else{
                viewHolder.binding.imageView2.setVisibility(View.VISIBLE);
                viewHolder.binding.message.setVisibility(View.GONE);
                Glide.with(context)
                        .load(message.getImageUrl())
                        .placeholder(R.drawable.image)
                        .into(viewHolder.binding.imageView2);
            }
            viewHolder.binding.message.setText(message.getMessage());
        }
    }


    @Override
    public int getItemCount() {
        return messages.size();
    }

    public class SendViewHolder extends RecyclerView.ViewHolder {

        ItemSendBinding binding;
        public SendViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = ItemSendBinding.bind(itemView);

        }
    }

    public class ReceiveViewHolder extends RecyclerView.ViewHolder {

        ItemReceiveBinding binding;
        public ReceiveViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = ItemReceiveBinding.bind(itemView);
        }
    }

}
