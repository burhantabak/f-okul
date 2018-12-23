package tr.k12.ari.burhan.fokul.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import tr.k12.ari.burhan.fokul.model.Student;
import tr.k12.ari.burhan.fokul.repositories.StudentRepository;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private StudentRepository studentRepository;

    @RequestMapping("/list")
    public String list(Model model) {
        model.addAttribute("student", new Student());
        model.addAttribute("students", studentRepository.findAll());
        return "admin-index";
    }
}
