package ch.admin.bit.jeap.jme.cdct;

import ch.admin.bit.jeap.jme.cdct.provider.web.api.task.TaskCreation;
import ch.admin.bit.jeap.jme.cdct.provider.web.api.task.TaskRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

/**
 * Populates the TaskRepository with sample data at local application startup.
 */
@Slf4j
@Component
@Profile("local")
@RequiredArgsConstructor
public class TaskDataInitializer implements CommandLineRunner {

    private final TaskRepository taskRepository;

    @Override
    public void run(String... args) {
        log.info("Initializing task repository with sample data...");

        TaskCreation task1 = new TaskCreation();
        task1.setTitle("Setup project documentation");
        task1.setContent("Create comprehensive documentation for the CDCT project");
        task1.setTag("documentation");
        taskRepository.saveNewTask(task1);

        TaskCreation task2 = new TaskCreation();
        task2.setTitle("Implement new API endpoint");
        task2.setContent("Add GET endpoint for retrieving tasks by tag");
        task2.setTag("development");
        taskRepository.saveNewTask(task2);

       log.info("Task repository initialized with {} tasks", taskRepository.getAllTasks().size());
    }

}
