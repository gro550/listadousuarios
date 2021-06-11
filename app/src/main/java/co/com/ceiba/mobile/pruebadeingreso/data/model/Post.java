package co.com.ceiba.mobile.pruebadeingreso.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import lombok.Data;
import lombok.Getter;

/**
 * {
 * "userId": 1,
 * "id": 1,
 * "title": "sunt aut facere repellat provident occaecati t",
 * "body": "quia et suscipit\nsuscipit recusand"
 * }
 *
 * @author Eduardo Fonseca
 */
@Data
public class Post implements Serializable {
    @Getter
    @SerializedName("userId")
    @Expose
    private Integer userId;
    @Getter
    @SerializedName("id")
    @Expose
    private Integer id;
    @Getter
    @SerializedName("title")
    @Expose
    private String title;
    @Getter
    @SerializedName("body")
    @Expose
    private String body;
}