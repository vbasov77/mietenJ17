package mieten17.services;

import mieten17.config.WebSecurityConfig;
import mieten17.models.Role;
import mieten17.models.User;
import mieten17.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.TreeSet;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return (UserDetails) userRepository.findByUsername(username);
    }

    public void save(User user) {
        userRepository.save(user);
    }

    public void creatNewUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        TreeSet<Role> role = new TreeSet<>();
        role.add(Role.valueOf("USER"));
        user.setRoles(role);
        save(user);
    }
}

