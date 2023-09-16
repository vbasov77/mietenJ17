package mieten17.services;
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

    /**
     * Добавление нового юзера в БД
     *
     * @param user - переменная нового Юзера;
     */
    public void save(User user) {
        userRepository.save(user);
    }

    /**
     * Метод добавляет пользователя в БД в таблицу - user
     * и его роль в таблицу roles_users.
     * Изначально юзеру присваивается роль - USER.
     *
     * @param user - переменная нового Юзера
     */
    public void creatNewUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        TreeSet<Role> role = new TreeSet<>();
        role.add(Role.valueOf("USER"));
        user.setRole(role);
        save(user);
    }

    public User getUserById(Long id){
        return userRepository.findUserById(id);
    }
}