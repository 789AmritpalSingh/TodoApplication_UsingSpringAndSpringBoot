package com.example.mywebapp.login;


//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;


@Controller
@SessionAttributes("name")
public class LoginControllerApplication {

    //private Logger logger = LoggerFactory.getLogger(getClass());
//    @RequestMapping("login")
//    public String loginControl(@RequestParam String bio, ModelMap model){
//        model.put("name",bio);

        // instead of print statement ,  logger is used
//        logger.debug("Requested Param at debug level is {}",bio);
//        logger.info("Requested Param at info level is {}",bio);
        //System.out.println("Request Param is "+bio); // not recommended for production application


//    @RequestMapping("login")
//    public String logicControl(){
//        return "loginControl";
//    }

    private AuthenticationService authenticationService; // for authentication purpose of username and password

    public LoginControllerApplication(AuthenticationService authenticationService){
        this.authenticationService = authenticationService;
    }

    @RequestMapping(value = "login",method = RequestMethod.GET)
    public String goToLoginPage(){
        return "loginControl";
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String goToWelcomePage(@RequestParam String name, @RequestParam String password, ModelMap model){
        if(authenticationService.authenticate(name,password)) { // if name and password matches , then return welcome page
            model.put("name", name);
            //model.put("password", password); -->we are not putting password in welcome page as it is not a good practice
            return "welcome";
        }

        model.put("errorMessage","username and password provided by you does not match to actual username and password." +
                " Try again!");
        return "loginControl"; // if it does not match return loginControl page
    }


}
