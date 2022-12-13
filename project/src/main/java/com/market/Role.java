package com.market;

public enum Role {
    USER("USER"), ADMIN("ADMIN");

    private final String name;

    private Role(String value) {
        name = value;
    }

    @Override
    public String toString() {
        return name;
    }
}