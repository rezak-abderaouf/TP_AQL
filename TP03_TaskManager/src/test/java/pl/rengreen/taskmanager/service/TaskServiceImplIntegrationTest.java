package pl.rengreen.taskmanager.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import pl.rengreen.taskmanager.model.Task;
import pl.rengreen.taskmanager.repository.TaskRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

// @SpringBootTest loads the real application context (Beans, Repositories, etc.)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@Testcontainers
public class TaskServiceImplIntegrationTest {

    // 1. Define the Docker Container
    @Container
    static MySQLContainer<?> mysql = new MySQLContainer<>("mysql:8.0.30")
            .withDatabaseName("taskmanagerdb")
            .withUsername("root")
            .withPassword("password");

    // 2. Intercept Spring Boot's configuration to use the Docker container's dynamic port
    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", mysql::getJdbcUrl);
        registry.add("spring.datasource.username", mysql::getUsername);
        registry.add("spring.datasource.password", mysql::getPassword);
        registry.add("spring.jpa.hibernate.ddl-auto", () -> "create-drop"); // Auto-generate tables for the test
    }

    // 3. Inject the REAL Service and Repository (No @Mock!)
    @Autowired
    private TaskService taskService;

    @Autowired
    private TaskRepository taskRepository;

    @BeforeEach
    public void setUp() {
        // Clean the database before every test to ensure isolation
        taskRepository.deleteAll();
    }

    @Test
    public void findAll_shouldReturnRealTasksListFromDatabase() {
        // Arrange: Create and save REAL tasks to the MySQL database
        Task task1 = new Task();
        task1.setName("Learn Docker");
        task1.setDescription("Understand isolated containers");

        Task task2 = new Task();
        task2.setName("Learn Testcontainers");
        task2.setDescription("Write bulletproof integration tests");

        taskRepository.save(task1);
        taskRepository.save(task2);

        // Act: Call the service
        List<Task> result = taskService.findAll();

        // Assert: Verify the service actually retrieved the data from the database
        assertThat(result).hasSize(2);
        assertThat(result.get(0).getName()).isEqualTo("Learn Docker");
        assertThat(result.get(1).getName()).isEqualTo("Learn Testcontainers");
    }
}