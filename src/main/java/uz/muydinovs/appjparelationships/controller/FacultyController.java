package uz.muydinovs.appjparelationships.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.muydinovs.appjparelationships.dto.FacultyDto;
import uz.muydinovs.appjparelationships.entity.Faculty;
import uz.muydinovs.appjparelationships.entity.University;
import uz.muydinovs.appjparelationships.repository.FacultyRepository;
import uz.muydinovs.appjparelationships.repository.UniversityRepository;

import java.util.List;
import java.util.Optional;

@RestController("/faculty")
public class FacultyController {
    @Autowired
    FacultyRepository facultyRepository;

    @Autowired
    UniversityRepository universityRepository;


    @GetMapping
    public List<Faculty> getAllFaculties(){
        return facultyRepository.findAll();
    }

    @PostMapping
    public String add(@RequestBody FacultyDto facultyDto) {
        if (facultyRepository.existsByNameAndUniversityId(facultyDto.getName(),facultyDto.getUniversityId())) {
            return "This university is already exist";
        }
        Faculty faculty = new Faculty();
        faculty.setName(facultyDto.getName());
        Optional<University> optionalUniversity = universityRepository.findById(facultyDto.getUniversityId());

        if (!optionalUniversity.isPresent()) {
            return "University not found";
        }
        faculty.setUniversity(optionalUniversity.get());


        facultyRepository.save(faculty);
        return "Faculty saved";
    }


    @GetMapping("/byUniversityId/{id}")
    public List<Faculty> getFacultiesByUniversityId(@PathVariable Integer id){
        return facultyRepository.findAllByUniversityId(id);
    }
}

