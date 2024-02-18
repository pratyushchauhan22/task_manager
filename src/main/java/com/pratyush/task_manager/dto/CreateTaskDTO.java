package com.pratyush.task_manager.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

@Getter
@Setter
@NoArgsConstructor
public class CreateTaskDTO {
    String title;
    String description;
    String deadline;

}
