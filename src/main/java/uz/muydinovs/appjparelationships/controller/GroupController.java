package uz.muydinovs.appjparelationships.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.muydinovs.appjparelationships.dto.FacultyDto;
import uz.muydinovs.appjparelationships.dto.GroupDto;
import uz.muydinovs.appjparelationships.entity.Faculty;
import uz.muydinovs.appjparelationships.entity.Group;
import uz.muydinovs.appjparelationships.entity.University;
import uz.muydinovs.appjparelationships.repository.FacultyRepository;
import uz.muydinovs.appjparelationships.repository.GroupRepository;
import uz.muydinovs.appjparelationships.repository.UniversityRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/group")
public class GroupController {

    @Autowired
    GroupRepository groupRepository;

    @Autowired
    FacultyRepository facultyRepository;

    @Autowired
    UniversityRepository universityRepository;

    @GetMapping
    public List<Group> getGroups() {
        return groupRepository.findAll();
    }

    @GetMapping("/byUniversityId/{universityId}")
    public List<Group> getGroupsByUniversityId(@PathVariable Integer universityId) {
        return groupRepository.findAllByFaculty_University_Id(universityId);
    }

    @PostMapping
    public String addGroup(@RequestBody GroupDto groupDto) {
        Group group = new Group();
        group.setName(group.getName());
        Optional<Faculty> optionalFaculty = facultyRepository.findById(groupDto.getFacultyId());

        if (!optionalFaculty.isPresent())
            return "Such faculty not found";

        group.setFaculty(optionalFaculty.get());
        groupRepository.save(group);
        return "Group saved";
    }

    @DeleteMapping("/{id}")
    public String deleteFaculty(@PathVariable Integer id) {
        try {
            facultyRepository.deleteById(id);
            return "Faculty deleted";
        } catch (Exception e) {
            return "Error while deleting";
        }
    }

    @PutMapping("/{id}")
    public String editFaculty(@PathVariable Integer id, @RequestBody FacultyDto facultyDto){
        Optional<Faculty> optionalFaculty = facultyRepository.findById(id);
        if (optionalFaculty.isPresent()) {
            Faculty faculty = optionalFaculty.get();
            faculty.setName(faculty.getName());
            Optional<University> optionalUniversity = universityRepository.findById(facultyDto.getUniversityId());
            if (!optionalUniversity.isPresent()) {
                return "University not found";
            }
            faculty.setUniversity(optionalUniversity.get());
            facultyRepository.save(faculty);
            return "faculty edited";
        }
        return "Faculty not found";
    }
}
