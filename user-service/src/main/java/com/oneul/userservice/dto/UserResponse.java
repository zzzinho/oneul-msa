package com.oneul.userservice.dto;

import com.netflix.servo.util.Objects;

public class UserResponse {
    private Long id;
    private String username;

    public UserResponse () {}

    public UserResponse(Long id, String username){
        this.id = id;
        this.username = username;
    }

    public Long getId(){
        return this.id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username){
        this.username = username;
    }

    @Override
    public boolean equals(Object object){
        if(this == object){
            return true;
        }
        if(object == null || getClass() != object.getClass()){
            return false;
        }
        UserResponse that = (UserResponse) object;
        return this.id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    @Override
    public String toString(){
        return "UserResponse["
            + "id: " + this.id
            + ", username: " + this.username
            + "]";
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Long id;
        private String username;

        public UserResponse build() {
            return new UserResponse(
                id,
                username
            );
        }

        public Builder id(Long id){
            this.id = id;
            return this;
        }

        public Builder username(String username){
            this.username = username;
            return this;
        }
    }
}
