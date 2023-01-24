package models;

import org.bson.types.ObjectId;

public class User {
    public static final String LIBRARIAN = "librarian";
    public static final String STUDENT = "student";
    private ObjectId id;
    private String name;
    private String email;
    private String password;
    private String role;
    private String username;
    private String phone;
    private String address;

    public User(ObjectId id, String name, String email, String password, String role, String username, String phone, String address) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
        this.username = username;
        this.phone = phone;
        this.address = address;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "{"
                + "  \"id\":\"" + id+"\""
                + ", \"name\":\"" + name + "\""
                + ", \"email\":\"" + email + "\""
                + ", \"role\":\"" + role + "\""
                + ", \"username\":\"" + username + "\""
                + ", \"phone\":\"" + phone + "\""
                + ", \"address\":\"" + address + "\""
                + "}";
    }
}
