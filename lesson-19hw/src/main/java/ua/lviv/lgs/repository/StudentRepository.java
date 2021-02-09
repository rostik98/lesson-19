package ua.lviv.lgs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import ua.lviv.lgs.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {

	@Transactional
	@Modifying
	@Query("delete from Student e where e.id like ?1 and fileName like ?2")
	public void deleteStudentWithFile(Long id, String fileName);
}
