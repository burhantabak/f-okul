package tr.k12.ari.burhan.fokul.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import tr.k12.ari.burhan.fokul.model.Student;
import tr.k12.ari.burhan.fokul.repositories.StudentRepository;

import javax.validation.Valid;

@Controller
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String submit(@Valid @ModelAttribute("student")Student student,
                         BindingResult result, ModelMap model) {
//        if (result.hasErrors()) {
//            return "error";
//        }
        studentRepository.save(student);
        return "redirect:/admin/list";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String savePerson(@Valid Student student, BindingResult bindingResult, Model model) {
        studentRepository.save(student);
        return "redirect:/";
    }

    @RequestMapping("/list")
    public String list(Model model) {
        Iterable<Student> students = studentRepository.findAll();
        model.addAttribute("students", students);
        return "student-list";
    }
}
