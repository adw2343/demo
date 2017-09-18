package com.laining.orm.repository.db1;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.laining.orm.model.db1.Person;

public interface PersonRepository extends PagingAndSortingRepository<Person, Long> {

}
