package com.pratyush.task_manager.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter@NoArgsConstructor
public class UpdateTaskDTO {
    String deadline;
    String description;
    Boolean completed;

}
