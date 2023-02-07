package com.spring.Model;

import com.andromeda.commons.model.BaseModel;

public class Contact extends BaseModel
{
private String name;
private String email;
private String subject;
private String status;

public String getStatus() {
	return status;
}
public void setStatus(String status) {
	this.status = status;
}
private String feedback;
public String getFeedback() {
	return feedback;
}
public void setFeedback(String feedback) {
	this.feedback = feedback;
}
public String getReply() {
	return reply;
}
public void setReply(String reply) {
	this.reply = reply;
}
private String reply;


public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getSubject() {
	return subject;
}
public void setSubject(String subject) {
	this.subject = subject;
}



}
