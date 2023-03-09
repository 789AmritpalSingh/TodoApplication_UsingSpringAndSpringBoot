package com.example.mywebapp.todo;

import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Service
public class TodoService {
    private static List<Todo> todos = new ArrayList<>();
    private static int todosCount = 0;

    static {
        todos.add(new Todo(++todosCount,"Amritpal Singh","Learn Java", LocalDate.now().plusYears(1),false));
        todos.add(new Todo(++todosCount,"Divya Sharma","Learn Java", LocalDate.now().plusYears(1),false));
        todos.add(new Todo(++todosCount,"Dilbag Singh","Learn Spring", LocalDate.now().plusYears(2),false));
        todos.add(new Todo(++todosCount,"Balwinder Kaur","Learn AWS", LocalDate.now().plusYears(3),false));
    }

    public List<Todo>findByUsername(String username){ // finding the list of todo using provided username
        Predicate<? super Todo> predicate = todo -> todo.getUserName().equalsIgnoreCase(username);
        return todos.stream().filter(predicate).toList();
    }

    public void addTodos(String userName,String description,LocalDate targetDate,boolean done){
        Todo todo = new Todo(++todosCount,userName,description,targetDate,done);
        todos.add(todo);// adding a new todo to todos list
    }

    public void deleteById(int id){
        // todo.getId == id;
        // todo-> todo.getId()==id
        Predicate<? super Todo> predicate = todos->todos.getId()==id;
        todos.removeIf(predicate); // delete that todo for the particular id
    }

    public Todo findById(int id){
        Predicate<? super  Todo> predicate = todos-> todos.getId()==id;
        Todo todo = todos.stream().filter(predicate).findFirst().get();
        return todo;
    }

    public void updateTodo(@Valid Todo todo) {
        deleteById(todo.getId());
        todos.add(todo);
    }
}
