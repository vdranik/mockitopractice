package com.mockitopractice.mockito.business.impl;

import com.mockitopractice.mockito.data.api.TodoService;

import java.util.List;
import java.util.stream.Collectors;

public class TodoBusinessImpl {
    private TodoService todoService;

    public TodoBusinessImpl(TodoService todoService) {
        this.todoService = todoService;
    }

    public List<String> retrieveTodosRelatedToSpring(String user) {

        List<String> allTodos = todoService.retrieveTodos(user);

        List<String> filteredTodos = allTodos.stream()
                .filter(todo -> todo.contains("Spring"))
                .collect(Collectors.toList());

        return filteredTodos;
    }

    public List<String> deleteTodosNotRelatedToSpring(String user) {

        List<String> allTodos = todoService.retrieveTodos(user);

        allTodos.stream().filter(todo -> !todo.contains("Spring")).forEach(todo -> todoService.deleteTodo(todo));

        return allTodos;
    }
}
