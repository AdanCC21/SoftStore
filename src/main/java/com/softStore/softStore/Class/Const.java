package com.softStore.softStore.Class;
import com.softStore.softStore.Class.User;

public class Const {
    private User currentUser = new User();

    public Const() {
    }

    public Const(User user) {
        this.currentUser = user;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }
}
