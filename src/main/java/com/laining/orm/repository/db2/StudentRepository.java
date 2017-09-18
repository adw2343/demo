package com.laining.orm.repository.db2;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.laining.orm.model.db2.Student;

public interface StudentRepository extends PagingAndSortingRepository<Student, Long> {

}
