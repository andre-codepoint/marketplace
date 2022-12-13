package com.frontend.ui;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Objects;
import com.market.Role;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomerUI {
    private Long id;
    private String name;
    private Role role;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public CustomerUI() {

    };
    public CustomerUI(Long id, String name, Role role) {
        this.id = id;
        this.name = name;
        this.role = role;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CustomerUI that)) return false;
        return getId().equals(that.getId()) && getName().equals(that.getName()) && getRole() == that.getRole();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getRole());
    }

    @Override
    public String toString() {
        return "CustomerUI{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", role=" + role +
                '}';
    }
}
