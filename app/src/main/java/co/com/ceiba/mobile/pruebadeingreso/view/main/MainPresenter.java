package co.com.ceiba.mobile.pruebadeingreso.view.main;

import android.content.Context;
import android.content.Intent;

import co.com.ceiba.mobile.pruebadeingreso.data.model.Post;
import co.com.ceiba.mobile.pruebadeingreso.data.model.User;
import co.com.ceiba.mobile.pruebadeingreso.data.model.response.Users;
import co.com.ceiba.mobile.pruebadeingreso.view.ViewContract;
import co.com.ceiba.mobile.pruebadeingreso.view.post.PostActivity;

import static co.com.ceiba.mobile.pruebadeingreso.rest.ParametersActivity.POST;
import static co.com.ceiba.mobile.pruebadeingreso.rest.ParametersActivity.USER;

/**
 * @author Eduardo Fonseca
 */
public class MainPresenter implements MainContract.Presenter {

    private MainContract.View view;
    private MainContract.Model model;
    private Context context;

    public MainPresenter(MainContract.View view, Context context) {
        this.view = view;
        this.context = context;
        this.model = new MainModel(this, this.context);
    }

    /**
     * Solicitar los usuarios al model para que los busque localmente o en el api rest
     */
    @Override
    public void requestLocallyUsersModel() {
        model.searchUsersLocally();
    }

    /**
     * Enviarle a la vista para que los presente
     *
     * @param users
     */
    @Override
    public void setUsersView(Users users) {
        view.showUsers(users);
    }

    /**
     * Crear intent pasando como parametro el usuario selecionado,
     * iniciar PostActivity sin limpiar el historial de navegacion del dispositivo
     *
     * @param user
     */
    @Override
    public void navegatePostsUser(User user) {
        Intent intent = new Intent((Context) this.view, PostActivity.class);
        intent.putExtra(USER, user);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        ((Context) this.view).getApplicationContext().startActivity(intent);
    }

    @Override
    public ViewContract getMainView() {
        return view;
    }

}