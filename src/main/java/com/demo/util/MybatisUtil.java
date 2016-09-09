package com.demo.util;

import java.io.InputStream;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

/**
 * 
 * @author 高鹤
 *
 * 2016年9月8日
 *
 * 作用：mybatis的工具类
 */
public class MybatisUtil {
	/**
	 * 加载配置文件,获取session工厂实例
	 */
	public static SqlSessionFactory getFactory() {
		String resource = "conf.xml";
		InputStream inputStream = Test.class.getClassLoader().getResourceAsStream(resource);
		// 加载 mybatis 的配置文件（它也加载关联的映射文件）
		/* Reader reader = Resources.getresourc(inputStream); */
		// 构建 sqlSession 的工厂
		SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		return sessionFactory;
	}

}
