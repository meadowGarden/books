package lt.techin.exam.dto;

import lombok.*;
import lt.techin.exam.utilities.UserRole;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {
    private String firstName;
    private String lastName;
    private String email;
    private UserRole userRole;
    private String password;
}
