package tr.k12.ari.burhan.fokul.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import tr.k12.ari.burhan.fokul.model.Grade;
import tr.k12.ari.burhan.fokul.repositories.GradeRepository;

import javax.validation.Valid;

@Controller
@RequestMapping("/grades")
public class GradesController {

    @Autowired
    private GradeRepository gradeRepository;

    @RequestMapping("/add")
    public String addGrade(Model model) {
        model.addAttribute("grade", new Grade());
        return "grade-add";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String savePerson(@Valid Grade grade, BindingResult bindingResult, Model model) {
        gradeRepository.save(grade);
        return "redirect:/";
    }

    @RequestMapping("/list")
    public String list(Model model) {
        Iterable<Grade> grades = gradeRepository.findAll();
        model.addAttribute("grades", grades);
        return "grades-list";
    }
}
