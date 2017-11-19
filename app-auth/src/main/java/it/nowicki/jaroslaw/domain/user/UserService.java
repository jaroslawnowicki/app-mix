package it.nowicki.jaroslaw.domain.user;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userDetailsService")
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    public UserService(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("load user namer");
        User user =  userRepository.findOneByUsername(username);

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), null);
    }

    public User createUser(User user) {
        return userRepository.createUser(user);
    }
}