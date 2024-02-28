package mieten17.services;

import mieten17.models.User;
import mieten17.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class UserServiceTest {
    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindUserByUsername() {
        User user = new User();
        user.setUsername("Ivan");
        when(userRepository.findUserByUsername("Ivan")).thenReturn(user);
        User foundUser = userService.findUserByUsername("Ivan");
        assertEquals("Ivan", foundUser.getUsername());
    }

    @Test
    public void TestGetUserNameParticipant() {
        Long id = 1L;
        userService.getUserNameParticipant(id);
        Mockito.verify(userRepository, Mockito.times(1)).findUserById(id);
    }
}
