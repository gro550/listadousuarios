package co.com.ceiba.mobile.pruebadeingreso.view.post;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import co.com.ceiba.mobile.pruebadeingreso.data.model.Post;
import co.com.ceiba.mobile.pruebadeingreso.data.model.response.Posts;
import co.com.ceiba.mobile.pruebadeingreso.data.network.CeibaAPI;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static co.com.ceiba.mobile.pruebadeingreso.data.network.RetrofitInstance.getRetrofitCeiba;
import static co.com.ceiba.mobile.pruebadeingreso.utils.Error.CATCH_ERROR;
import static co.com.ceiba.mobile.pruebadeingreso.utils.Error.ERROR_SERVER;
import static co.com.ceiba.mobile.pruebadeingreso.utils.Error.NOT_POSTS;

public class PostModel implements PostContract.Model {

    private PostContract.Presenter presenter;
    private Context context;

    public PostModel(PostContract.Presenter presenter, Context context) {
        this.presenter = presenter;
        this.context = context;
    }

    /**
     * Consumir servicio get que devuelve el listado de posts filtrando por id
     */
    @Override
    public void searchPostAPI(Integer id) {
        Call<JsonElement> call = getRetrofitCeiba(context).create(
                CeibaAPI.class).getPosts(id);
        call.enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                //VALIDAMOS SI LA RESPUESTA FUE CORRECTA Y CONTIENE POST
                if (response.isSuccessful() && response.body() != null) {
                    try {
                        Gson gson = new Gson();
                        Type type = new TypeToken<List<Post>>() {
                        }.getType();
                        List<Post> postList = gson.fromJson(response.body().toString(), type);
                        Posts posts = new Posts();
                        posts.setPosts(postList);
                        presenter.setPostsView(posts);
                    } catch (Exception e) {
                        presenter.getPostView().showMessage(CATCH_ERROR);
                    }
                } else {
                    presenter.getPostView().showMessage(NOT_POSTS);
                }
            }

            @Override
            public void onFailure(Call<JsonElement> call, Throwable t) {
                presenter.getPostView().showMessage(ERROR_SERVER);
            }
        });

    }
}