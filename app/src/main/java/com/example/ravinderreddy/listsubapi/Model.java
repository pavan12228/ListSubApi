package com.example.ravinderreddy.listsubapi;

import java.io.Serializable;

/**
 * Created by Ravinder Reddy on 19-08-2017.
 */

public class Model implements Serializable {
    String id;
    String parent_id;
    String id_path;
    String category;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParent_id() {
        return parent_id;
    }

    public void setParent_id(String parent_id) {
        this.parent_id = parent_id;
    }

    public String getId_path() {
        return id_path;
    }

    public void setId_path(String id_path) {
        this.id_path = id_path;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    String position;
    String status;


}
