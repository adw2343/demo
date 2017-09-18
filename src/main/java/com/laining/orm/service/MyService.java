package com.laining.orm.service;

import com.laining.orm.model.db1.Book;
import com.laining.orm.model.db2.Student;

public interface MyService {

	void saveStudent();

	void saveBook();

	Book findBook();

	void updateBook(String name);

	Student findStudent();

}
