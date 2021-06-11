package co.com.ceiba.mobile.pruebadeingreso.view.post;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.com.ceiba.mobile.pruebadeingreso.R;
import co.com.ceiba.mobile.pruebadeingreso.data.model.Post;
import co.com.ceiba.mobile.pruebadeingreso.data.model.response.Posts;
import co.com.ceiba.mobile.pruebadeingreso.view.ViewBase;
import co.com.ceiba.mobile.pruebadeingreso.view.adapters.PostAdapter;

public class PostActivity extends ViewBase implements PostContract.View {


    private PostContract.Presenter presenter;
    private PostAdapter postAdapter;
    @BindView(R.id.recyclerViewPostsResults)
    RecyclerView recyclerPosts;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.email)
    TextView email;
    @BindView(R.id.phone)
    TextView phone;
    private List<Post> postList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        ButterKnife.bind(this);
        presenter = new PostPresenter(this, this);
        getRequestActivity();
        setDataUserInView();
        requestPostPresenter(userRequest.getId());
    }

    /**
     * mostrar el nombre, email, teléfono del usuario
     */
    @Override
    public void setDataUserInView() {
        phone.setText(userRequest.getPhone());
        email.setText(userRequest.getEmail());
        name.setText(userRequest.getName());
    }

    /**
     * Solicitar los post del usuario  a  la capa de datos atravez del presenter enviando el id del usuario
     *
     * @param id
     */
    @Override
    public void requestPostPresenter(Integer id) {
        presenter.requestPostModel(id);
    }


    /**
     * Llenar adaptador con los datos devueltos
     *
     * @param posts
     */
    @Override
    public void showPostUsers(Posts posts) {
        postList = posts.getPosts();
        postAdapter = new PostAdapter(posts.getPosts(), this);
        recyclerPosts.setLayoutManager(new GridLayoutManager(getApplicationContext(), 1));
        recyclerPosts.setAdapter(postAdapter);
    }

}