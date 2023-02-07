package com.spring.Service;

import java.util.*;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.andromeda.commons.model.Response;
import com.spring.Dao.RegisterDao;
import com.spring.Model.College;
import com.spring.Model.Hospital;
import com.spring.Model.Speciality;
import com.spring.Model.Email;
import com.spring.Model.Register;

import com.spring.Service.EmailService;

@Service
public class RegisterService {
	
	Response response = new Response();
	@Autowired
	EmailService emailService;
	@Autowired
	private RegisterDao registerDAO;
	
	public Response add(Register register) throws JSONException {
		this.response.setSuccessful(false);
		boolean status = registerDAO.add(register);
		if (status) {
			Email email = new Email();
			email.setFrom("MetaAid Team <metaaid.team@gmail.com>");
			email.setTo(register.getEmail().trim());
			email.setSubject("MetaAid - Registration Sucessfull!!.");
			String msg = "Dear Participant,<br><br>Thank you for Registering  to MetaAid"; 

			email.setText(msg);

			this.emailService.sendHtmlMsg(email); 

			this.response.setSuccessful(true);      
		} else {
			this.response.setSuccessful(false);
			this.response.setErrorMessage("Link expired!");
		}
		return this.response;
	}

	public Response viewaccounts() {
		response.setSuccessful(false);
		List<Register> RegisterDetails = registerDAO.viewaccounts();
		response.setSuccessful(true);
		response.setResponseObject(RegisterDetails);
		return response;
	}
	
	public Response getDetails(String username)
	{
		response.setSuccessful(false);
		Register singleuserdetails = registerDAO.getDetails(username);
		response.setSuccessful(true);
		response.setResponseObject(singleuserdetails);
		return response;
	}
	
	public Response updateDetails(Register register)
	{
		response.setSuccessful(false);
		registerDAO.updateDetails(register);
		response.setSuccessful(true);
		response.setResponseObject(register);
		return response;
	}
	
	public Response getById(String name)
	{
		response.setSuccessful(false);
		Register singleuserdetails = registerDAO.getById(name);
		response.setSuccessful(true);
		response.setResponseObject(singleuserdetails);
		return response;
	}
	public Response updateData(Register register)
	{
		response.setSuccessful(false);
		registerDAO.updateData(register);
		response.setSuccessful(true);
		response.setResponseObject(register);
		return response;
	}
	public Response changeData(Register register)
	{
		response.setSuccessful(false);
		registerDAO.changeData(register);
		response.setSuccessful(true);
		response.setResponseObject(register);
		return response;
	}
	
	public Response removeDetails(String username)
	{
		response.setSuccessful(false);
		registerDAO.removeDetails(username);
		response.setSuccessful(true);
		response.setResponseObject(username);
		return response;
	}
	public Response login(Register register) {
		response.setSuccessful(false);
		boolean result = registerDAO.login(register);
		System.out.println("++++++++++++++++++++++++++++++++++++++");
		System.out.println(result);
		System.out.println("_______________________________________");
		if (result) {
			response.setSuccessful(true);
			Register data = registerDAO.getUserDetails(register);
			
			response.setResponseObject(data);
		
		} else {
			response.setErrorMessage("Invalid credentials");
		}

		return response;
	}
	public Response verifyEmail(String email)
	{
		response.setSuccessful(false);
		boolean status=registerDAO.verifyEmail(email);
		if(status)
		{
			response.setSuccessful(true);
			Register data=registerDAO.getPersonDetails(email);
			response.setResponseObject(data);
			response.setErrorMessage(null);
		}else {
			response.setSuccessful(false);
			response.setErrorMessage("Invalid credentials...!");
		}
		return response;
	}


	public Response forgotpassword(Register register) throws JSONException {
		response.setSuccessful(false);
		response.setSuccessful(true);
		response.setResponseObject(register);
		if(response.isSuccessful()) {
	
			Email email = new Email();
		email.setFrom("MetaAid Team <metaaid.team@gmail.com>");
		email.setTo(register.getEmail().trim());
		email.setSubject("MetaAid Updated Password Details..!");
		String msg = "Dear Sir/Madam,<br><br>Your registration details are as follows...<br><br><b>Username : </b>"+ register.getUsername() + "<br><br><b>Password : </b>"+ register.getPassword(); 

		email.setText(msg);

		this.emailService.sendHtmlMsg(email);
		}
		return response;
	}
 	
	
	public Response getProfile(Integer id)
	{
		response.setSuccessful(false);
		Register singleuserdetails = registerDAO.getProfile(id);
		response.setSuccessful(true);
		response.setResponseObject(singleuserdetails);
		return response;
	}
	public Response changePassword(Register register) throws JSONException {
		response.setSuccessful(false);
		registerDAO.changePassword(register);
		response.setSuccessful(true);
		response.setResponseObject(register);
		if(response.isSuccessful()) {
		Email email = new Email();
		email.setFrom("MetaAid Team <metaaid.team@gmail.com>");
		email.setTo(register.getEmail().trim());
		email.setSubject("Student Portal Email Code Verification..!");
		String msg  = "Dear Sir/Madam,<br><br>Your registration details are as follows...<br><br><b>Username : </b>"+ register.getUsername() + "<br><br><b>Password : </b>"+ register.getPassword(); 

		email.setText(msg);

		this.emailService.sendHtmlMsg(email);
		}
		return response;
	}
	public Response getDistricts()
	{
		response.setSuccessful(false);
		List<Map<String, Object>> list = registerDAO.getDistricts();
		response.setSuccessful(true);
		response.setResponseObject(list);
		return response;
	}
	public Response getProblems()
	{
		response.setSuccessful(false);
		List<Map<String, Object>> list = registerDAO.getProblems();
		response.setSuccessful(true);
		response.setResponseObject(list);
		return response;
	}

