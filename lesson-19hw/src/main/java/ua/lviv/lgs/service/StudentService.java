package ua.lviv.lgs.service;

import java.io.IOException;
import java.util.List;

import ua.lviv.lgs.entity.Student;

public interface StudentService {
 
	public boolean saveStudent(Student student) throws IOException;
	
	public List<Student> getAllStudents();
	
	public boolean deleteFile(Long id, String file);
}
