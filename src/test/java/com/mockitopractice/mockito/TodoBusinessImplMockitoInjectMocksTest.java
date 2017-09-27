package com.mockitopractice.mockito;

import com.mockitopractice.mockito.business.impl.TodoBusinessImpl;
import com.mockitopractice.mockito.data.api.TodoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TodoBusinessImplMockitoInjectMocksTest {
    @Mock
    private TodoService todoServiceMock;

    @InjectMocks
    private TodoBusinessImpl todoBusinessImpl; // todoServiceMock will be injected to todoBusinessImpl
    //TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoServiceMock);

    @Captor
    private ArgumentCaptor<String> stringArgumentCaptor;

    @Test
    public void usingMockito() {

       // TodoService todoServiceMock = mock(TodoService.class); this is no longer necessary - we use @Mock
        List<String> allTodos = Arrays.asList("Learn Spring MVC",
                "Learn Spring", "Learn to Dance");

        when(todoServiceMock.retrieveTodos("Ranga")).thenReturn(allTodos);

        //TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoServiceMock); this is no longer necessary - we use  @InjectMocks

        List<String> todos = todoBusinessImpl
                .retrieveTodosRelatedToSpring("Ranga");
        assertEquals(2, todos.size());
    }

    @Test
    public void usingMockito_UsingBDD() {
        List<String> allTodos = Arrays.asList("Learn Spring MVC",
                "Learn Spring", "Learn to Dance");

        //given
        given(todoServiceMock.retrieveTodos("Ranga")).willReturn(allTodos);

        //when
        List<String> todos = todoBusinessImpl
                .retrieveTodosRelatedToSpring("Ranga");

        //then
        assertThat(todos.size(), is(2));
    }

    @Test
    public void letsTestDeleteNow() {

        List<String> allTodos = Arrays.asList("Learn Spring MVC",
                "Learn Spring", "Learn to Dance");

        when(todoServiceMock.retrieveTodos("Ranga")).thenReturn(allTodos);

        todoBusinessImpl.deleteTodosNotRelatedToSpring("Ranga");

        verify(todoServiceMock).deleteTodo("Learn to Dance");

        verify(todoServiceMock, Mockito.never()).deleteTodo("Learn Spring MVC");

        verify(todoServiceMock, Mockito.never()).deleteTodo("Learn Spring");

        verify(todoServiceMock, Mockito.times(1)).deleteTodo("Learn to Dance");
        // atLeastOnce, atLeast

    }

    @Test
    public void captureArgument() {
        List<String> allTodos = Arrays.asList("Learn Spring MVC",
                "Learn Spring", "Learn to Dance");
        Mockito.when(todoServiceMock.retrieveTodos("Ranga")).thenReturn(allTodos);

        todoBusinessImpl.deleteTodosNotRelatedToSpring("Ranga");
        Mockito.verify(todoServiceMock).deleteTodo(stringArgumentCaptor.capture());

        assertEquals("Learn to Dance", stringArgumentCaptor.getValue());
    }
}
