package it.nowicki.jaroslaw.infrastructure.mongo.user;

import it.nowicki.jaroslaw.domain.user.User;
import it.nowicki.jaroslaw.domain.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MongoUserRepository implements UserRepository {

    private final UserDAO userDAO;
    private final UserDAOMapper userDAOMapper;

    @Autowired
    public MongoUserRepository(final UserDAO userDAO,
                               final UserDAOMapper userDAOMapper) {
        this.userDAO = userDAO;
        this.userDAOMapper = userDAOMapper;
    }

    @Override
    public User findOneByUsername(String username) {
        return userDAOMapper.map(userDAO.findOneByUsername(username));
    }

    @Override
    public User createUser(User user) {
        return userDAOMapper.map(userDAO.save(userDAOMapper.map(user)));
    }
}