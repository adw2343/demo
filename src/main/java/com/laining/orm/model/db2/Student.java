package com.laining.orm.model.db2;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.laining.orm.model.BaseEntity;

@Entity
@Table(name = "Student")
@Cacheable // 使用二级缓存
@Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL, region = "Student")
public class Student extends BaseEntity {

	private String name;

	private Set<Teacher> teachers = new HashSet<>();

	@ManyToMany(targetEntity = Teacher.class, fetch = FetchType.EAGER) // 设置为及时获取是因为如果是懒加载，需要在view层开启session,为了测试方便，直接设置成及时抓取
	@JoinTable(name = "student_to_teacher", joinColumns = @JoinColumn(name = "student"), inverseJoinColumns = @JoinColumn(name = "teacher")) // joincolumns该表的主键对应在中间表的外键
	@Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL, region = "Student_To_Teacher") // 启用集合缓存，会将关联的中间表数据也缓存到二级缓存
	public Set<Teacher> getTeachers() {
		return teachers;
	}

	public void setTeachers(Set<Teacher> teachers) {
		this.teachers = teachers;
	}

	@Column(name = "name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

}
