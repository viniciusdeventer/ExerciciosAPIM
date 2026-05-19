package br.ifsp.taskmaster.service;

import br.ifsp.taskmaster.dto.TaskDTO;
import br.ifsp.taskmaster.mapper.TaskMapper;
import br.ifsp.taskmaster.model.Task;
import br.ifsp.taskmaster.repository.TaskRepository;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class TaskService {
	
    private final TaskRepository taskRepository;
    
    public TaskService(TaskRepository taskRepository) {
    	this.taskRepository = taskRepository;
	}
    
    public TaskDTO createTask(TaskDTO dto) {
        Task task = TaskMapper.toEntity(dto);
        
    	if (dto.getDeadline() != null && dto.getDeadline().isBefore(LocalDate.now())) {
    	    throw new ResponseStatusException(
    	            HttpStatus.BAD_REQUEST,
    	            "A deadline não pode ser menor que a data de hoje"
    	    );
    	}
        
        return TaskMapper.toDTO(taskRepository.save(task));
    }
    
    public TaskDTO updateTask(Long id, TaskDTO dto) {
    	Task task = taskRepository.findById(id)
    	        .orElseThrow(() -> new ResponseStatusException(
    	                HttpStatus.NOT_FOUND,
    	                "Tarefa não encontrada"
    	        ));

    	task.setTitle(dto.getTitle());

        if (dto.getDescription() != null) 
        	task.setDescription(dto.getDescription());
        
    	task.setCategory(dto.getCategory());
        
    	if (dto.getDeadline() != null && dto.getDeadline().isBefore(LocalDate.now())) {
    	    throw new ResponseStatusException(
    	            HttpStatus.BAD_REQUEST,
    	            "A deadline não pode ser menor que a data de hoje"
    	    );
    	}
    	
    	task.setDeadline(dto.getDeadline());
        
        return TaskMapper.toDTO(taskRepository.save(task));
    }
    
    public void deleteTask(Long id) {
    	Task task = taskRepository.findById(id)
    	        .orElseThrow(() -> new ResponseStatusException(
    	                HttpStatus.NOT_FOUND,
    	                "Tarefa não encontrada"
    	        ));
    	
        taskRepository.delete(task);
    }
    
    public TaskDTO getTaskById(Long id) {
    	Task task = taskRepository.findById(id)
    	        .orElseThrow(() -> new ResponseStatusException(
    	                HttpStatus.NOT_FOUND,
    	                "Tarefa não encontrada"
    	        ));
    	
        return TaskMapper.toDTO(task);
    }
    
    public Page<TaskDTO> getAllTasks(String category, Pageable pageable) {
        Page<Task> tasks;

        if (category != null && !category.isBlank()) {
            tasks = taskRepository.findByCategory(category, pageable);
        } else {
            tasks = taskRepository.findAll(pageable);
        }

        return tasks.map(TaskMapper::toDTO);
    }
}
