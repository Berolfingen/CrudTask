package com.leonid.crudtask.controller;

import com.leonid.crudtask.model.User;
import com.leonid.crudtask.options.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@SessionAttributes(value = { "user", "filter" })
public class UserController {
    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    private UserService userService;

    @RequestMapping("/index")
    public ModelAndView setupForm(HttpServletRequest request){

        ModelAndView modelAndView = new ModelAndView("index");
        String name = null;
        Page<User> page = null;
        Integer pageNumber = (Integer) request.getSession().getAttribute("currentIndex");
        if ((name = (String) request.getSession().getAttribute("filter")) != null){
            page =  userService.findByNameContaining(name, 1);
        } else {
            page = userService.getAllUser(1);
        }

        modelAndView.clear();
        modelAndView.addObject("user", new User());
        int current = page.getNumber() + 1;
        int begin = Math.max(1, current - 2);
        int end = Math.min(begin + 5, page.getTotalPages());

        modelAndView.addObject("pageData", page);
        modelAndView.addObject("beginIndex", begin);
        modelAndView.addObject("endIndex", end);
        modelAndView.addObject("currentIndex", current);
        return modelAndView;
    }

    @RequestMapping(value = "/pages/{pageNumber}", method = RequestMethod.GET)
    public ModelAndView getRunbookPage(@PathVariable Integer pageNumber,
                                       ModelAndView modelAndView,
                                       HttpServletRequest request) {

        String name = null;
        Page<User> page = null;
        if ((name = (String) request.getSession().getAttribute("filter")) != null){
            page =  userService.findByNameContaining(name, pageNumber);
        } else {
            page = userService.getAllUser(pageNumber);
        }

        int current = page.getNumber() + 1;
        int begin = Math.max(1, current - 2);
        int end = Math.min(begin + 5, page.getTotalPages());
        modelAndView.addObject("pageData", page);
        modelAndView.addObject("beginIndex", begin);
        modelAndView.addObject("endIndex", end);
        modelAndView.addObject("currentIndex", current);
        modelAndView.addObject("user", new User());
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @RequestMapping(value="user.do", method=RequestMethod.POST)
    public ModelAndView doActionsPost(@Valid @ModelAttribute User user,
                                      BindingResult result,
                                      @RequestParam String action,
                                      HttpServletRequest request){
        if (result.hasErrors()){
            ModelAndView modelAndView = new ModelAndView("index");
            Page<User> page = userService.getAllUser(1);
            modelAndView.addObject("pageData", page);

            int current = page.getNumber() + 1;
            int begin = Math.max(1, current - 2);
            int end = Math.min(begin + 5, page.getTotalPages());

            modelAndView.addObject("pageData", page);
            modelAndView.addObject("beginIndex", begin);
            modelAndView.addObject("endIndex", end);
            modelAndView.addObject("currentIndex", current);
            return modelAndView;
        }

        userService.add(user);
        ModelAndView modelAndView = new ModelAndView("index");
        String name = null;
        Page<User> page = null;

        int pageNumber = 1;
        if ((name = (String) request.getSession().getAttribute("filter")) != null){
            while (true) {
                page =  userService.findByNameContaining(name, pageNumber);
                boolean isFound = false;
                for (User user1 : page.getContent()){
                    if (user1.getId() == user.getId()){
                        isFound = true;
                        break;
                    }
                }
                if (isFound) break;
                pageNumber++;
            }
        } else {
            while (true) {
                page = userService.getAllUser(pageNumber);
                boolean isFound = false;
                for (User user1 : page.getContent()){
                    if (user1.getId() == user.getId()){
                        isFound = true;
                        break;
                    }
                }
                if (isFound) break;
                pageNumber++;
            }
        }

        int current = page.getNumber() + 1;
        int begin = Math.max(1, current - 2);
        int end = Math.min(begin + 5, page.getTotalPages());
        modelAndView.addObject("pageData", page);
        modelAndView.addObject("beginIndex", begin);
        modelAndView.addObject("endIndex", end);
        modelAndView.addObject("currentIndex", current);
        return modelAndView;
    }

    @RequestMapping(value="filter", method=RequestMethod.POST)
    public ModelAndView doFilter(@RequestParam("name") String name,
                                 @RequestParam String action,
                                 HttpServletRequest request){
        Page<User> page = null;
        ModelAndView modelAndView = new ModelAndView("index");

        switch(action.toLowerCase()){
            case "filter":
                page =  userService.findByNameContaining(name, 1);
                modelAndView.addObject("filter", name);
                break;

            case "clear":
                page =  userService.findByNameContaining("", 1);
                modelAndView.addObject("filter", "");
                break;
        }
        int current = page.getNumber() + 1;
        int begin = Math.max(1, current - 2);
        int end = Math.min(begin + 5, page.getTotalPages());
        modelAndView.addObject("pageData", page);
        modelAndView.addObject("beginIndex", begin);
        modelAndView.addObject("endIndex", end);
        modelAndView.addObject("currentIndex", current);
        modelAndView.addObject("pageData", page);
        return modelAndView;
    }

    @RequestMapping(value="user.do", method=RequestMethod.GET)
    public ModelAndView doActionsGet(@ModelAttribute User user,
                                     BindingResult result,
                                     @RequestParam String action,
                                     HttpServletRequest request){
        User userResult = new User();

        switch(action.toLowerCase()){
            case "delete":
                userService.delete(user.getId());
                userResult = new User();
                break;
            case "search":
                User searchedUser = userService.getUser(user.getId());
                userResult = searchedUser != null ? searchedUser : new User();
                break;
        }

        String name = null;
        Page<User> page = null;
        String[] referrer = request.getHeader("referer").split("/");

        String pageNumberString = referrer[referrer.length-1];
        Integer pageNumber = null;
        try {
            pageNumber = Integer.parseInt(pageNumberString);
        } catch (NumberFormatException e) {
            pageNumber = 1;
        }

        if ((name = (String) request.getSession().getAttribute("filter")) != null){
            page =  userService.findByNameContaining(name, pageNumber);
        } else {
            page = userService.getAllUser(pageNumber);
        }

        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("user", userResult);
        int current = page.getNumber() + 1;
        int begin = Math.max(1, current - 2);
        int end = Math.min(begin + 5, page.getTotalPages());
        modelAndView.addObject("pageData", page);
        modelAndView.addObject("beginIndex", begin);
        modelAndView.addObject("endIndex", end);
        modelAndView.addObject("currentIndex", current);
        modelAndView.addObject("pageData", page);
        return modelAndView;
    }
}