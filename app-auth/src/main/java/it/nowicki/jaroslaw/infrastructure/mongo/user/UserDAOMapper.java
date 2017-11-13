package it.nowicki.jaroslaw.infrastructure.mongo.user;

import it.nowicki.jaroslaw.domain.user.User;
import org.springframework.stereotype.Component;

@Component
public class UserDAOMapper {

    public User map(UserDTO userDTO) {
        return new User();
    }

    public UserDTO map(User user) {
        return new UserDTO();
    }
}
