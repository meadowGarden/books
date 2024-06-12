package lt.techin.exam.response.user;

import lt.techin.exam.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;

import java.util.List;

public class UserListResponse {
    private Page<User> users;
    private HttpStatus status;


    public UserListResponse() {
    }

    public UserListResponse(Page<User> users, HttpStatus status) {
        this.users = users;
        this.status = status;
    }


    public Page<User> getUsers() {
        return users;
    }

    public void setUsers(Page<User> users) {
        this.users = users;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }
}
