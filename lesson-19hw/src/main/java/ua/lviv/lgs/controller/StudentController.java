package ua.lviv.lgs.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import ua.lviv.lgs.entity.Student;
import ua.lviv.lgs.exception.MyFileNotFoundException;
import ua.lviv.lgs.service.StudentService;

@Controller
@RequestMapping("/image-upload")
public class StudentController {

	private static Logger log = LoggerFactory.getLogger(StudentController.class);
	public static String uploadDirectory = System.getProperty("user.dir") + "/uploads";

	@Autowired
	StudentService studentService;

	@GetMapping(value = { "/", "/index", "/home", "/default" })
	public String homePage() {
		return "index";
	}

	@GetMapping("/sign-up")
	public String showSignupPage() {
		return "sign-up";
	}

	@PostMapping("/saveStudent")
	public @ResponseBody ResponseEntity<?> createStudent(@Valid Student student,
			@RequestParam("name") final String name, @RequestParam("age") final String age,
			final @RequestParam("file") MultipartFile file) {
		try {
			HttpHeaders headers = new HttpHeaders();
			if (student == null) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
			String[] agee = age.split(",");
			String fileName = file.getOriginalFilename();
			String filePath = Paths.get(uploadDirectory, fileName).toString();
			String fileType = file.getContentType();
			long size = file.getSize();
			String fileSize = String.valueOf(size);
			Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());

			log.info("Name: " + name);
			log.info("Age: " + agee[0]);
			log.info("FileName: " + file.getOriginalFilename());
			log.info("FileType: " + file.getContentType());
			log.info("FileSize: " + file.getSize());

			BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(filePath)));
			stream.write(file.getBytes());
			stream.close();

			student.setName(name);
			student.setAge(agee[0]);
			student.setFileName(fileName);
			student.setFilePath(filePath);
			student.setFileType(fileType);
			student.setFileSize(fileSize);
			student.setCreatedDate(currentTimestamp);

			boolean status = studentService.saveStudent(student);
			if (status) {
				log.info("Student Created");
				headers.add("Student Saved With Image - ", fileName);
				return new ResponseEntity<>("Student Saved With File - " + fileName, headers, HttpStatus.OK);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception: " + e);
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	@GetMapping("/students")
	public ModelAndView showAllStudents() {
		log.info("Showing Student list with File.");
		ModelAndView mav = new ModelAndView("students");
		List<Student> studentsList = studentService.getAllStudents();
		mav.addObject("studentsList", studentsList);
		return mav;
	}

	@GetMapping("/removeFile/{id}/{deletedFileName}")
	public ModelAndView removeFileHandler(@PathVariable("id") Long id,
			@PathVariable("deletedFileName") String deletedFileName) {
		ModelAndView mav = new ModelAndView("redirect:/image-upload/students");
		String path = null;
		File file = null;
		try {
			path = uploadDirectory + "/" + deletedFileName;
			file = new File(path);
			if (file.exists()) {
				log.info("File Exits: ID: " + id + " File: " + deletedFileName);
				log.info("Deleteing Student with File.");
				boolean status = studentService.deleteFile(id, deletedFileName);
				log.info("Status: " + status);
				if (status) {
					log.info("Student Deleted with file Status: " + status);
					List<Student> studentsList = studentService.getAllStudents();
					mav.addObject("studentsList", studentsList);
					return mav;
				} else {
					log.info("Oops! Something Went Wrong Status: " + status);
					List<Student> studentsList = studentService.getAllStudents();
					mav.addObject("studentsList", studentsList);
					return mav;
				}
			} else {
				log.info("Oops! File Not Found: " + deletedFileName);
				throw new MyFileNotFoundException("Oops! File Not Found: " + deletedFileName);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.info(e.getMessage());
			throw new MyFileNotFoundException("Oops! File Not Found: " + deletedFileName, e);
		}
	}
}
