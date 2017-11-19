package it.nowicki.jaroslaw.domain.user;

public interface UserRepository {
    User findOneByUsername(String username);

    User createUser(User user);
}
