package com.laining.orm.model.db1;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.laining.orm.model.BaseEntity;

@Entity
@Table(name = "Book")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL, region = "Book") // READ_ONLY
																		// 只读，如果更新会有异常，READ_WRITE
																		// 读写模式在更新缓存的时候会把缓存里面的数据换成一个锁，其它事务如果去取相应的缓存数据，发现被锁了，直接就去数据库查询
																		// NONSTRICT_READ_WRITE
																		// 不严格的读写模式则不会的缓存数据加锁
																		// TRANSACTIONAL事务模式指缓存支持事务，当事务回滚时，缓存也能回滚，只支持
																		// JTA
																		// 环境
public class Book extends BaseEntity {

	private String name;

	public Book() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Book(String name) {
		super();
		this.name = name;
	}

	@Column(name = "name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Book [name=" + name + "]";
	}

}
