package tr.k12.ari.burhan.fokul.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import tr.k12.ari.burhan.fokul.model.Course;
import tr.k12.ari.burhan.fokul.repositories.CourseRepository;

import javax.validation.Valid;

@Controller
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private CourseRepository courseRepository;

    @RequestMapping("/add")
    public String addCourse(Model model) {
        model.addAttribute("course", new Course());
        return "course-add";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String savePerson(@Valid Course course, BindingResult bindingResult, Model model) {
        courseRepository.save(course);
        return "redirect:/";
    }

    @RequestMapping("/list")
    public String list(Model model) {
        Iterable<Course> courses = courseRepository.findAll();
        model.addAttribute("courses", courses);
        return "course-list";
    }
}
