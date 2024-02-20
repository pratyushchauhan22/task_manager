package com.pratyush.task_manager.controllers;

import com.pratyush.task_manager.dto.CreateTaskDTO;
import com.pratyush.task_manager.dto.ErrorResponseDTO;
import com.pratyush.task_manager.dto.TaskResponseDTO;
import com.pratyush.task_manager.dto.UpdateTaskDTO;
import com.pratyush.task_manager.entities.TaskEntity;
import com.pratyush.task_manager.services.NotesService;
import com.pratyush.task_manager.services.TaskService;
import org.springframework.http.ResponseEntity;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TasksController {
    private final TaskService taskService;
    private  final NotesService notesService;
    private ModelMapper modelMapper = new ModelMapper();

    public TasksController(TaskService taskService, NotesService notesService) {
        this.taskService = taskService;
        this.notesService = notesService;
    }


    @GetMapping
    public ResponseEntity<List<TaskEntity>> getTasks() {
        var tasks = taskService.getTasks();
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskResponseDTO> getTaskById(@PathVariable ("id") Integer id) {
        var task = taskService.getTaskById(id);
        var notes = notesService.getNotesForTask(id);
        if (task == null) {
            return ResponseEntity.notFound().build();
        }
       // task.setNotes(notes);
        var taskResponse = modelMapper.map(task, TaskResponseDTO.class);
        taskResponse.setNotes(notes);


        return ResponseEntity.ok(taskResponse);
    }


    @PostMapping("")
    public ResponseEntity<TaskEntity> addTask(@RequestBody CreateTaskDTO body) throws ParseException {
        var task = taskService.addTask(body.getTitle(), body.getDescription(), body.getDeadline());
        return ResponseEntity.ok(task);

    }

    @PatchMapping("/{id}")
    public ResponseEntity<TaskEntity> updateTask(@PathVariable ("id") Integer id, @RequestBody UpdateTaskDTO body) throws ParseException{
        var task = taskService.updateTask(id, body.getDescription(), body.getDeadline(), body.getCompleted());
        if (task == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(task);

    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDTO> handleErrors (Exception e){
        if ( e instanceof ParseException){
            return ResponseEntity.badRequest().body(new ErrorResponseDTO("Invalid date format"));
        }
        e.printStackTrace();
        return ResponseEntity.internalServerError().body(new ErrorResponseDTO("Internal server error"));

    }

}
