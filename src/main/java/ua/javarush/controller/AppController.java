package ua.javarush.controller;

import java.util.List;
import java.util.Locale;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import ua.javarush.model.User;
import ua.javarush.service.UserService;

@Controller
@RequestMapping("/")
public class AppController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    private static final int MAX_ROWS_PER_PAGE = 15;

    private final UserService service;

    private final MessageSource messageSource;

    @Autowired
    public AppController(UserService service, MessageSource messageSource) {
        this.service = service;
        this.messageSource = messageSource;
    }

    @GetMapping(value = {"/", "/list"})
    public String getAll(ModelMap model, @RequestParam(required = false) Integer page) {
        List<User> users = service.getAll();
        log.info("getAllUsers");
        Integer currentPage = page;

        PagedListHolder<User> pagedListHolder = new PagedListHolder<>(users);
        pagedListHolder.setPageSize(MAX_ROWS_PER_PAGE);
        model.addAttribute("maxPages", pagedListHolder.getPageCount());

        if (currentPage == null || currentPage < 1 || currentPage > pagedListHolder.getPageCount()) {
            currentPage = 1;
        }
        model.addAttribute("page", currentPage);
        if (currentPage > pagedListHolder.getPageCount()) {
            pagedListHolder.setPage(0);
            model.addAttribute("users", pagedListHolder.getPageList());
        } else if (currentPage <= pagedListHolder.getPageCount()) {
            pagedListHolder.setPage(currentPage - 1);
            model.addAttribute("users", pagedListHolder.getPageList());
        }

        return "allUsers";
    }

    @GetMapping("/new")
    public String newUser(ModelMap model) {
        User user = new User();
        model.addAttribute("user", user);
        return "registration";
    }

    @PostMapping("/new")
    public String save(@Valid User user, BindingResult result,
                       ModelMap model, Locale locale) {

        if (result.hasErrors()) {
            return "registration";
        }

        if (service.isUserNameUnique(user.getId(), user.getName())) {
            service.save(user);
            log.info("save {}", user);
            model.addAttribute("success", messageSource.getMessage("jsp.registered", new String[]{user.getName()}, locale));
            return "success";
        }

        FieldError nameError = new FieldError("user", "name", messageSource.getMessage("non.unique.name", new String[]{user.getName()}, locale));
        result.addError(nameError);
        return "registration";
    }

    @GetMapping("/edit-{name}-user")
    public String getByName(@PathVariable String name, ModelMap model) {
        User user = service.getByName(name);
        model.addAttribute("user", user);
        log.info("get {}", user);
        return "registration";
    }

    @PostMapping("/edit-{name}-user")
    public String update(@Valid User user, BindingResult result,
                         ModelMap model, @PathVariable String name,
                         Locale locale) {

        if (result.hasErrors()) {
            return "registration";
        }

        if (service.isUserNameUnique(user.getId(), user.getName())) {
            service.update(user);
            log.info("update {}", user);
            model.addAttribute("success", messageSource.getMessage("jsp.updated", new String[]{user.getName()}, locale));
            return "success";
        }

        FieldError nameError = new FieldError("user", "name", messageSource.getMessage("non.unique.name", new String[]{user.getName()}, locale));
        result.addError(nameError);
        return "registration";
    }

    @GetMapping("/delete-{name}-user")
    public String deleteByName(@PathVariable String name) {
        service.deleteByName(name);
        log.info("delete {}", name);
        return "redirect:/list";
    }

    @RequestMapping("searchUser")
    public String findByName(ModelMap model, @RequestParam("searchName") String searchName) {
        List<User> usersList = service.findByName(searchName);
        model.addAttribute("users", usersList);
        log.info("findUsersByName {}", searchName);
        return "allUsers";
    }
}
