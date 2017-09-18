package com.laining.orm.service;

import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component("scheduledTask")
public class ScheduledTask {

	private static final Logger LOGGER = LoggerFactory.getLogger(ScheduledTask.class);
	private AtomicInteger Version = new AtomicInteger(1);

	@Autowired
	private MyService myService;

	@Scheduled(fixedRate = 1000 * 60)
	public void cleanImportFile() {
		LOGGER.debug("scheduled task");
		myService.saveBook();
		myService.updateBook("Java从入门到放弃" + Version.getAndIncrement());
		myService.findBook(); // 就算启用二级缓存，由于在查询之前执行了更新操作，仍会向数据库发出查询语句

		myService.saveStudent();
		myService.findStudent(); // 启用了二级缓存和集合缓存，多次查询且中间没有进行更新操作，不会向数据库发出查询语句.如果未启用集合缓存，仍会向数据库发出查询中间表的查询语句
	}

}
