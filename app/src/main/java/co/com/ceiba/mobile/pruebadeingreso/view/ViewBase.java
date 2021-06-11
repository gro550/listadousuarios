package co.com.ceiba.mobile.pruebadeingreso.view;

import android.app.Activity;
import android.widget.Toast;

import co.com.ceiba.mobile.pruebadeingreso.data.model.User;

import static co.com.ceiba.mobile.pruebadeingreso.rest.ParametersActivity.USER;

public class ViewBase extends Activity implements ViewContract {
    public User userRequest;


    public void getRequestActivity() {
        userRequest = (User) getIntent().getSerializableExtra(USER);
    }

    @Override
    public void showLoading() {
        Toast.makeText(this, "Cargando", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showMessage(String msg) {
        hideLoading();
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

}
