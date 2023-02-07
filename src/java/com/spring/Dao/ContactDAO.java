
package com.spring.Dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.Model.Contact;

@Repository
public class ContactDAO extends BaseDao {
@Autowired
private SqlSessionFactory sqlSessionFactory;

public void insertFeedback(Contact Contact) {
	Map<String, Object> params = new HashMap<String, Object>();
	params.put("T", Contact);
	SqlSession sqlSession = sqlSessionFactory.openSession();
	sqlSession.insert("Contact.insertFeedback", params);
	sqlSession.close();
}


 public List<Map<String, Object>> viewFeedbacks()
  {
    List<Map<String, Object>> list = this.sqlSessionTemplate.selectList("Contact.viewFeedbacks");
    return list;
  }


public void deleteFeedback(Contact Contact)
{
	Map<String, Object> params = new HashMap<String, Object>();
	params.put("T", Contact);
	SqlSession sqlSession = sqlSessionFactory.openSession();
	sqlSession.update("Contact.deleteFeedback",params);
	sqlSession.close();
}


public void sendReply(Contact Contact) {
	Map<String, Object> params = new HashMap<String, Object>();
	params.put("T", Contact);
	SqlSession sqlSession = sqlSessionFactory.openSession();
	sqlSession.update("Contact.sendReply", params);
	sqlSession.close();
}



}