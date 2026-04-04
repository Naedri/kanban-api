package com.naedri.kanban_api.service.impl;

import com.naedri.kanban_api.domain.model.Task;
import com.naedri.kanban_api.dto.task.CreateTaskRequestDto;
import com.naedri.kanban_api.mapper.TaskMapper;
import com.naedri.kanban_api.repository.TaskRepository;
import com.naedri.kanban_api.service.TaskService;
import org.springframework.stereotype.Service;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskMapper taskMapper;
    private final TaskRepository taskRepository;

    public TaskServiceImpl(TaskMapper taskMapper,
                           TaskRepository taskRepository) {
        this.taskMapper = taskMapper;
        this.taskRepository = taskRepository;
    }

    @Override
    public Task createTask(CreateTaskRequestDto dto) {

        Task task = taskMapper.fromDto(dto);

        return taskRepository.save(task);
    }
}
