package lt.techin.exam.response.user;

import lt.techin.exam.entity.User;
import org.springframework.http.HttpStatus;

public class UserResponse {
    private User user;
    private HttpStatus status;


    public UserResponse() {
    }

    public UserResponse(User user, HttpStatus status) {
        this.user = user;
        this.status = status;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }
}
