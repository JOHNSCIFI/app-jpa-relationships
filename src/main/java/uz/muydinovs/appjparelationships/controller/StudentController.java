package uz.muydinovs.appjparelationships.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import uz.muydinovs.appjparelationships.entity.Student;
import uz.muydinovs.appjparelationships.repository.StudentRepository;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentRepository studentRepository;

    @GetMapping("/forMinistry")
    public Page<Student> getAllStudentList(@RequestParam int page) {
        Pageable pageable = PageRequest.of(page, 10);
        return studentRepository.findAll(pageable);
    }

    @GetMapping("/forUniversity/{id}")
    public Page<Student> getStudentByUniversityId(@RequestParam int page, @PathVariable Integer id) {
        Pageable pageable = PageRequest.of(page, 10);
        return studentRepository.findAllByGroup_Faculty_University_Id(id,pageable);
    }
}
