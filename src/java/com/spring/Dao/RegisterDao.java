package com.spring.Dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.spring.Dao.BaseDao;
import com.spring.Model.College;
import com.spring.Model.Contact;
import com.spring.Model.Hospital;
import com.spring.Model.Speciality;
import com.spring.Model.Register;


@Repository
public class RegisterDao extends BaseDao  {
	
	
	
	public boolean add(Register register) {
		/*Map<String, Object> params = new HashMap<String, Object>();
		params.put("T", register);
		SqlSession sqlSession = sqlSessionFactory.openSession();
		sqlSession.insert("ApplicationRegister.InsertData", params);
		sqlSession.close();
		*/
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("T",register);
		Integer  status=0;
		Integer i=this.sqlSessionTemplate.selectOne("ApplicationRegister.checkStudent", map);
		
		if(i !=0){
			status=this.sqlSessionTemplate.update("ApplicationRegister.updateDetails", map);
			/*System.out.println("--------------------------------------");
			System.out.println(status);
			System.out.println("--------------------------------------");*/
			if(status !=0){
				return true;
			}else{
				return false;
			}
		}
		else{
			status=(Integer)this.sqlSessionTemplate.insert("ApplicationRegister.InsertData", map);
			if(status !=0){
				return true;
			}else{
				return false;
			}
		}
	}

	public List<Register> viewaccounts() {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		List<Register> RegisterDetails=sqlSession.selectList("ApplicationRegister.viewaccounts");
		sqlSession.close();
		return RegisterDetails;
	}

	public Register getDetails(String username)
	{
		SqlSession sqlSession = sqlSessionFactory.openSession();
		Register singleuserdetails=sqlSession.selectOne("ApplicationRegister.getDetails",username);
		sqlSession.close();
		return singleuserdetails;
		
	}
	
	public void updateDetails(Register register)
	{
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("T", register);
		SqlSession sqlSession = sqlSessionFactory.openSession();
		sqlSession.update("ApplicationRegister.updateDetails",params);
		sqlSession.close();
	}
	
	public Register getById(String name)
	{
		SqlSession sqlSession = sqlSessionFactory.openSession();
		Register registration=sqlSession.selectOne("ApplicationRegister.getById",name);
		sqlSession.close();
		return registration;
		
	}
	public void updateData(Register register)
	{
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("p", register);
		SqlSession sqlSession = sqlSessionFactory.openSession();
		sqlSession.update("ApplicationRegister.updateData",params);
		sqlSession.close();
	}
	public void changeData(Register register)
	{
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("p", register);
		SqlSession sqlSession = sqlSessionFactory.openSession();
		sqlSession.update("ApplicationRegister.changeData",params);
		sqlSession.close();
	}
	
	
	public void removeDetails(String username)
	{
		SqlSession sqlSession = sqlSessionFactory.openSession();
		sqlSession.delete("ApplicationRegister.removeDetails",username);
		sqlSession.close();
	}
	public Boolean login(Register register)
	{
		
		  Boolean status = Boolean.valueOf(false);
		  Map<String, Object> map = new HashMap();
		    map.put("p", register);
		    Integer RegisteredData = 0;
		    RegisteredData = (Integer)this.sqlSessionTemplate.selectOne("ApplicationRegister.CheckRegistertaion", map);
		    System.out.println("((((((((((((((((((((((((((((((((((((((((((");
		    System.out.println(RegisteredData);
		    
		    System.out.println("))))))))))))))))))))))))))))))))))))))))))");
		    
		  if (RegisteredData!=0) {
		    	
		    	   status = Boolean.valueOf(true);
		
		  }
		  else {
			   status = Boolean.valueOf(false);}
		  
		  return status;
		  }

	
	public Register getUserDetails(Register register)
	{
		SqlSession sqlSession = sqlSessionFactory.openSession();
		Register singleuserdetails=sqlSession.selectOne("ApplicationRegister.getUserDetails",register);
		sqlSession.close();
		return singleuserdetails;
		
	}
	
	public Register getPersonDetails(String email)
	{
		SqlSession sqlSession = sqlSessionFactory.openSession();
		Register singleuserdetails=sqlSession.selectOne("ApplicationRegister.getPersonDetails",email);
		sqlSession.close();
		return singleuserdetails;
	}
	
	public Register getProfile(Integer id)
	{
		SqlSession sqlSession = sqlSessionFactory.openSession();
		Register singleuserdetails=sqlSession.selectOne("ApplicationRegister.getProfile",id);
		sqlSession.close();
		return singleuserdetails;
		
	}
	
	public void changePassword(Register register)
	{
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("T", register);
		SqlSession sqlSession = sqlSessionFactory.openSession();
		sqlSession.update("ApplicationRegister.changePassword",params);
		sqlSession.close();
	}
	

