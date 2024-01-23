package uz.muydinovs.appjparelationships.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.muydinovs.appjparelationships.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student,Integer> {
        Page<Student> findAllByGroup_Faculty_University_Id(Integer id, Pageable pageable);
}
