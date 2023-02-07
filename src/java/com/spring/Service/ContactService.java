
package com.spring.Service;

import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.andromeda.commons.model.Response;
import com.spring.Dao.ContactDAO;
import com.spring.Model.Contact;
import com.spring.Model.Email;

@Service
public class ContactService {

Response response = new Response();

@Autowired
private ContactDAO contactDAO;
@Autowired
EmailService emailService;

public Response insertFeedback(Contact Contact) {
	response.setSuccessful(false);
	contactDAO.insertFeedback(Contact);
	response.setSuccessful(true);
	response.setResponseObject(Contact);
	return response;
}

public Response viewFeedbacks()
{
	response.setSuccessful(false);
	List<Map<String, Object>> list = contactDAO.viewFeedbacks();
	response.setSuccessful(true);
	response.setResponseObject(list);
	return response;
}

public Response deleteFeedback(Contact Contact)
{
	response.setSuccessful(false);
	contactDAO.deleteFeedback(Contact);
	response.setSuccessful(true);
	response.setResponseObject(Contact);
	return response;
}


public Response sendReply(Contact Contact) throws JSONException {
	response.setSuccessful(false);
	contactDAO.sendReply(Contact);
	response.setSuccessful(true);
	response.setResponseObject(Contact);
	if(response.isSuccessful()) {
	Email email = new Email();
	email.setFrom("MetaAid gate <joinus@apssdc.in>");
	email.setTo(Contact.getEmail().trim());
	email.setSubject("Feedback-Reply..!");
	String msg = "Hello "+Contact.getName()+",<br><br>Thank You For Your Response.Your Feedback is as follows..<br><br>"+Contact.getFeedback()+"<br><br>Our Reply for your Feedback is as follows..<br><br>"+Contact.getReply(); 

	email.setText(msg);

	this.emailService.sendHtmlMsg(email);
	}
	return response;
}



}

