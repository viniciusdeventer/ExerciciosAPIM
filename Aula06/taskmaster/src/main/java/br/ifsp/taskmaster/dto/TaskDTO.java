package br.ifsp.taskmaster.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class TaskDTO {

    private Long id;
    
    @NotBlank(message = "O Título é obrigatório")
    private String title;
    
    private String description;
    
    @NotBlank(message = "A categoria é obrigatória")
    private String category;
    
    @NotNull(message = "A Deadline é obrigatória")
    private LocalDate deadline;

    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {  
        this.id = id;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    
    public LocalDate getDeadline() {
        return deadline;
    }
    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }
}
