package com.example.projektproo;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Task {
    private StringProperty Id;
    private StringProperty Title;
    private StringProperty Description;
    private StringProperty  Deadline;
    private StringProperty Category;
    private StringProperty  IsCompleted;
    private StringProperty  UserId;

    public Task(){
        Id = new SimpleStringProperty(this, "Id");
        Title = new SimpleStringProperty(this, "Title");
        Description = new SimpleStringProperty(this, "Description");
        Category = new SimpleStringProperty(this, "Category");
        Deadline = new SimpleStringProperty(this, "Deadline");
        IsCompleted = new SimpleStringProperty(this, "IsCompleted");
        UserId = new SimpleStringProperty(this, "UserId");
    }

    public String getTitle() {
        return Title.get();
    }

    public StringProperty titleProperty() {
        return Title;
    }

    public String getDescription() {
        return Description.get();
    }

    public StringProperty descriptionProperty() {
        return Description;
    }

    public String getDeadline() {
        return Deadline.get();
    }

    public StringProperty deadlineProperty() {
        return Deadline;
    }

    public String getCategory() {
        return Category.get();
    }

    public StringProperty categoryProperty() {
        return Category;
    }

    public String getIsCompleted() {
        return IsCompleted.get();
    }

    public StringProperty isCompletedProperty() {
        return IsCompleted;
    }

    public String getUserId() {
        return UserId.get();
    }

    public StringProperty userIdProperty() {
        return UserId;
    }

    public void setTitle(String title) {
        this.Title.set(title);
    }

    public void setDescription(String description) {
        this.Description.set(description);
    }

    public void setDeadline(String deadline) {
        this.Deadline.set(deadline);
    }

    public void setCategory(String category) {
        this.Category.set(category);
    }

    public void setIsCompleted(String isCompleted) {
        this.IsCompleted.set(isCompleted);
    }

    public void setUserId(String userId) {
        this.UserId.set(userId);
    }

    public String getId() {
        return Id.get();
    }

    public StringProperty idProperty() {
        return Id;
    }

    public void setId(String id) {
        this.Id.set(id);
    }
}
