package com.mockitopractice.mockito;

import com.mockitopractice.mockito.business.impl.TodoBusinessImpl;
import com.mockitopractice.mockito.data.api.TodoService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

public class TodoBusinessImplMockitoTest {

    @Mock
    private TodoService todoServiceMock;

    @Before
    public void init(){
        //todoServiceMock = mock(TodoService.class);
        //or
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testRetrieveRodosRelatedToSpring() {
        List<String> allTodos = Arrays.asList("Learn Spring MVC",
                "Learn Spring", "Learn to Dance");

        when(todoServiceMock.retrieveTodos(anyString())).thenReturn(allTodos); // //"Ranga" -> anyString()
        TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoServiceMock);
        List<String> todos = todoBusinessImpl
                .retrieveTodosRelatedToSpring(anyString());
        assertEquals(2, todos.size());
    }

    @Test
    public void testRetrieveRodosRelatedToSpring_withEmptyList() {
        when(todoServiceMock.retrieveTodos(anyString())).thenReturn(new ArrayList<>());
        TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoServiceMock);
        List<String> todos = todoBusinessImpl
                .retrieveTodosRelatedToSpring(anyString());
        assertEquals(0, todos.size());
    }

    //BDD STYLE
    @Test
    public void testRetrieveRodosRelatedToSpring_usingBDD() { // BDD = GIVEN -> WHEN -> THAN
        //Given
        TodoService todoServiceMock = mock(TodoService.class);
        List<String> allTodos = Arrays.asList("Learn Spring MVC",
                "Learn Spring", "Learn to Dance");
        //when(todoServiceMock.retrieveTodos(anyString())).thenReturn(allTodos);
        given(todoServiceMock.retrieveTodos(anyString())).willReturn(allTodos); //change methods names
        TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoServiceMock);
        //When
        List<String> todos = todoBusinessImpl
                .retrieveTodosRelatedToSpring(anyString());
        //Then
        //assertEquals(2, todos.size());
        assertThat(todos.size(),is(2)); //change methods names
    }


    @Test
    public void usingMockito_UsingBDD() {
        TodoService todoService = mock(TodoService.class);
        TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoService);
        List<String> allTodos = Arrays.asList("Learn Spring MVC",
                "Learn Spring", "Learn to Dance");

        //given
        given(todoService.retrieveTodos("Ranga")).willReturn(allTodos);

        //when
        List<String> todos = todoBusinessImpl
                .retrieveTodosRelatedToSpring("Ranga");

        //then
        assertThat(todos.size(), is(2));
    }

    @Test
    public void testDeleteTodosNotRelatedToSpring_usingBDD() { // BDD = GIVEN -> WHEN -> THAN
        //Given
        TodoService todoServiceMock = mock(TodoService.class);
        List<String> allTodos = Arrays.asList("Learn Spring MVC",
                "Learn Spring", "Learn to Dance");
        //when(todoServiceMock.retrieveTodos(anyString())).thenReturn(allTodos);
        given(todoServiceMock.retrieveTodos(anyString())).willReturn(allTodos); //change methods names
        TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoServiceMock);

        //When
        List<String> todos = todoBusinessImpl
                .deleteTodosNotRelatedToSpring(anyString());

        //Then
        //verify(todoServiceMock, times(1)).deleteTodo("Learn to Dance");
        then(todoServiceMock).should().deleteTodo("Learn to Dance");  //MOCKITO 2.0.0

        //verify(todoServiceMock, never()).deleteTodo("Learn Spring MVC");
        then(todoServiceMock).should(never()).deleteTodo("Learn Spring MVC");

        //verify(todoServiceMock, never()).deleteTodo("Learn Spring");
        then(todoServiceMock).should(never()).deleteTodo("Learn Spring");
    }

    @Test
    public void testDeleteTodosNotRelatedToSpring_usingBDD_argumentCapture() { // BDD = GIVEN -> WHEN -> THAN
        //Declare Argument Captor
        ArgumentCaptor<String> stringArgumentCaptor = ArgumentCaptor.forClass(String.class);
        //Define Arguement captor on specific method call
        //Capture the argument

        //Given
        TodoService todoServiceMock = mock(TodoService.class);
        List<String> allTodos = Arrays.asList("Learn Spring MVC",
                "Learn Spring", "Learn to Dance");
        //when(todoServiceMock.retrieveTodos(anyString())).thenReturn(allTodos);
        given(todoServiceMock.retrieveTodos(anyString())).willReturn(allTodos); //change methods names
        TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoServiceMock);

        //When
        List<String> todos = todoBusinessImpl
                .deleteTodosNotRelatedToSpring(anyString());

        //Then
        then(todoServiceMock).should().deleteTodo(stringArgumentCaptor.capture());
        assertThat(stringArgumentCaptor.getValue(), is("Learn to Dance"));
    }

    @Test
    public void testDeleteTodosNotRelatedToSpring_usingBDD_argumentCaptureMultipleTimes() { // BDD = GIVEN -> WHEN -> THAN
        //Declare Argument Captor
        ArgumentCaptor<String> stringArgumentCaptor = ArgumentCaptor.forClass(String.class);
        //Define Arguement captor on specific method call
        //Capture the argument

        //Given
        TodoService todoServiceMock = mock(TodoService.class);
        List<String> allTodos = Arrays.asList("Learn to Rock and Roll",
                "Learn Spring", "Learn to Dance");
        //when(todoServiceMock.retrieveTodos(anyString())).thenReturn(allTodos);
        given(todoServiceMock.retrieveTodos(anyString())).willReturn(allTodos); //change methods names
        TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoServiceMock);

        //When
        List<String> todos = todoBusinessImpl
                .deleteTodosNotRelatedToSpring(anyString());

        //Then
        then(todoServiceMock).should(times(2)).deleteTodo(stringArgumentCaptor.capture()); //times(2) is very important here
        assertThat(stringArgumentCaptor.getAllValues().size(), is(2));
    }

    @Test //capture argument with not bdd
    public void captureArgument() {
        ArgumentCaptor<String> argumentCaptor = ArgumentCaptor
                .forClass(String.class);

        TodoService todoService = mock(TodoService.class);

        List<String> allTodos = Arrays.asList("Learn Spring MVC",
                "Learn Spring", "Learn to Dance");
        when(todoService.retrieveTodos("Ranga")).thenReturn(allTodos);

        TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoService);
        todoBusinessImpl.deleteTodosNotRelatedToSpring("Ranga");
        verify(todoService).deleteTodo(argumentCaptor.capture());

        assertEquals("Learn to Dance", argumentCaptor.getValue());
    }


    @After
    public void teardown(){
        todoServiceMock = null;
    }
}
