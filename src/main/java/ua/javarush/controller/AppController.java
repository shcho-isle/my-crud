package ua.javarush.controller;

import java.util.List;
import java.util.Locale;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ua.javarush.model.User;
import ua.javarush.service.UserService;

@Controller
@RequestMapping("/")
public class AppController {
    private static final int MAX_ROWS_PER_PAGE = 15;

    private final UserService service;

    private final MessageSource messageSource;

    @Autowired
    public AppController(UserService service, MessageSource messageSource) {
        this.service = service;
        this.messageSource = messageSource;
    }

    @RequestMapping(value = {"/", "/list"}, method = RequestMethod.GET)
    public String listUsers(ModelMap model, @RequestParam(required = false) Integer page) {

        List<User> users = service.findAllUsers();
        Integer currentPage = page;

        PagedListHolder<User> pagedListHolder = new PagedListHolder<User>(users);
        pagedListHolder.setPageSize(MAX_ROWS_PER_PAGE);
        model.addAttribute("maxPages", pagedListHolder.getPageCount());

        if (currentPage == null || currentPage < 1 || currentPage > pagedListHolder.getPageCount()) {
            currentPage = 1;
        }
        model.addAttribute("page", currentPage);
        if (currentPage < 1 || currentPage > pagedListHolder.getPageCount()) {
            pagedListHolder.setPage(0);
            model.addAttribute("users", pagedListHolder.getPageList());
        } else if (currentPage <= pagedListHolder.getPageCount()) {
            pagedListHolder.setPage(currentPage - 1);
            model.addAttribute("users", pagedListHolder.getPageList());
        }

        return "allusers";
    }

    @RequestMapping(value = {"/new"}, method = RequestMethod.GET)
    public String newUser(ModelMap model) {
        User user = new User();
        model.addAttribute("user", user);
        return "registration";
    }

    @RequestMapping(value = {"/new"}, method = RequestMethod.POST)
    public String saveUser(@Valid User user, BindingResult result,
                           ModelMap model, Locale locale) {

        if (result.hasErrors()) {
            return "registration";
        }

        if (!service.isUserNameUnique(user.getId(), user.getName())) {
            FieldError nameError = new FieldError("user", "name", messageSource.getMessage("non.unique.name", new String[]{user.getName()}, locale));
            result.addError(nameError);
            return "registration";
        }

        service.saveUser(user);

        model.addAttribute("success", "User " + user.getName() + " registered successfully");
        return "success";
    }

    @RequestMapping(value = {"/edit-{name}-user"}, method = RequestMethod.GET)
    public String editUser(@PathVariable String name, ModelMap model) {
        User user = service.findUserByName(name);
        model.addAttribute("user", user);
        return "registration";
    }

    @RequestMapping(value = {"/edit-{name}-user"}, method = RequestMethod.POST)
    public String updateUser(@Valid User user, BindingResult result,
                             ModelMap model, @PathVariable String name) {

        if (result.hasErrors()) {
            return "registration";
        }

        if (!service.isUserNameUnique(user.getId(), user.getName())) {
            FieldError nameError = new FieldError("user", "name", messageSource.getMessage("non.unique.name", new String[]{user.getName()}, Locale.getDefault()));
            result.addError(nameError);
            return "registration";
        }

        service.updateUser(user);

        model.addAttribute("success", "User " + user.getName() + " updated successfully");
        return "success";
    }

    @RequestMapping(value = {"/delete-{name}-user"}, method = RequestMethod.GET)
    public String deleteUser(@PathVariable String name) {
        service.deleteUserByName(name);
        return "redirect:/list";
    }

    @RequestMapping("searchUser")
    public String searchUser(ModelMap model, @RequestParam("searchName") String searchName) {
        List<User> usersList = service.findUsersByName(searchName);
        model.addAttribute("users", usersList);
        return "allusers";

    }
}
