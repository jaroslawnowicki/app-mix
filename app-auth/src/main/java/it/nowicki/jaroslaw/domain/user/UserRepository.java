package it.nowicki.jaroslaw.domain.user;

public interface UserRepository {
    User findOneByUsername(String username);
}
