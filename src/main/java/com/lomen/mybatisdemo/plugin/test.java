package com.lomen.mybatisdemo.plugin;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;
import java.util.Date;

public class test {
    /*private static SqlSessionFactory factory;

    public static void main(String[] args) {
        TestEntity entity=new TestEntity();
        entity.setId("abcd");
        entity.setName("test");
        entity.setCreateTime(new Date());

        int num =0;
        SqlSession sqlSession = getSqlSessionFactory().openSession();
        try {
            TestEntityMapper testDao = sqlSession.getMapper(TestEntityMapper.class);
            num=testDao.insert(entity);
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }


        System.out.println("受影响的行: "+num);
    }
    public static SqlSessionFactory getSqlSessionFactory() {

        Reader reader = null;
        try {
            reader = Resources.getResourceAsReader("./mybatis/mybatis-config.xml");
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
        factory = new SqlSessionFactoryBuilder().build(reader);
        return factory;
    }
*/
}
