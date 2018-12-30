package tr.k12.ari.burhan.fokul.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import tr.k12.ari.burhan.fokul.model.Course;
import tr.k12.ari.burhan.fokul.model.Student;
import tr.k12.ari.burhan.fokul.repositories.CourseRepository;
import tr.k12.ari.burhan.fokul.repositories.StudentRepository;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    @RequestMapping("/list")
    public String list(Model model) {
        model.addAttribute("student", new Student());
        model.addAttribute("students", studentRepository.findAll());
        model.addAttribute("course", new Course());
        model.addAttribute("courses", courseRepository.findAll());
        return "admin-index";
    }
}
