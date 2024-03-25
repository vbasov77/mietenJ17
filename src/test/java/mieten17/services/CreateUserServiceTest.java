package mieten17.services;

import mieten17.models.User;
import mieten17.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


@ExtendWith(SpringExtension.class)
@SpringBootTest
public class CreateUserServiceTest {
    @InjectMocks
    private CreateUserService createUserService;
    @Mock
    private UserRepository userRepository;

    @Test
    public void testCreateNewUser() {
        User user = new User();
        user.setUsername("Ivan");
        user.setEmail("123@mail.ru");
        user.setPassword("100");
        user.setRoles("ROLE_USER");
        createUserService.save(user);

        Mockito.verify(userRepository, Mockito.times(1)).save(user);
    }

    @Test
    public void testGetUserById() {
        User mockUser = new User();
        mockUser.setId(1L);
        when(userRepository.findUserById(1L)).thenReturn(mockUser);
        User foundUser = createUserService.getUserById(1L);
        assertEquals(1L, foundUser.getId());
    }


}
