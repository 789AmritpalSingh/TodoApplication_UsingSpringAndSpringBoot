package com.example.mywebapp.todo;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@Controller
public class TodoController {
    private TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @RequestMapping("list-todos")
    public String listAllTodos(ModelMap model) {

        List<Todo> todos = todoService.findByUsername("Amritpal Singh");

        model.addAttribute("todos", todos);

        return "listTodos";
    }

    @RequestMapping(value = "add-todo", method = RequestMethod.GET)
    public String showNewTodoPage(ModelMap model) {
        String userName = (String) model.get("name");
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
        String userName = (String) model.get("name"); // get the name of user who has logged in
        todoService.addTodos(userName, todo.getDescription(), LocalDate.now().plusYears(1), false);
        return "redirect:list-todos"; // redirecting addNewTodoPage back to listAllTodos page
    }
}
