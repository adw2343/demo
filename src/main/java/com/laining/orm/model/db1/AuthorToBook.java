package com.laining.orm.model.db1;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.laining.orm.model.BaseEntity;

@Entity
@Table(name = "author_to_book")
public class AuthorToBook extends BaseEntity {

	private Book book;
	private Person author;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "book")
	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	@ManyToOne()
	@JoinColumn(name = "author")
	public Person getAuthor() {
		return author;
	}

	public void setAuthor(Person author) {
		this.author = author;
	}

}
