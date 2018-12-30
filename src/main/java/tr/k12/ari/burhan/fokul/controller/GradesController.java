package tr.k12.ari.burhan.fokul.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import tr.k12.ari.burhan.fokul.model.Grade;
import tr.k12.ari.burhan.fokul.model.GradeHolder;
import tr.k12.ari.burhan.fokul.model.Student;
import tr.k12.ari.burhan.fokul.repositories.CourseRepository;
import tr.k12.ari.burhan.fokul.repositories.GradeRepository;
import tr.k12.ari.burhan.fokul.repositories.StudentRepository;

import javax.validation.Valid;

@Controller
@RequestMapping("/grade")
public class GradesController {
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private GradeRepository gradeRepository;

    @RequestMapping(value = "/list/{studentId}")
    public String listGrades(@PathVariable long studentId, Model model) {
        Student student = studentRepository.findOne(studentId);
        model.addAttribute("student", student);

        GradeHolder grade = new GradeHolder();
        grade.setStudent(student.getId());
        model.addAttribute("grade", grade);

        model.addAttribute("courses", courseRepository.findAll());
        return "grades-list-grades";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String save(@Valid @ModelAttribute("grade") GradeHolder gradeHolder,
                       BindingResult result, ModelMap model) {
        Grade grade = gradeHolder.getGrade();
        grade.setCourse(courseRepository.findOne(gradeHolder.getCourse()));
        Student student = studentRepository.findOne(gradeHolder.getStudent());
        grade.setStudent(student);
        gradeRepository.save(grade);
        return "redirect:/grade/list/" + student.getId();
    }
}
