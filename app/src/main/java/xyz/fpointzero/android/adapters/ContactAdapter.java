package xyz.fpointzero.android.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import xyz.fpointzero.android.MainActivity;
import xyz.fpointzero.android.R;
import xyz.fpointzero.android.activities.ChatActivity;
import xyz.fpointzero.android.data.User;
import xyz.fpointzero.android.utils.activity.ActivityUtil;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder> {
    private List<User> contactList;
    
    public ContactAdapter(List<User> contactList){
        this.contactList = contactList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_contact, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        User user = contactList.get(position);
        holder.contactID.setText(user.getUserID());
        holder.contactView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = holder.contactID.getContext();
                Intent intent = new Intent(context, ChatActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("userID", user.getUserID());
                bundle.putString("username", user.getUsername());
                intent.putExtras(bundle);
                context.startActivity(intent);
                Toast.makeText(context, "click " + user.getUserID(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        View contactView;
        ImageView contactImage;
        TextView contactID;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            contactView = itemView;
            contactImage = itemView.findViewById(R.id.image_contact);
            contactID = itemView.findViewById(R.id.textview_contact);
        }
    }
}