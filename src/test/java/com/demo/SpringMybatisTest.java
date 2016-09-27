package com.demo;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.demo.dao.UsersMapper;
import com.demo.model.Users;
@RunWith(SpringJUnit4ClassRunner.class)//使用spring的测试框架
@ContextConfiguration("/applicationContext1.xml")
public class SpringMybatisTest {
	ApplicationContext applicationContext;
	@Autowired
	UsersMapper usersMapper;
/*    @Before
    public void init(){
    	try {
    		applicationContext=new ClassPathXmlApplicationContext("applicationContext.xml");
        	 
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
    	
    }
    @Test
    public void testSpringMybatis(){
    	SqlSessionFactory sqlSessionFactory=(SqlSessionFactory) applicationContext.getBean("sqlSessionFactory");
    	SqlSession sqlSession=sqlSessionFactory.openSession();
     	String statement = "com.demo.dao.UsersMapper.selectById";
		// 执行查询返回一个唯一 user 对象的 sql
		Users user = sqlSession.selectOne(statement, 1);
		System.out.println(user.getName());
    }
 */
@Test
public void testSpringMybatis1(){
	try {
		System.err.println(usersMapper==null);
	
/*	applicationContext=new ClassPathXmlApplicationContext("applicationContext1.xml");*/
	Users user =usersMapper.selectById(1);
	System.out.println(user.getName());
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
	}
}
}
