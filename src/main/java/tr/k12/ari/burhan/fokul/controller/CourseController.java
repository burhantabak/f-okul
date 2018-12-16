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
    public String addPerson(Model model) {
        model.addAttribute("person", new Course());
        return "add";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String savePerson(@Valid Course course, BindingResult bindingResult, Model model) {
        courseRepository.save(course);
        return "redirect:/";
    }

    @RequestMapping("/list")
    public String list(Model model) {
        Iterable<Course> peoples = courseRepository.findAll();
        model.addAttribute("courses", peoples);
        return "courseList";
    }

    @RequestMapping("/edit")
    public String edit(@RequestParam("id") Long id, Model model) {
        Course course = courseRepository.findOne(id);
        model.addAttribute("course", course);
        return "add";
    }

    @RequestMapping("/messages")
    public String showMessages(Long courseId, Model model) {
        model.addAttribute("person", courseRepository.findOne(courseId));
        return "message_list";
    }

    @RequestMapping("/search")
    public String search(String name, Model model) {
        Iterable<Course> peoples = courseRepository.findByNameUsingJPQL(name);
        model.addAttribute("peoples", peoples);
        return "personList";
    }
}
