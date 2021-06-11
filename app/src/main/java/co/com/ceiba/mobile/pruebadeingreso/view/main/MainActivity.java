package co.com.ceiba.mobile.pruebadeingreso.view.main;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.com.ceiba.mobile.pruebadeingreso.R;
import co.com.ceiba.mobile.pruebadeingreso.data.model.User;
import co.com.ceiba.mobile.pruebadeingreso.data.model.response.Users;
import co.com.ceiba.mobile.pruebadeingreso.view.ViewBase;
import co.com.ceiba.mobile.pruebadeingreso.view.adapters.UserAdapter;

public class MainActivity extends ViewBase implements MainContract.View {


    private MainContract.Presenter presenter;
    private UserAdapter userAdapter;
    @BindView(R.id.recyclerViewSearchResults)
    RecyclerView recyclerUsers;
    @BindView(R.id.editTextSearch)
    EditText editTextSearch;
    private List<User> userList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        presenter = new MainPresenter(this, this);
        requestLocallyUsersPresenter();
        setOnChangeTextSearch();
    }

    /**
     * Verificar si el nombre que esta escribiendo contiene coincidencias
     */
    private void setOnChangeTextSearch() {
        if (userList != null) {
            editTextSearch.addTextChangedListener(new TextWatcher() {
                public void afterTextChanged(Editable s) {
                }

                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    searchUserByName(s.toString());
                }
            });
        }
    }

    /**
     * Solicitar los usuarios al presenter para  listarlos y  mostrarlos posteriormente
     */
    @Override
    public void requestLocallyUsersPresenter() {
        presenter.requestLocallyUsersModel();
    }

    /**
     * Llenar adaptador con los datos devueltos
     *
     * @param users
     */
    @Override
    public void showUsers(Users users) {
        userList = users.getUsers();
        setAdapter(users.getUsers());
    }

    private void setAdapter(final List<User> params) {
        userAdapter = new UserAdapter(params, this);
        recyclerUsers.setLayoutManager(new GridLayoutManager(getApplicationContext(), 1));
        userAdapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = params.get(recyclerUsers.getChildAdapterPosition(v));
                presenter.navegatePostsUser(user);
            }
        });
        recyclerUsers.setAdapter(userAdapter);
    }

    /**
     * buscar en los datos presentados una coincidencia de dato y muestre los resultados
     *
     * @param userName
     */
    @Override
    public void searchUserByName(String userName) {
        if (userName.trim().equals("")) {
            setAdapter(userList);
        } else {
            List<User> temp = new ArrayList<>();
            for (User userItem : userList) {
                if (userItem.getName().toLowerCase().contains(userName.toString().toLowerCase())) {
                    temp.add(userItem);
                }
            }
            setAdapter(temp);
        }

    }

    /**
     * Mostrar por pantalla que la lista esta vacia
     */
    @Override
    public void showAlertNotUsers() {
        showMessage("NO Hay usuarios registrados");
    }

}