package ch.admin.bit.jeap.jme.cdct.provider.web.api.task;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@Slf4j
@RestController
@RequestMapping("/api/task")
@RequiredArgsConstructor
public class TaskController {

    private final TaskRepository taskRepository;

    @Schema(description = "Get all tasks")
    @GetMapping()
    @PreAuthorize("hasRole('task', 'read')")
    public Collection<Task> getTasks() {
        return taskRepository.getAllTasks();
    }

    @Schema(description = "Get one task by id")
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('task', 'read')")
    public ResponseEntity<Task> getTask(@PathVariable("id") String id) {
        Task task = taskRepository.getTaskById(id);
        if (task != null) {
            return ResponseEntity.ok().body(task);
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @Schema(description = "Create a new task")
    @PostMapping()
    @PreAuthorize("hasRole('task', 'write')")
    public Task createTask(@RequestBody TaskCreation taskCreation) {
        return taskRepository.saveNewTask(taskCreation);
    }

}
