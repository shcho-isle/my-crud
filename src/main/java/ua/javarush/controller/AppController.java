package ua.javarush.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import ua.javarush.dao.UserDao;
import ua.javarush.model.User;

@Controller
@RequestMapping("/")
public class AppController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    private static final int MAX_ROWS_PER_PAGE = 15;

    private final UserDao dao;

    @Autowired
    public AppController(UserDao dao) {
        this.dao = dao;
    }

    @GetMapping(value = {"/", "/list"})
    public String getAll(ModelMap model, @RequestParam(required = false) Integer page) {
        List<User> users = dao.getAll();
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
    public String create(ModelMap model) {
        User user = new User();
        model.addAttribute("user", user);
        return "registration";
    }

    @PostMapping("/new")
    public String save(@Valid User user, BindingResult result, ModelMap model) {

        if (result.hasErrors()) {
            return "registration";
        }

        dao.save(user);
        log.info("save {}", user);
        model.addAttribute("isNew", true);
        model.addAttribute("userName", user.getName());
        return "success";
    }

    @GetMapping("/edit-user-No.{id}")
    public String get(@PathVariable Integer id, ModelMap model) {
        User user = dao.get(id);
        model.addAttribute("user", user);
        log.info("get {}", user);
        return "registration";
    }

    @PostMapping("/edit-user-No.{id}")
    public String update(@Valid User user, BindingResult result,
                         ModelMap model, @PathVariable Integer id) {

        if (result.hasErrors()) {
            return "registration";
        }

        dao.update(user);
        log.info("update {}", user);
        model.addAttribute("isNew", false);
        model.addAttribute("userName", user.getName());
        return "success";
    }

    @GetMapping("/delete-{id}-user")
    public String delete(@PathVariable Integer id) {
        dao.delete(id);
        log.info("delete {}", id);
        return "redirect:/list";
    }

    @RequestMapping("searchUser")
    public String findByName(ModelMap model, @RequestParam("searchName") String searchName) {
        List<User> usersList = dao.findByName(searchName);
        model.addAttribute("users", usersList);
        log.info("findUsersByName {}", searchName);
        return "allUsers";
    }
}
