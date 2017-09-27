package com.mockitopractice.mockito.stub;

import com.mockitopractice.mockito.data.api.TodoService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TodoServiceStub implements TodoService {
    public List<String> retrieveTodos(String user) {
        //bad practice
        if(user.equals("Dummy")) return new ArrayList<>();

        return Arrays.asList("Learn Spring MVC", "Learn Spring",
                "Learn to Dance");
    }

    @Override
    public void deleteTodo(String todo) {

    }
}
