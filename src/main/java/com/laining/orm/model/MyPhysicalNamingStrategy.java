package com.laining.orm.model;

import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;
import org.springframework.boot.orm.jpa.hibernate.SpringPhysicalNamingStrategy;

/**
 * spring默认的PhysicalNamingStrategy会对字段名和表名做处理，这里希望不做处理 该策略需要在配置文件中配置
 * 
 * @author laining
 *
 */
public class MyPhysicalNamingStrategy extends SpringPhysicalNamingStrategy {

	@Override
	public Identifier toPhysicalTableName(Identifier name, JdbcEnvironment jdbcEnvironment) {
		Identifier fullTableName = new Identifier(name.getText(), name.isQuoted());
		return super.toPhysicalTableName(fullTableName, jdbcEnvironment);
	}

	@Override
	public Identifier toPhysicalColumnName(Identifier name, JdbcEnvironment jdbcEnvironment) {
		return super.getIdentifier(name.getText(), name.isQuoted(), jdbcEnvironment);
	}

}
