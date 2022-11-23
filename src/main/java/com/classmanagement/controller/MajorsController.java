package com.classmanagement.controller;

import com.classmanagement.model.Majors;
import com.classmanagement.repository.MajorsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/major")
public class MajorsController {
    @Autowired
    private MajorsRepository majorsRepository;

    @GetMapping("")
    public String index(Model model, @RequestParam(value = "majorId", required = false) Integer majorId){
        if (majorId !=null){
            Majors major = majorsRepository.findById(majorId).get();
            model.addAttribute("major", major);
        }else {
            model.addAttribute("major", new Majors());
        }

        List<Majors> majors = majorsRepository.findAll();
        model.addAttribute("majors", majors);
        return "/major/index";
    }

    @GetMapping("/edit/{id}")
    public RedirectView edit(Model model, @PathVariable int id, RedirectAttributes redirectAttributes){
        redirectAttributes.addAttribute("majorId", id);
        return new RedirectView("/major");
    }

    @GetMapping("/delete/{id}")
    public String delete(Model model, @PathVariable int id){
        majorsRepository.deleteById(id);
        return "redirect:/major";
    }

    @PostMapping("/{value}")
    public String createOrEdit(@PathVariable String value,  @ModelAttribute Majors major){
        System.out.printf("sgsg");
        if (value.equals("add")){
            majorsRepository.save(major);
        }

        if (value.equals("edit")){
            Optional<Majors> item = majorsRepository.findById(major.getId());
            item.ifPresent(i -> {
                majorsRepository.save(major);
            });
        }

        return "redirect:/major";
    }
}
