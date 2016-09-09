package com.demo;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.apache.ibatis.exceptions.IbatisException;
import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import com.demo.model.Student;
import com.demo.model.Users;
import com.demo.util.MybatisUtil;

public class UsersTest {
	/**
	 * 简单的查询案例
	 */
	@Test
	public void testSelectOne() {
		String resource = "conf.xml";
		InputStream inputStream = Test.class.getClassLoader()
				.getResourceAsStream(resource);
		// 加载 mybatis 的配置文件（它也加载关联的映射文件）
		/* Reader reader = Resources.getresourc(inputStream); */
		// 构建 sqlSession 的工厂
		SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder()
				.build(inputStream);
		// 创建能执行映射文件中 sql 的 sqlSession
		SqlSession session = sessionFactory.openSession();
		// 映射 sql 的标识字符串
		String statement = "com.demo.model.UsersMapper.selectById";
		// 执行查询返回一个唯一 user 对象的 sql
		Users user = session.selectOne(statement, 1);
		System.out.println(user.getName());
		Users user2 = session.selectOne(statement, 1);
		System.out.println(user2.getName());
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Users users = new Users();
		users.setId(2);
		users.setAge(new Random().nextInt(100));
		users.setName("礼乐");
		String statement1 = "com.demo.model.UsersMapper.updateById";
		int i = session.update(statement1, users);
		System.out.println("UsersTest.testInsert()=>" + i);

		session.commit();
		Users user1 = session.selectOne(statement, 1);
		System.out.println(user1.getName());
		session.close();
	}
	/**
	 * 嵌套查询
	 */
	@Test
   public void testSelectLink(){
		try {
		SqlSession session = MybatisUtil.getFactory().openSession();
		Student student=session.selectOne("com.demo.model.StudentMapper.selectById", 1);
		System.out.println(student.getS_name()+"---"+student.getTeacher().getT_name());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}	
			
   }
	@Test
	public void testInsert() {
		try {

			SqlSession session = MybatisUtil.getFactory().openSession();
			Users users = new Users();
			users.setId(new Random().nextInt(100));
			users.setAge(new Random().nextInt(100));
			users.setName("星魂");
			String statement = "com.demo.model.UsersMapper.insert";
			int i = session.insert(statement, users);
			System.out.println("UsersTest.testInsert()=>" + i);

			session.commit();
		} catch (PersistenceException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	@Test
	public void testUpdate() {
		try {

			SqlSession session = MybatisUtil.getFactory().openSession();
			Users users = new Users();
			users.setId(1);
			users.setAge(new Random().nextInt(100));
			users.setName("顾天涯");
			String statement = "com.demo.model.UsersMapper.updateById";
			int i = session.update(statement, users);
			System.out.println("UsersTest.testInsert()=>" + i);

			session.commit();
		} catch (PersistenceException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	/**
	 * 调用存储过程
	 */
	@Test
	public void testProcedure1(){
		try {
			SqlSession session = MybatisUtil.getFactory().openSession();
			String statement="com.demo.model.UsersMapper.testProcedure1";
			Map<String, Integer> map=new HashMap<String, Integer>();
			map.put("mixid", 1);
			map.put("usercoount", 0);
			Integer nameString=session.selectOne(statement, map);
			System.out.println("testProcedure1()==>"+nameString+"------"+map.get("usercoount"));
			session.close();
		} catch (PersistenceException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	 
	}
  @Test
  public void testTwoCache(){
	  try {
		
	
		String statement = "com.demo.model.UsersMapper.selectById";
		SqlSessionFactory factory=MybatisUtil.getFactory();
		SqlSession session1 = factory.openSession();
		SqlSession session2 =factory.openSession();
		Users user = session1.selectOne(statement, 1);
		session1.commit();
/*		Users users = new Users();
		users.setId(2);
		users.setAge(new Random().nextInt(100));
		users.setName("礼乐");
		String statement1 = "com.demo.model.UsersMapper.updateById";
		int i = session1.update(statement1, users);
		session1.commit();
		System.out.println(user.getName());*/
		testUpdate();
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Users user2 = session2.selectOne(statement, 1);
		System.out.println(user2.getName());
		session2.commit();
	  } catch (Exception e) {
			// TODO: handle exception
		  e.printStackTrace();
		}
  }
}
