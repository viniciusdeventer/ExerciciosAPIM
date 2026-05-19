package br.ifsp.taskmaster.controller;

import br.ifsp.taskmaster.dto.TaskDTO;
import br.ifsp.taskmaster.service.TaskService;

import jakarta.validation.Valid;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tasks")
@Tag(name = "Taskmaster", description = "API de Gerenciamento de Tarefas")
public class TaskController {
	
	@RestControllerAdvice
	public class GlobalExceptionHandler {

	    @ExceptionHandler(MethodArgumentNotValidException.class)
	    public ResponseEntity<String> handleValidation(MethodArgumentNotValidException ex) {

	        String message = ex.getBindingResult()
	                .getFieldErrors()
	                .get(0)
	                .getDefaultMessage();

	        return ResponseEntity
	                .badRequest()
	                .body(message);
	    }
	    
	    @ExceptionHandler(HttpMessageNotReadableException.class)
	    public ResponseEntity<String> handleInvalidType(HttpMessageNotReadableException ex) {
	        return ResponseEntity
	                .badRequest()
	                .body("Um ou mais campos possuem dados inválidos");
	    }
	}
    
    private final TaskService taskService;
    
    public TaskController(TaskService taskService) {
    	this.taskService = taskService;
    }
    
    @PostMapping
    @Operation(
            summary = "Criar tarefa",
            description = "Cria uma nova tarefa no sistema"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Tarefa criada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro de validação nos dados enviados")
    })
    public ResponseEntity<TaskDTO> createTask(@Valid @RequestBody TaskDTO dto) {
    	TaskDTO result = taskService.createTask(dto);
    	
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(result);
    }
    

    @PutMapping("/{id}")
    @Operation(
            summary = "Atualizar tarefa",
            description = "Atualiza uma tarefa existente pelo ID"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tarefa atualizada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos enviados"),
            @ApiResponse(responseCode = "404", description = "Tarefa não encontrada")
    })
    public ResponseEntity<TaskDTO> updateTask(@Valid @PathVariable Long id, @RequestBody TaskDTO dto) {
    	TaskDTO result = taskService.updateTask(id, dto);
    	
    	return ResponseEntity
    			.status(HttpStatus.OK)
    			.body(result);
    }
    
    @DeleteMapping("/{id}")
    @Operation(
            summary = "Excluir tarefa",
            description = "Remove uma tarefa existente pelo ID"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Tarefa removida com sucesso"),
            @ApiResponse(responseCode = "404", description = "Tarefa não encontrada")
    })
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {

        taskService.deleteTask(id);

        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/{id}")
    @Operation(
            summary = "Buscar tarefa por ID",
            description = "Retorna uma tarefa específica pelo seu identificador"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tarefa encontrada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Tarefa não encontrada")
    })
    public ResponseEntity<TaskDTO> getTaskById(@PathVariable Long id) {
        TaskDTO result = taskService.getTaskById(id);

        return ResponseEntity.ok(result);
    }

    @GetMapping
    @Operation(
            summary = "Listar tarefas",
            description = "Retorna todas as tarefas com suporte a paginação e filtro por categoria"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de tarefas retornada com sucesso")
    })
    public ResponseEntity<Page<TaskDTO>> getAllTasks(@RequestParam(required = false) String category, Pageable pageable) {
    	Page<TaskDTO> result = taskService.getAllTasks(category, pageable);
    	
    	return ResponseEntity.ok(result);
    }

}