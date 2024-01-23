package uz.muydinovs.appjparelationships.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.muydinovs.appjparelationships.entity.Faculty;

import java.util.List;

@Repository
public interface FacultyRepository extends JpaRepository<Faculty,Integer> {
    boolean existsByNameAndUniversityId(String name, Integer universityId);

    List<Faculty> findAllByUniversityId(Integer id);
}
