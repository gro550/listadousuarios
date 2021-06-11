package co.com.ceiba.mobile.pruebadeingreso.data.repository;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import co.com.ceiba.mobile.pruebadeingreso.data.model.response.Users;

public class Preference {
    private static final String ID_PREFERENCE = "USER";

    public static void saveUsersPref(Context context, Users rp) {
        SharedPreferences.Editor editor = getEditor(context);
        Gson gson = new Gson();
        String json = gson.toJson(rp);
        editor.putString(ID_PREFERENCE, json);
        editor.commit();
    }

    public static Users getUsersPref(Context context) {
        SharedPreferences sp = context.getApplicationContext().getSharedPreferences(ID_PREFERENCE, Context.MODE_PRIVATE);
        String json = sp.getString(ID_PREFERENCE, "");
        Gson gson = new Gson();
        Type type = new TypeToken<Users>() {
        }.getType();
        Users up = gson.fromJson(json, type);
        return up;
    }

    public static void deleteUsersPref(Context context) {
        SharedPreferences.Editor editor = getEditor(context);
        editor.putString(ID_PREFERENCE, "");
        editor.commit();
    }


    public static SharedPreferences.Editor getEditor(Context context) {
        SharedPreferences sharedPrefPositions = context.getApplicationContext().getSharedPreferences(ID_PREFERENCE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPrefPositions.edit();
        return editor;
    }
}
