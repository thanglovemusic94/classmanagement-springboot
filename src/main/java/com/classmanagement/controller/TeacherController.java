package com.classmanagement.controller;

import com.classmanagement.model.Teacher;
import com.classmanagement.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/teacher")
public class TeacherController {
    @Autowired
    private TeacherRepository teacherRepository;

    @GetMapping("")
    public String index(Model model, @RequestParam(value = "teacherId", required = false) Integer teacherId){
        if (teacherId !=null){
                Teacher teacher = teacherRepository.findById(teacherId).get();
                model.addAttribute("teacher", teacher);
        }else {
            model.addAttribute("teacher", new Teacher());
        }

        List<Teacher>  teachers = teacherRepository.findAll();
        model.addAttribute("teachers", teachers);
        return "/teacher/index";
    }

    @GetMapping("/edit/{id}")
    public RedirectView edit(Model model, @PathVariable int id, RedirectAttributes redirectAttributes){
        redirectAttributes.addAttribute("teacherId", id);
        return new RedirectView("/teacher");
    }

    @GetMapping("/delete/{id}")
    public String delete(Model model, @PathVariable int id){
        teacherRepository.deleteById(id);
        return "redirect:/teacher";
    }

    @PostMapping("/{value}")
    public String createOrEdit(@PathVariable String value,  @ModelAttribute Teacher teacher){
        System.out.printf("sgsg");
        if (value.equals("add")){
            teacherRepository.save(teacher);
        }

        if (value.equals("edit")){
            Optional<Teacher> item = teacherRepository.findById(teacher.getId());
            item.ifPresent(i -> {
                teacherRepository.save(teacher);
            });
        }

        return "redirect:/teacher";
    }
}
