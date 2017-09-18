package com.laining.orm.repository.db1;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.laining.orm.model.db1.Book;

public interface BookRepository extends PagingAndSortingRepository<Book, Long> {

}
