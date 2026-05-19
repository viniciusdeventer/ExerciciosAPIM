package br.ifsp.taskmaster.mapper;

import br.ifsp.taskmaster.dto.TaskDTO;
import br.ifsp.taskmaster.model.Task;

public class TaskMapper {
	public static TaskDTO toDTO(Task task) {
		TaskDTO dto = new TaskDTO();

        dto.setId(task.getId());
        dto.setTitle(task.getTitle());
        dto.setDescription(task.getDescription());
        dto.setCategory(task.getCategory());
        dto.setDeadline(task.getDeadline());

        return dto;
    }
	
	public static Task toEntity(TaskDTO dto) {
		Task task = new Task();

        task.setTitle(dto.getTitle());
        task.setDescription(dto.getDescription());
        task.setCategory(dto.getCategory());
        task.setDeadline(task.getDeadline());

        return task;
    }
}
