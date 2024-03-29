package uz.muydinovs.appjparelationships.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.muydinovs.appjparelationships.dto.UniversityDto;
import uz.muydinovs.appjparelationships.entity.Address;
import uz.muydinovs.appjparelationships.entity.University;
import uz.muydinovs.appjparelationships.repository.AddressRepository;
import uz.muydinovs.appjparelationships.repository.UniversityRepository;

import java.util.List;
import java.util.Optional;

@RestController
public class UniversityController {

    @Autowired
    UniversityRepository universityRepository;

    @Autowired
    AddressRepository addressRepository;

    @RequestMapping(value = "/university", method = RequestMethod.GET)
    public List<University> getUniversities() {
        return universityRepository.findAll();
    }

    @RequestMapping(value = "/university", method = RequestMethod.POST)
    public String addUniversity(@RequestBody UniversityDto universityDto) {
        Address address = new Address();
        address.setCity(universityDto.getCity());
        address.setDistrict(universityDto.getDistrict());
        address.setStreet(universityDto.getStreet());
        Address savedAddress = addressRepository.save(address);
        University university = new University();
        university.setAddress(savedAddress);
        university.setName(universityDto.getName());
        universityRepository.save(university);
        return "University saved";
    }


    @PutMapping("university/{id}")
    public String editUniversity(@PathVariable Integer id, @RequestBody UniversityDto universityDto) {
        Optional<University> optionalUniversity = universityRepository.findById(id);
        if (optionalUniversity.isPresent()) {
            University university = optionalUniversity.get();
            university.setName(universityDto.getName());
            Address address = university.getAddress();
            address.setCity(universityDto.getCity());
            address.setDistrict(universityDto.getDistrict());
            address.setStreet(universityDto.getStreet());
            addressRepository.save(address);
            universityRepository.save(university);
        }
        return "University edited";
    }

    @RequestMapping(value = "/university/{id}", method = RequestMethod.DELETE)
    public String deleteUniversity(@PathVariable Integer id){
        universityRepository.deleteById(id);
        return "University deleted";
    }
}
