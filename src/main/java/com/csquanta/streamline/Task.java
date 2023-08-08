package com.csquanta.streamline;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.image.ImageView;

public class Task {

    private StringProperty title;
    private StringProperty tag;
    private StringProperty priority;
    private StringProperty dueDate;
    private StringProperty description;


    public Task(String title, String tag, String priority, String dueDate, String description, ImageView icon) {
        this.title = new SimpleStringProperty(title);
        this.tag = new SimpleStringProperty(tag);
        this.priority = new SimpleStringProperty(priority);
        this.dueDate = new SimpleStringProperty(dueDate);
        this.description = new SimpleStringProperty(description);

    }


    public StringProperty titleProperty() {
        return title;
    }

    public String getTitle() {
        return title.get();
    }

    public void setTitle(String title) {
        this.title.set(title);
    }


    public StringProperty tagProperty() {
        return tag;
    }

    public String getTag() {
        return tag.get();
    }

    public void setTag(String tag) {
        this.tag.set(tag);
    }


    public StringProperty priorityProperty() {
        return priority;
    }

    public String getPriority() {
        return priority.get();
    }

    public void setPriority(String priority) {
        this.priority.set(priority);
    }


    public StringProperty dueDateProperty() {
        return dueDate;
    }

    public String getDueDate() {
        return dueDate.get();
    }

    public void setDueDate(String dueDate) {
        this.dueDate.set(dueDate);
    }


    public StringProperty descriptionProperty() {
        return description;
    }

    public String getDescription() {
        return description.get();
    }

    public void setDescription(String description) {
        this.description.set(description);
    }



}

