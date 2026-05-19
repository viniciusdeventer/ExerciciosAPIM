package br.ifsp.taskmaster.repository;

import br.ifsp.taskmaster.model.Task;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
	Page<Task> findByCategory(String category, Pageable pageble);
}