package com.example.allinone;

public class BModel {

    String title;
    String description;
    String id;
    String updatedatetime;
    String currentdatetime;
    boolean expanded;

    public BModel(String id, String title, String description, String updatedatetime, String currentdatetime) {
        this.title = title;
        this.description = description;
        this.id = id;
        this.updatedatetime = updatedatetime;
        this.currentdatetime = currentdatetime;
        this.expanded = false;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCurrentdatetime() {
        return currentdatetime;
    }

    public void setCurrentdatetime(String currentdatetime) {
        this.currentdatetime = currentdatetime;
    }

    public boolean isExpanded() {
        return expanded;
    }

    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }

    public String getUpdatedatetime() {
        return updatedatetime;
    }

    public void setUpdatedatetime(String updatedatetime) {
        this.updatedatetime = updatedatetime;
    }
}

