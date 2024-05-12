package com.example.demo.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


import com.example.demo.models.Staff;
import com.example.demo.repositories.StaffResponsitory;

@Controller
@RequestMapping("/")
public class ManageCtr {
    @Autowired
    private StaffResponsitory staffRepository;

    @GetMapping
    public String getAllstaff(Model model) {
        List<Staff> staffs = staffRepository.findAll();
        model.addAttribute("staffs", staffs);
        return "StaffManageFrm";
    }

    @GetMapping("/staff/{staffId}")
    public String getStaff(Model model, @PathVariable("staffId") Integer staffId) {
        Optional<Staff> staff = staffRepository.findById(staffId);
        if (staff.isPresent()) {
            model.addAttribute("staff", staff.get());
            model.addAttribute("error", null);
        } else {
            model.addAttribute("error", "Không tìm thấy staff với id = " + staffId);
        }
        return "EditFrm";
    }

    @PostMapping(value = "/staffs/addStaff")
    public String addStaff(
            Model model,
            @RequestBody Staff staff

    ) {
        Staff newStaff = staffRepository.save(staff);
        model.addAttribute("staff", newStaff);
        return "redirect:/StaffManageFrm";
    }

    @PutMapping(value = "/staffs/{id}")
    public String editStaff(
            Model model,
            @RequestBody Staff staff,
            @PathVariable("id") Integer id

    ) {
        Optional<Staff> existingStaff = staffRepository.findById(id);
        if (existingStaff.isPresent()) {
            Staff newStaff = existingStaff.get();
            newStaff.setName(staff.getName());
            newStaff.setAccount(staff.getAccount());
            newStaff.setPhone(staff.getPhone());
            newStaff.setPassword(staff.getPassword());
            newStaff.setAddress(staff.getAddress());
            newStaff.setRole(staff.getRole());
            model.addAttribute("staff", newStaff);
        } else {
            model.addAttribute("error", "Lỗi lầm");

        }
        return "redirect:/StaffManageFrm";
    }
    @DeleteMapping(value = "/staffs/{id}")
    public String deleteStaff(
            Model model,
            @PathVariable("id") Integer id

    ) {
        staffRepository.deleteById(id);
        return "redirect:/StaffManageFrm";
    }

    @GetMapping("/staffs/addStaff")
    public String addStaffFrm(Model model) {
        model.addAttribute("staff", new Staff());
        return "AddFrm";
    }


}
