package com.example.courier.repository;

import com.example.courier.domain.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByCreationDateBetween(LocalDate date1, LocalDate date2);

}
