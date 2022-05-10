package org.example.config.conrollers;


import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@org.springframework.stereotype.Controller
public class Controller {

    private String name;

    @GetMapping("/hello")
    public String hello(@RequestParam(value = "name") String name, Model model)
   {
       System.out.println("Hello"+name);
       model.addAttribute("message","Hello "+name);
       return "hello";
    }

    @GetMapping("/goodbye")
    public String goodbye(@RequestParam(value = "name")String name){
        System.out.println("Good bye"+name);
       return "goodbye";
    }

}
