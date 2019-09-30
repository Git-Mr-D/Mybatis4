package com.java.impl;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Emp {
    SqlSession sqlSession=null;

    //最先执行
    @Before
    public void insert() throws Exception{
        SqlSessionFactoryBuilder sql=new SqlSessionFactoryBuilder();
        //将ybatis文件变成字节输入流
        InputStream resourceAsStream = Resources.getResourceAsStream("Mybatis.xml");
        SqlSessionFactory build = sql.build(resourceAsStream);
         sqlSession = build.openSession();

    }


    //查询所有
    @Test
    public void selectAll(){
        List<Map<String,Object>> mapList = sqlSession.selectList("com.java.impl.Emp.selectAll");
        mapList.forEach(temp->System.out.println(temp));
    }

    @Test
    //带条件查询
    public void getById(){
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("ename","赵六");
        map.put("job","经理");
        Map<String,Object> oneByid = sqlSession.selectOne("com.java.impl.Emp.getById",map);
        oneByid.forEach((k,v)->System.out.println(k+"==="+v));
    }

    @Test
    //添加
    public void getinsert(){
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("empno","1019");
        map.put("ename","赵六");
        map.put("job","经理");
        map.put("mgr","1009");
        map.put("hiredate","2000-11-01");
        map.put("sal","666");
        map.put("COMM","66");
        map.put("deptno","20");
        int insert = sqlSession.insert("com.java.impl.Emp.getinsert", map);
        System.out.println(insert);
    }

    @Test
    //根据主键删除
    public void getdelect(){
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("empno","1019");
        int delete = sqlSession.delete("com.java.impl.Emp.getdelect", map);
        System.out.println(delete);
    }

    @Test
    //模糊查询
    public void selectAlll(){
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("ename","赵六");
        List<Map<String,Object>> mapList = sqlSession.selectList("com.java.impl.Emp.selectAlll", map);
        mapList.forEach(temp->System.out.println(temp));
    }

    @Test
    //动态修改
    public void getupdate(){
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("ename","刘备");
        map.put("empno","1004");
        int update = sqlSession.update("com.java.impl.Emp.getupdate", map);
          System.out.println(update);
    }

    //提交事务
    @After
    public void select(){
        sqlSession.commit();
    }
}
