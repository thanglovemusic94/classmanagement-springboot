package com.classmanagement.controller;

import com.classmanagement.model.Room;
import com.classmanagement.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/room")
public class RoomController {
    @Autowired
    private RoomRepository roomRepository;

    @GetMapping("")
    public String index(Model model, @RequestParam(value = "roomId", required = false) Integer roomId){
        if (roomId !=null){
            Room room = roomRepository.findById(roomId).get();
            model.addAttribute("room", room);
        }else {
            model.addAttribute("room", new Room());
        }

        List<Room> rooms = roomRepository.findAll();
        model.addAttribute("rooms", rooms);
        return "/room/index";
    }

    @GetMapping("/edit/{id}")
    public RedirectView edit(Model model, @PathVariable int id, RedirectAttributes redirectAttributes){
        redirectAttributes.addAttribute("roomId", id);
        return new RedirectView("/room");
    }

    @GetMapping("/delete/{id}")
    public String delete(Model model, @PathVariable int id){
        roomRepository.deleteById(id);
        return "redirect:/room";
    }

    @PostMapping("/{value}")
    public String createOrEdit(@PathVariable String value,  @ModelAttribute Room room){
        System.out.printf("sgsg");
        if (value.equals("add")){
            roomRepository.save(room);
        }

        if (value.equals("edit")){
            Optional<Room> item = roomRepository.findById(room.getId());
            item.ifPresent(i -> {
                roomRepository.save(room);
            });
        }

        return "redirect:/room";
    }
}
