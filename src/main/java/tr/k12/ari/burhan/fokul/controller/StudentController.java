package tr.k12.ari.burhan.fokul.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import tr.k12.ari.burhan.fokul.model.Grade;
import tr.k12.ari.burhan.fokul.model.Student;
import tr.k12.ari.burhan.fokul.repositories.StudentRepository;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String submit(@Valid @ModelAttribute("student") Student student,
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

    @RequestMapping("/listGrades")
    public String listGrades(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUser = authentication.getName();
        Student student = studentRepository.findByUsername(currentUser).iterator().next();
        model.addAttribute("student", student);
        List<String> messages = analyzeGrades(student);
        model.addAttribute("messages", messages);

        return "student-list-grades";
    }

    private List<String> analyzeGrades(Student student) {
        List<Grade> grades = student.getGrades();
        List<String> messages = new ArrayList<>();
        analyzeMinGrade(messages, grades);
        analyzeMaxGrade(grades, messages);
        specialGradeAnalysis(grades, messages);

        return messages;
    }

    private void specialGradeAnalysis(List<Grade> grades, List<String> messages) {
        Double f = null;
        Grade maxF = null;
        for (Grade g : grades) {
            if (maxF == null || specialWeightFunction(g) > f) {
                maxF = g;
                f = specialWeightFunction(g);
            }
        }
        if (maxF != null) {
            messages.add(String.format("Ağırlık analizine göre %s dersine çalıştığında en yüksek faydayı sağlayabilirsin. ", maxF.getCourse().getName()));
        }
    }

    private double specialWeightFunction(Grade g) {
        double v = g.getValue();
        int k = g.getCourse().getCoefficient();
        return (100 - v) * k;
    }

    private void analyzeMaxGrade(List<Grade> grades, List<String> messages) {
        Double max = null;
        Grade maxG = null;
        for (Grade g : grades) {
            if (max == null || g.getValue() > max) {
                max = g.getValue();
                maxG = g;
            }
        }
        if (maxG != null) {
            messages.add(String.format("En yüksek notu %s dersinden %.0f olarak almışsın. Tebrikler. ", maxG.getCourse().getName(), maxG.getValue()));
        }
    }

    private void analyzeMinGrade(List<String> messages, List<Grade> grades) {
        Double min = null;
        Grade minG = null;
        for (Grade g : grades) {
            if (min == null || g.getValue() < min) {
                min = g.getValue();
                minG = g;
            }
        }
        if (minG != null && min < 100) {
            messages.add(String.format("En düşük notu %s dersinden %.0f olarak almışsın. Bu derse daha çok eğilebilirsin. ", minG.getCourse().getName(), minG.getValue()));
        }
    }
}
