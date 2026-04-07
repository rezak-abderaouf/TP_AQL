package org.example;

public class UserService {

    // The dependency is injected via the constructor
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // This method retrieves a user object from the repository
    public User getUserById(long id) {
        return userRepository.findUserById(id);
    }
}