	public Boolean verifyEmail(String email)
	{
		
		  Boolean status = Boolean.valueOf(false);
		    Integer isExists = 0;
		    isExists = (Integer)this.sqlSessionTemplate.selectOne("ApplicationRegister.verifyEmail", email);  
		  if (isExists!=0) {
		    	
		    	   status = Boolean.valueOf(true);
		
		  }
		  else {
			   status = Boolean.valueOf(false);
			   }
		  
		  return status;
     }
	public List<Map<String, Object>> getDistricts()
	  {
	    List<Map<String, Object>> list = this.sqlSessionTemplate.selectList("ApplicationRegister.getDistricts");
	    return list;
	  }
	public List<Map<String, Object>> getProblems()
	  {
	    List<Map<String, Object>> list = this.sqlSessionTemplate.selectList("ApplicationRegister.getProblems");
	    return list;
	  }
	  public List<College> getColleges(Integer districtId)
	  {
	    List<College> colleges = this.sqlSessionTemplate.selectList("ApplicationRegister.getColleges", districtId);
	    return colleges;
	  }
	  public List<Hospital> getHospitals(Integer collegeId)
	  {
	    List<Hospital> colleges = this.sqlSessionTemplate.selectList("ApplicationRegister.getHospitals", collegeId);
	    return colleges;
	  }
	  public List<Speciality> getSpeciality(String hospital)
	  {
	    List<Speciality> hospitals = this.sqlSessionTemplate.selectList("ApplicationRegister.getSpeciality", hospital);
	    return hospitals;
	  }
	  public List<Speciality> getData1(String problem)
	  {
	    List<Speciality> hospitals = this.sqlSessionTemplate.selectList("ApplicationRegister.getData1", problem);
	    return hospitals;
	  }
	  public List<Register> getData2(Register register)
	  {
	    List<Register> hospitals = this.sqlSessionTemplate.selectList("ApplicationRegister.getData2",register);
	    return hospitals;
	  }
	  
	  public boolean addReceptionist(Register register) {
			/*Map<String, Object> params = new HashMap<String, Object>();
			params.put("T", register);
			SqlSession sqlSession = sqlSessionFactory.openSession();
			sqlSession.insert("ApplicationRegister.InsertData", params);
			sqlSession.close();
			*/
			
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("T",register);
			Integer  status=0;
			Integer i=this.sqlSessionTemplate.selectOne("ApplicationRegister.checkStudent", map);
			
			if(i !=0){
				status=this.sqlSessionTemplate.update("ApplicationRegister.updateDetails", map);
				/*System.out.println("--------------------------------------");
				System.out.println(status);
				System.out.println("--------------------------------------");*/
				if(status !=0){
					return true;
				}else{
					return false;
				}
			}
			else{
				status=(Integer)this.sqlSessionTemplate.insert("ApplicationRegister.InsertData", map);
				if(status !=0){
					return true;
				}else{
					return false;
				}
			}
		}
	  public void insertHospitals(Hospital hospital) {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("T", hospital);
			SqlSession sqlSession = sqlSessionFactory.openSession();
			sqlSession.insert("ApplicationRegister.insertHospitals", params);
			sqlSession.close();
		}
	  public List<Register> getHospitalDetails() {
			SqlSession sqlSession = sqlSessionFactory.openSession();
			List<Register> Register=sqlSession.selectList("ApplicationRegister.getHospitalDetails");
			sqlSession.close();
			return Register;
		}
	  public void addHospitalDetails(Register hospital) {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("T", hospital);
			SqlSession sqlSession = sqlSessionFactory.openSession();
			sqlSession.insert("ApplicationRegister.addHospitalDetails", params);
			sqlSession.close();
		}
	  public boolean addappointment(Register register) {
			/*Map<String, Object> params = new HashMap<String, Object>();
			params.put("T", register);
			SqlSession sqlSession = sqlSessionFactory.openSession();
			sqlSession.insert("ApplicationRegister.InsertData", params);
			sqlSession.close();
			*/
			
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("T",register);
			Integer  status=0;
			Integer i=this.sqlSessionTemplate.selectOne("ApplicationRegister.checkappointments", map);
			
			if(i >10){
				return false;
				
			}
			else{
				
				
				status=this.sqlSessionTemplate.update("ApplicationRegister.addappointment", map);
				/*System.out.println("--------------------------------------");
				System.out.println(status);
				System.out.println("--------------------------------------");*/
				if(status !=0){
					return true;
				}else{
					return false;
				}
				
			}
		}
	  public List<Register> getDoctorAppointment(Register register)
	  {
	    List<Register> hospitals = this.sqlSessionTemplate.selectList("ApplicationRegister.getDoctorAppointment",register);
	    return hospitals;
	  }
	  public Boolean setDoctorsAppointment(Register register) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("T", register);
			Integer  status=0;
			status=(Integer)this.sqlSessionTemplate.update("ApplicationRegister.setDoctorsAppointment", map);
			if(status !=0){
				return true;
			}else{
				return false;
			}
		}
	  public void addTreatmentData(Speciality hospital) {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("T", hospital);
			SqlSession sqlSession = sqlSessionFactory.openSession();
			sqlSession.insert("ApplicationRegister.addTreatmentData", params);
			sqlSession.close();
		}
	  public List<Register> getAppointments(Register register)
	  {
	    List<Register> hospitals = this.sqlSessionTemplate.selectList("ApplicationRegister.getAppointments",register);
	    return hospitals;
	  }
	  public void delAppointment(Register hospital) {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("T", hospital);
			SqlSession sqlSession = sqlSessionFactory.openSession();
			sqlSession.delete("ApplicationRegister.delAppointment", params);
			sqlSession.close();
		}
}
