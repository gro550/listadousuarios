package co.com.ceiba.mobile.pruebadeingreso.view.main;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import co.com.ceiba.mobile.pruebadeingreso.data.model.User;
import co.com.ceiba.mobile.pruebadeingreso.data.model.response.Users;
import co.com.ceiba.mobile.pruebadeingreso.data.network.CeibaAPI;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static co.com.ceiba.mobile.pruebadeingreso.data.network.RetrofitInstance.getRetrofitCeiba;
import static co.com.ceiba.mobile.pruebadeingreso.data.repository.Preference.getUsersPref;
import static co.com.ceiba.mobile.pruebadeingreso.data.repository.Preference.saveUsersPref;
import static co.com.ceiba.mobile.pruebadeingreso.utils.Error.ERROR_SERVER;
import static co.com.ceiba.mobile.pruebadeingreso.utils.Error.NOT_USERS;
import static co.com.ceiba.mobile.pruebadeingreso.utils.Error.SAVE_USERS;

/**
 * @author Eduardo Fonseca
 */
public class MainModel implements MainContract.Model {

    private MainContract.Presenter presenter;
    private Context context;

    public MainModel(MainContract.Presenter presenter, Context context) {
        this.presenter = presenter;
        this.context = context;
    }

    /**
     * Buscar usuarios localmente en las preferencias
     */
    @Override
    public void searchUsersLocally() {
        /** VALIDAMOS SI TIENE USUARIOS GUARDADOS LOCALMENTE*/
        Users users = getUsersPref(context);
        if (users != null) {
            presenter.setUsersView(users);
        } else {
            searchAndSaveUsersAPi();
        }
    }

    /**
     * Consumir el endpoint y guardar los usuarios  posteriormente
     */
    @Override
    public void searchAndSaveUsersAPi() {
        Call<JsonElement> call = getRetrofitCeiba(context).create(
                CeibaAPI.class).getUsers();
        call.enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                //VALIDAMOS SI LA RESPUESTA FUE CORRECTA Y CONTIENE USUARIOS
                if (response.isSuccessful() && response.body() != null) {
                    try {
                        Gson gson = new Gson();
                        Type type = new TypeToken<List<User>>() {
                        }.getType();
                        List<User> usersList = gson.fromJson(response.body().toString(), type);
                        Users users = new Users();
                        users.setUsers(usersList);
                        presenter.setUsersView(users);
                        saveUsers(users);
                    } catch (Exception e) {

                    }
                } else {
                    presenter.getMainView().showMessage(NOT_USERS);
                }
            }

            @Override
            public void onFailure(Call<JsonElement> call, Throwable t) {
                presenter.getMainView().showMessage(ERROR_SERVER);
            }
        });
    }

    /**
     * GUARDAR USUARIOS LOCALMENTE
     */
    @Override
    public void saveUsers(Users users) {
        saveUsersPref(context, users);
        presenter.getMainView().showMessage(SAVE_USERS);
        searchUsersLocally();
    }

}