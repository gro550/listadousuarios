package co.com.ceiba.mobile.pruebadeingreso.view.post;

import co.com.ceiba.mobile.pruebadeingreso.data.model.User;
import co.com.ceiba.mobile.pruebadeingreso.data.model.response.Posts;
import co.com.ceiba.mobile.pruebadeingreso.view.ViewContract;

/**
 * En este segmento de la aplicación se buscan los post de un usuario con acceso a internet
 */
public interface PostContract {

    interface View extends ViewContract {
        void setDataUserInView();

        void requestPostPresenter(Integer id);

        void showPostUsers(Posts posts);
    }

    interface Presenter {
        void requestPostModel(Integer id);

        void setPostsView(Posts post);

        ViewContract getPostView();
    }

    interface Model {

        void searchPostAPI(Integer id);

    }

}