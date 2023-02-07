package com.spring.Controller;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONException;
import  org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.andromeda.commons.model.Response;
import com.spring.Model.Contact;
import com.spring.Service.ContactService;

@RestController
@RequestMapping("/Contact")
public class ContactController {    


@Autowired
private ContactService ContactService;

@ResponseBody
@RequestMapping(value = "insertFeedback", method = { RequestMethod.POST })
public Response insertFeedback(@RequestBody Contact register)
{
	return ContactService.insertFeedback(register);
}

@ResponseBody
@RequestMapping(value = "viewFeedbacks", method = { RequestMethod.POST, RequestMethod.GET })
public Response viewFeedbacks()
{
	return ContactService.viewFeedbacks();
}

@ResponseBody
@RequestMapping(value = "deleteFeedback", method = { RequestMethod.POST })
public Response deleteFeedback(@RequestBody Contact register)
{
	return ContactService.deleteFeedback(register);
}

@ResponseBody
@RequestMapping(value = "sendReply", method = {org.springframework.web.bind.annotation.RequestMethod.POST })
public Response sendReply(@RequestBody Contact register,HttpServletRequest httpServletRequest) throws JSONException
{
	return this.ContactService.sendReply(register);
}


}

