package uz.muydinovs.appjparelationships.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.muydinovs.appjparelationships.entity.Subject;
import uz.muydinovs.appjparelationships.repository.SubjectRepository;

import java.util.List;

@RestController
@RequestMapping(value = "/subject")
public class SubjectController {

    @Autowired
    SubjectRepository subjectRepository;
    @PostMapping
    public String add(@RequestBody Subject subject){
        if (subjectRepository.existsByName(subject.getName())) {
            return "Subject is already exist";
        }
        subjectRepository.save(subject);
        return "Subject added";
    }

    @GetMapping
    public List<Subject> getAll(){
        return subjectRepository.findAll();
    }

    @GetMapping(value = "/{id}")
    public Subject getById(@PathVariable Integer id){
        return subjectRepository.getById(id);
    }

    @DeleteMapping(value = "/{id}")
    public String delete(@PathVariable Integer id){
        subjectRepository.deleteById(id);
        return "Deleted";
    }

}
