package com.laining.orm.test.repository;

import javax.transaction.Transactional;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.laining.orm.model.db1.Book;
import com.laining.orm.model.db1.Person;
import com.laining.orm.repository.db1.BookRepository;
import com.laining.orm.repository.db1.PersonRepository;
import com.laining.orm.test.DemoApplicationTests;

public class DB1RepositoryTest extends DemoApplicationTests {

	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private PersonRepository personRepository;

	@Test
	@Transactional
	public void testdb1() {
		Person person = new Person("赖宁");
		Book book = new Book("Java从入门到放弃");
		bookRepository.save(book);
		personRepository.save(person);

	}

}
