package com.laining.orm.service;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.laining.orm.model.db1.Book;
import com.laining.orm.model.db1.Person;
import com.laining.orm.model.db2.Student;
import com.laining.orm.model.db2.Teacher;
import com.laining.orm.repository.db1.BookRepository;
import com.laining.orm.repository.db1.PersonRepository;
import com.laining.orm.repository.db2.StudentRepository;
import com.laining.orm.repository.db2.TeacherRepository;

@Service("myService")
public class MyServiceImpl implements MyService {

	private Logger LOGGER = LoggerFactory.getLogger(MyServiceImpl.class);

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private TeacherRepository teacherRepository;

	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private PersonRepository personRepository;

	@Override
	@Transactional()
	public void saveStudent() {
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

	@Override
	@Transactional()
	public void saveBook() {
		Person person = new Person("赖宁");
		Book book = new Book("Java从入门到放弃");
		bookRepository.save(book);
		personRepository.save(person);

	}

	@Override
	public Book findBook() {
		Book book = bookRepository.findOne(1L);
		if (book != null)
			LOGGER.debug("book {}", book);
		return book;
	}

	@Override
	@Transactional
	public void updateBook(String name) {
		Book book = bookRepository.findOne(1L);
		if (book != null) {
			book.setName(name);
			book.setUpdateOn(new Date());
			bookRepository.save(book);
		}

	}

	@Override
	public Student findStudent() {
		Student existedS1 = studentRepository.findOne(5L);
		if (existedS1 != null) {
			LOGGER.debug("Student name {},teacher size {}", existedS1.getName(), existedS1.getTeachers().size());
		}
		return existedS1;
	}

}
