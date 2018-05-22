package com.hmelizarraraz.apicrudclient.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hmelizarraraz.apicrudclient.R;
import com.hmelizarraraz.apicrudclient.pojo.User;
import com.hmelizarraraz.apicrudclient.view.UserDetail;

import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {

    private ArrayList<User> users;
    private Activity activity;

    public UserAdapter(ArrayList<User> users, Activity activity) {
        this.users = users;
        this.activity = activity;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_user, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        final User user = users.get(position);

        holder.tvUserName.setText(user.getName());
        holder.tvUserEmail.setText(user.getEmail());

        holder.cvUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, UserDetail.class);
                intent.putExtra("id", user.getId());
                activity.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    static class UserViewHolder extends RecyclerView.ViewHolder {

        private TextView tvUserName;
        private TextView tvUserEmail;
        private CardView cvUser;

        public UserViewHolder(View itemView) {
            super(itemView);
            tvUserName  = itemView.findViewById(R.id.tvUserName);
            tvUserEmail = itemView.findViewById(R.id.tvUserEmail);
            cvUser      = itemView.findViewById(R.id.cvUser);
        }
    }
}
