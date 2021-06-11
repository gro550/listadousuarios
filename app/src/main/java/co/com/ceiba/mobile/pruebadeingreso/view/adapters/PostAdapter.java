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
import co.com.ceiba.mobile.pruebadeingreso.data.model.Post;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolderPost> {
    private View.OnClickListener listener;
    private Context context;
    private List<Post> post;

    public PostAdapter(List<Post> post, Context context) {
        this.context = context;
        this.post = post;
    }


    @NonNull
    @Override
    public PostAdapter.ViewHolderPost onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_list_item, null, false);
        return new ViewHolderPost(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostAdapter.ViewHolderPost holder, int position) {
        Post post = this.post.get(position);
        holder.tittle.setText(post.getTitle());
        holder.body.setText(post.getBody());
    }

    @Override
    public int getItemCount() {
        return post.size();
    }


    public class ViewHolderPost extends RecyclerView.ViewHolder {
        TextView tittle, body;

        public ViewHolderPost(View itemView) {
            super(itemView);
            tittle = (TextView) itemView.findViewById(R.id.title);
            body = (TextView) itemView.findViewById(R.id.body);
        }
    }
}