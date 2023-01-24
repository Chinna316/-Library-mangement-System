package models;

import org.bson.types.ObjectId;

import java.util.List;

public class Author {
        private ObjectId id;
        private String name;
        private String address;
        private String email;
        private String mobile;

        public Author(ObjectId id, String name, String address, String email, String mobile) {
            this.id = id;
            this.name = name;
            this.address = address;
            this.email = email;
            this.mobile = mobile;
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

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        @Override
        public String toString() {
            return "{"
                    + " \"id\":\"" + id + "\""
                    + ", \"name\":\"" + name + "\""
                    + ", \"address\":\"" + address + "\""
                    + ", \"email\":\"" + email + "\""
                    + ", \"mobile\":\"" + mobile + "\""
                    + "}";
        }
    }


