package com.naoele.sample01.model;

import androidx.lifecycle.MutableLiveData;

import org.jetbrains.annotations.NotNull;

public class User {

    private long id;
    private String name;
    public final MutableLiveData<Boolean> isChecked = new MutableLiveData<>(false);

    public User() {

    }

    public User(long id, String name) {
        setId(id);
        setName(name);
    }

    @NotNull
    public String toString() {
        return "{\n" +
                "\t\"id\": " + id + ",\n" +
                "\t\"name\": " + name + ",\n" +
                "\t\"isChecked.getValue()\": " + isChecked.getValue() + "\n" +
                "}";
    }

    @Override
    public boolean equals(Object other) {

        if (this == other) {
            return true;
        }

        if (!(other instanceof User)) {
            return false;
        }

        User otherUser = (User) other;
        if (this.id == otherUser.getId()) {
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return (int) this.id;
    }

    public long getId() {
        return this.id;
    }

    public String getIdAsString() {
        return String.valueOf(id);
    }

    public void setId(long value) {
        this.id = value;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String value) {
        this.name = value;
    }
}
