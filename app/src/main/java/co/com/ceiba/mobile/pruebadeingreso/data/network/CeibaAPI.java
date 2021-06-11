package co.com.ceiba.mobile.pruebadeingreso.data.network;

import com.google.gson.JsonElement;

import co.com.ceiba.mobile.pruebadeingreso.data.model.response.Posts;
import co.com.ceiba.mobile.pruebadeingreso.data.model.response.Users;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CeibaAPI {
    /**
     * SOLICITAR USUARIOS
     */
    @GET("/users")
    Call<JsonElement> getUsers();

    /**
     * SOLICITAR PUBLICACIONES
     */
    @GET("/posts")
    Call<JsonElement> getPosts(@Query("userid") Integer userId);
}
