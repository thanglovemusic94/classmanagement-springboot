package com.classmanagement.controller;

import com.classmanagement.model.ClassEntity;
import com.classmanagement.model.Majors;
import com.classmanagement.model.Room;
import com.classmanagement.model.Teacher;
import com.classmanagement.repository.ClassEntityRepository;
import com.classmanagement.repository.MajorsRepository;
import com.classmanagement.repository.RoomRepository;
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
@RequestMapping("")
public class HomeController {



    @Autowired
    private ClassEntityRepository classEntityRepository;

    @Autowired
    private MajorsRepository majorsRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private TeacherRepository teacherRepository;




    @GetMapping("")
    public String index(Model model, @RequestParam(value = "classId", required = false) Integer classId){
        if (classId !=null){
            ClassEntity classEntity = classEntityRepository.findById(classId).get();
            model.addAttribute("classEntity", classEntity);
        }else {
            model.addAttribute("classEntity", new ClassEntity());
        }

        List<Majors> majors = majorsRepository.findAll();
        List<Room> rooms = roomRepository.findAll();
        List<Teacher> teachers = teacherRepository.findAll();
        List<ClassEntity> classEntitys = classEntityRepository.findAll();

        model.addAttribute("majors", majors);
        model.addAttribute("rooms", rooms);
        model.addAttribute("teachers", teachers);
        model.addAttribute("classEntitys", classEntitys);
        return "/index";
    }

    @GetMapping("/edit/{id}")
    public RedirectView edit(Model model, @PathVariable int id, RedirectAttributes redirectAttributes){
        redirectAttributes.addAttribute("classId", id);
        return new RedirectView("/");
    }

    @GetMapping("/delete/{id}")
    public String delete(Model model, @PathVariable int id){
        classEntityRepository.deleteById(id);
        return "redirect:/";
    }

    @PostMapping("/{value}")
    public String createOrEdit(@PathVariable String value,  @ModelAttribute ClassEntity classEntity){
        System.out.printf("sgsg");
        if (value.equals("add")){
            ClassEntity classEntity1 = new ClassEntity();
            classEntity1.setName(classEntity.getName());
            classEntity1.setRoom(classEntity.getRoom());
            classEntity1.setMajors(classEntity.getMajors());
            classEntity1.setTeacher(classEntity.getTeacher());
            classEntityRepository.save(classEntity);
        }

        if (value.equals("edit")){
            Optional<ClassEntity> item = classEntityRepository.findById(classEntity.getId());
            item.ifPresent(i -> {
                classEntityRepository.save(classEntity);
            });
        }

        return "redirect:/";
    }
}
