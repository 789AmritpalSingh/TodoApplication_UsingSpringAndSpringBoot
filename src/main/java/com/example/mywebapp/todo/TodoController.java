package com.example.mywebapp.todo;

import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

//@Controller --> using it as backup , disabled it because to retrieve data from the database
public class TodoController {
    private TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @RequestMapping("list-todos")
    public String listAllTodos(ModelMap model) {

        // we are not using model.get("name") for String userName because its taking value from @Session Attribute from WelcomeController

        String userName = getLoggedInUserName(model);
        List<Todo> todos = todoService.findByUsername(userName);

        model.addAttribute("todos", todos);

        return "listTodos";
    }

    @RequestMapping(value = "add-todo", method = RequestMethod.GET)
    public String showNewTodoPage(ModelMap model) {
        String userName = getLoggedInUserName(model);
        Todo todo = new Todo(0, userName, "", LocalDate.now().plusYears(1), false);
        model.put("todo", todo);
        return "addTodos";
    }


    /*
    @RequestMapping(value = "add-todo", method = RequestMethod.POST)
    public String addNewTodoPage(@RequestParam String description, ModelMap model){
        String userName = (String) model.get("name"); // get the name of user who has logged in
        todoService.addTodos(userName,description, LocalDate.now().plusYears(1),false);
        return "redirect:list-todos"; // redirecting addNewTodoPage back to listAllTodos page
    }

     */

    @RequestMapping(value = "add-todo", method = RequestMethod.POST)
    public String addNewTodoPage(ModelMap model, @Valid Todo todo, BindingResult result) { // binding directly to todo bean
        if(result.hasErrors()){
            return "addTodos"; // if the input is not following validations , then go back to addTodos page
        }
        //String userName = (String) model.get("name"); // get the name of user who has logged in
        String userName = getLoggedInUserName(model);
        todoService.addTodos(userName, todo.getDescription(), todo.getTargetDate(), false);
        return "redirect:list-todos"; // redirecting addNewTodoPage back to listAllTodos page
    }


    @RequestMapping(value = "delete-todo")
    public String deleteTodo(@RequestParam int id){
        // Delete Todo
        todoService.deleteById(id);

        return "redirect:list-todos"; // go back to list-todos jsp page after deleting todos
    }

    @RequestMapping(value = "update-todo",method = RequestMethod.GET)
    public String showUpdateTodoPage(@RequestParam int id, ModelMap model) { // showing update todo page
        //Update Todo
        Todo todo = todoService.findById(id);
        model.addAttribute("todo",todo);
        return "addTodos";
    }

    @RequestMapping(value="update-todo",method = RequestMethod.POST)
    public String updateTodo(ModelMap model, @Valid Todo todo,BindingResult result){
        if(result.hasErrors()){
            return "addTodos";
        }
        String userName = getLoggedInUserName(model);
        todo.setUserName(userName); // adding userName to todo list , target date is pending , we will do that in next step
        todoService.updateTodo(todo);
        return "redirect:list-todos";

    }

    public String getLoggedInUserName(ModelMap model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }
}
