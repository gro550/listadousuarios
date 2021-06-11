package co.com.ceiba.mobile.pruebadeingreso.view.post;

import android.content.Context;

import co.com.ceiba.mobile.pruebadeingreso.data.model.response.Posts;

public class PostPresenter implements PostContract.Presenter {

    private PostContract.View view;
    private PostContract.Model model;
    private Context context;

    public PostPresenter(PostContract.View view, Context context) {
        this.view = view;
        this.context = context;
        this.model = new PostModel(this, this.context);
    }

    /**
     * Solicitar los posts al repositorio de datos API buscando por el id de usuario
     *
     * @param id
     */
    @Override
    public void requestPostModel(Integer id) {
        model.searchPostAPI(id);
    }


    /**
     * Enviarle a la vista los posts para que los presente
     *
     * @param post
     */
    @Override
    public void setPostsView(Posts post) {
        view.showPostUsers(post);
    }

    @Override
    public PostContract.View getPostView() {
        return view;
    }
}