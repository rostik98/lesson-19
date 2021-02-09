package ua.lviv.lgs.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ua.lviv.lgs.entity.Student;
import ua.lviv.lgs.repository.StudentRepository;

@Service
@Transactional
public class StudentServiceImpl implements StudentService {

	@Autowired
	StudentRepository studentRepository;

	public static String uploadDirectory = System.getProperty("user.dir") + "/uploads";

	@Override
	public boolean saveStudent(Student student) throws IOException {
		try {
			if (student != null) {
				studentRepository.save(student);
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return false;

	}

	@Override
	public List<Student> getAllStudents() {
		return studentRepository.findAll();
	}

	@Override
	public boolean deleteFile(Long id, String file) {
		boolean status = false;
		try {
			if (id != 0 && file != null) {
				studentRepository.deleteStudentWithFile(id, file);
				System.out.println(this.getClass().getSimpleName() + ":deleting Student... " + id);
				String path = uploadDirectory + "/" + file;
				System.out.println("Path=" + path);
				File fileToDelete = new File(path);
				status = fileToDelete.delete();
				System.out.println(this.getClass().getSimpleName() + ":deleting file... " + file);
				System.out.println("Success: " + status + " fileToDelete: " + fileToDelete);
				return status;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return status;
		}
		return status;
	}

}
