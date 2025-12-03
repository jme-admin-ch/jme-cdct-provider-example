package ch.admin.bit.jeap.jme.cdct.provider.web.api.task;

import org.springframework.stereotype.Repository;

import java.time.ZonedDateTime;
import java.util.*;

@Repository
public class TaskRepository {

    private final Map<String, Task> tasks = Collections.synchronizedMap(new LinkedHashMap<>());

    public Collection<Task> getAllTasks() {
        return tasks.values();
    }

    public Task getTaskById(String id) {
        return tasks.get(id);
    }

    public Task saveNewTask(TaskCreation taskToCreate) {
        Task task = new Task();
        task.setId(UUID.randomUUID().toString());
        task.setTitle(taskToCreate.getTitle());
        task.setContent(taskToCreate.getContent());
        task.setTag(taskToCreate.getTag());
        task.setCreatedAt(ZonedDateTime.now());
        saveTask(task);
        return task;
    }

    public void saveTask(Task task) {
        tasks.put(task.getId(), task);
    }

    public void deleteAll() {
        tasks.clear();
    }

}
