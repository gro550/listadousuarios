package co.com.ceiba.mobile.pruebadeingreso.view.main;

import android.view.View;

import co.com.ceiba.mobile.pruebadeingreso.data.model.User;
import co.com.ceiba.mobile.pruebadeingreso.data.model.response.Users;
import co.com.ceiba.mobile.pruebadeingreso.view.ViewContract;

/**
 * En este segmento de la aplicación se  verificara si los usuarios se encuentran almacenados de manera local,  si es así se solo se deben presentar; si no se encuentran almacenados de
 * manera local se debe consumir el Web Service, almacenar el resultado y presentarlos al
 * usuario.
 */
public interface MainContract {

    interface View extends ViewContract {

        void requestLocallyUsersPresenter();

        void showUsers(Users users);

        void searchUserByName(String userName);

        void showAlertNotUsers();

    }

    interface Presenter {

        void requestLocallyUsersModel();

        void setUsersView(Users users);

        void navegatePostsUser(User user);

        ViewContract getMainView();

    }

    interface Model {

        void searchUsersLocally();

        void searchAndSaveUsersAPi();

        void saveUsers(Users users);

    }

}