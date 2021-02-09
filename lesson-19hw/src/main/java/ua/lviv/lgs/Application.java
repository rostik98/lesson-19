package ua.lviv.lgs;

import java.io.File;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import ua.lviv.lgs.controller.StudentController;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		new File(StudentController.uploadDirectory).mkdir();
		SpringApplication.run(Application.class, args);
	}

}