	public Response getColleges(Integer districtId)
	{
		response.setSuccessful(false);
		List<College> colleges = registerDAO.getColleges(districtId);
		response.setSuccessful(true);
		response.setResponseObject(colleges);
		return response;
	}
	public Response getHospitals(Integer collegeId)
	{
		response.setSuccessful(false);
		List<Hospital> colleges= registerDAO.getHospitals(collegeId);
		response.setSuccessful(true);
		response.setResponseObject(colleges);
		return response;
	}
	public Response getSpeciality(String hospital)
	{
		response.setSuccessful(false);
		List<Speciality> hospitals= registerDAO.getSpeciality(hospital);
		response.setSuccessful(true);
		response.setResponseObject(hospitals);
		return response;
	}
	public Response getData1(String problem)
	{
		response.setSuccessful(false);
		List<Speciality> hospital= registerDAO.getData1(problem);
		response.setSuccessful(true);
		response.setResponseObject(hospital);
		return response;
	}
	public Response getData2(Register register)
	{
		response.setSuccessful(false);
		List<Register> hospitals= registerDAO.getData2(register);
		response.setSuccessful(true);
		response.setResponseObject(hospitals);
		return response;
	}
	
	
	public Response addReceptionist(Register register) throws JSONException {
		this.response.setSuccessful(false);
		boolean status = registerDAO.addReceptionist(register);
		if (status) {
			Email email = new Email();
			email.setFrom("MetaAid Team <metaaid.team@gmail.com>");
			email.setTo(register.getEmail().trim());
			email.setSubject("MetaAid - Hospital Registration Sucessfull!!.");
			String msg = "Dear "+ register.getHospital() + ",<br><br>Your registration details are as follows...<br><br><b>Username : </b>"+ register.getUsername() + "<br><br><b>Password : </b>"+ register.getPassword(); 

			email.setText(msg);

			this.emailService.sendHtmlMsg(email); 

			this.response.setSuccessful(true);      
		} else {
			this.response.setSuccessful(false);
			this.response.setErrorMessage("Link expired!");
		}
		return this.response;
	}
	
	public Response insertHospitals(Hospital hospital) {
		response.setSuccessful(false);
		registerDAO.insertHospitals(hospital);
		response.setSuccessful(true);
		response.setResponseObject(hospital);
		return response;
	}
	public Response getHospitalDetails()
	{
		response.setSuccessful(false);
		List<Register> register= registerDAO.getHospitalDetails();
		response.setSuccessful(true);
		response.setResponseObject(register);
		return response;
	}
	public Response addHospitalDetails(Register register) {
		response.setSuccessful(false);
		registerDAO.addHospitalDetails(register);
		response.setSuccessful(true);
		response.setResponseObject(register);
		return response;
	}
	public Response addappointment(Register register) throws JSONException {
		this.response.setSuccessful(false);
		boolean status = registerDAO.addappointment(register);
		if (status) {
			Email email = new Email();
			email.setFrom("MetaAid Team <metaaid.team@gmail.com>");
			email.setTo(register.getEmail().trim());
			email.setSubject("MetaAid - Your Appointment request is Sucessfull!!.");
			String msg = "Dear Participant,<br><br>You will recieve a mail for the time ,<br>Thank you for Requesting Apppointment  in MetaAid"; 

			email.setText(msg);

			this.emailService.sendHtmlMsg(email); 

			this.response.setSuccessful(true);      
		} else {
			this.response.setSuccessful(false);
			this.response.setErrorMessage("Link expired!");
		}
		return this.response;
	}
	public Response getDoctorAppointment(Register register)
	{
		response.setSuccessful(false);
		List<Register> hospitals= registerDAO.getDoctorAppointment(register);
		response.setSuccessful(true);
		response.setResponseObject(hospitals);
		return response;
	}
	public Response setDoctorsAppointment(Register register) throws JSONException {
		this.response.setSuccessful(false);
		boolean status = registerDAO.setDoctorsAppointment(register);
		if (status) {
			Email email = new Email();
			email.setFrom("MetaAid Team <metaaid.team@gmail.com>");
			email.setTo(register.getEmail().trim());
			email.setSubject("MetaAid - Your Appointment  is setted Sucessfull!!.");
			String msg = "Dear "+ register.getUsername() +",<br><br>Your appointment is dated on"+ register.getDate() +" at time "+ register.getTime() +",<br>Thank you for Regarding Apppointment  in MetaAid"; 

			email.setText(msg);

			this.emailService.sendHtmlMsg(email); 

			this.response.setSuccessful(true);      
		} else {
			this.response.setSuccessful(false);
			this.response.setErrorMessage("Link expired!");
		}
		return this.response;
	}
	public Response addTreatmentData(Speciality register) {
		response.setSuccessful(false);
		registerDAO.addTreatmentData(register);
		response.setSuccessful(true);
		response.setResponseObject(register);
		return response;
	}
	public Response getAppointments(Register register)
	{
		response.setSuccessful(false);
		List<Register> hospitals= registerDAO.getAppointments(register);
		response.setSuccessful(true);
		response.setResponseObject(hospitals);
		return response;
	}
	public Response delAppointment(Register register) {
		response.setSuccessful(false);
		registerDAO.delAppointment(register);
		response.setSuccessful(true);
		response.setResponseObject(register);
		return response;
	}
}
