package com.laining.orm.test.repository;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.laining.orm.model.db2.Student;
import com.laining.orm.model.db2.Teacher;
import com.laining.orm.repository.db2.StudentRepository;
import com.laining.orm.repository.db2.TeacherRepository;
import com.laining.orm.test.DemoApplicationTests;

public class DB2RepositoryTest extends DemoApplicationTests {

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private TeacherRepository teacherRepository;

	@Test
	@Transactional
	public void test() {

		Student existedS1 = studentRepository.findOne(5L);
		Student existedS2 = studentRepository.findOne(6L);

		Teacher existedT1 = teacherRepository.findOne(1L);
		Teacher existedT2 = teacherRepository.findOne(2L);

		existedS1.getTeachers().add(existedT1);
		existedS2.getTeachers().add(existedT2);

		existedT1.getStudents().add(existedS1);
		existedT2.getStudents().add(existedS2);

		studentRepository.save(existedS1);
		studentRepository.save(existedS2);

		teacherRepository.save(existedT1);
		teacherRepository.save(existedT2);

	}

}
