package com.spring.model.security;

public enum Group {
    ADMIN("ADMIN"),  // DELETE
    STAFF("STAFF"), // WRITE
    USER("USER"),  // READ
    STUDENT("STUDENT"),
    INSTRUCTOR("INSTRUCTOR");

    private final String text;

    Group(final String text) {
        this.text = text;
    }

    @Override
    public String toString(){
        return text;
    }
}
