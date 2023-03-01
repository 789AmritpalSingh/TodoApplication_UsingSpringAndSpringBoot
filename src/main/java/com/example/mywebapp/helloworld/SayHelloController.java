package com.example.mywebapp.helloworld;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SayHelloController {
    @RequestMapping("hello-world")
    @ResponseBody
    public String helloWorld(){
        return "Hello!, How are you doing today";
    }

    @RequestMapping("hello-world-html")
    @ResponseBody
    public String helloWorldHtml(){
        StringBuffer sb = new StringBuffer(); // we are not using string because they are immutable
        sb.append("<html>");
        sb.append("<head>");
        sb.append("<title>HTML hardcoded webpage</title>");
        sb.append("</head>");
        sb.append("<body>");
        sb.append("HTML hardcoded body");
        sb.append("</body>");
        sb.append("</html>");

        return sb.toString();


    }
    @RequestMapping("hello-world-jsp")
    public String helloWorldJsp(){
        return "sayHello";
    } // you have to return the jsp file name in the string, otherwise it gives white label error

    @RequestMapping("hello-world-param")
    public String requestParam(@RequestParam String parameter, ModelMap model){
        model.put("name",parameter);
        return "sayHello";
    }

}
