package co.com.ceiba.mobile.pruebadeingreso.view.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import co.com.ceiba.mobile.pruebadeingreso.R;
import co.com.ceiba.mobile.pruebadeingreso.data.model.User;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolderUser> implements View.OnClickListener {
    private View.OnClickListener listener;
    private Context context;
    private List<User> users;

    public UserAdapter(List<User> users, Context context) {
        this.context = context;
        this.users = users;
    }

    @Override
    public void onClick(View v) {
        if (listener != null) {
            listener.onClick(v);
        }
    }

    public void setOnClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public UserAdapter.ViewHolderUser onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_list_item, null, false);
        view.setOnClickListener(this);
        return new ViewHolderUser(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapter.ViewHolderUser holder, int position) {
        User user = users.get(position);
        holder.name.setText(user.getName());
        holder.phone.setText(user.getPhone());
        holder.email.setText(user.getEmail());
    }

    @Override
    public int getItemCount() {
        return users.size();
    }


    public class ViewHolderUser extends RecyclerView.ViewHolder {
        TextView name, phone, email;

        public ViewHolderUser(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name);
            phone = (TextView) itemView.findViewById(R.id.phone);
            email = (TextView) itemView.findViewById(R.id.email);
        }
    }
}