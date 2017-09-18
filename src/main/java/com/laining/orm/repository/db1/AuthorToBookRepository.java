package com.laining.orm.repository.db1;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.laining.orm.model.db1.AuthorToBook;

public interface AuthorToBookRepository extends PagingAndSortingRepository<AuthorToBook, Long> {

}
