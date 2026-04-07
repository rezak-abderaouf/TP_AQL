import org.example.User;
import org.example.UserRepository;
import org.example.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository; // 1. We create a mock object for the repository

    @InjectMocks
    private UserService userService; // 2. Mockito injects the mock into the service automatically

    @Test
    public void testGetUserById() {
        // Arrange: Set up the test data and the mock's behavior
        long targetId = 1L;
        User mockUser = new User(targetId, "Rezak");

        // When the mock's findUserById is called with targetId, return our mockUser
        when(userRepository.findUserById(targetId)).thenReturn(mockUser);

        // Act: Call the method we actually want to test
        User result = userService.getUserById(targetId);

        // Assert: Check the results
        assertNotNull(result, "The returned user should not be null");
        assertEquals("Rezak", result.getName(), "The user's name should match the mock data");

        // CRITICAL CHECK: Verify that the method findUserById was called exactly once with the correct argument
        verify(userRepository).findUserById(targetId);
    }
}