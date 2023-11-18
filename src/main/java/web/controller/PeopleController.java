package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import web.model.User;
import web.service.UserServiceImpl;

@Controller
public class PeopleController {
    private final UserServiceImpl userService;

    @Autowired
    public PeopleController(UserServiceImpl userService) {
        this.userService = userService;
    }


    @GetMapping("/people")
    public String index(Model model) {
        model.addAttribute("people", userService.index());
        return "index";
    }
    @GetMapping("show_people")
    public String show(@RequestParam(defaultValue = "5") Integer id, Model model) {
        model.addAttribute("user", userService.show(id));
        return "show";
    }
    @GetMapping("/new_user")
    public String newUser(@ModelAttribute("user") User user) {
        return "new";
    }
    @PostMapping("/create_people")
    public String create(@ModelAttribute("user") User user) {
        userService.save(user);
        return "redirect:/people";
    }

    @GetMapping("/new_update")
    public String editUser(@RequestParam("id") Integer id, @RequestParam("firstName") String firstName,
                           @RequestParam("lastName") String lastName, @RequestParam("age") int age,
                           @ModelAttribute("user") User user, Model model) {
        model.addAttribute("person", userService.show(id));
        return "edit";
    }
    //
    @PostMapping("/edit_people")
    public String update(@RequestParam("id") Integer id, Model model, @ModelAttribute("user") User user) {
        userService.update(id, user);
        return "redirect:/people";
    }
    @GetMapping("/remove_user")
    public String deleteUser(@ModelAttribute("user") User user) {
        return "delete";
    }
    //
    @PostMapping("/delete_people")
    public String delete(Integer id, @ModelAttribute("user") User user) {
        userService.delete(id);
        return "redirect:/people";
    }
}
