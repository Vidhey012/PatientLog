package com.spring.Controller;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.andromeda.commons.model.Response;
import com.spring.Model.Contact;
import com.spring.Model.Register;
import com.spring.Model.Hospital;
import com.spring.Model.Speciality;
import com.spring.Service.RegisterService;


@RestController
@RequestMapping("/register")
public class Registration {
	
	Response response = new Response();

	@Autowired
	private RegisterService registerService;
	

	@ResponseBody
	@RequestMapping(value = "add", method = {
			org.springframework.web.bind.annotation.RequestMethod.POST })
	public Response add(@RequestBody Register register,HttpServletRequest httpServletRequest) throws JSONException
	{
		return this.registerService.add(register);
	}

	@ResponseBody
	@RequestMapping(value = "viewaccounts", method = { RequestMethod.POST })
	public Response viewaccounts()
	{
		return registerService.viewaccounts();
	}
	
	@ResponseBody
	@RequestMapping(value = "getDetails", method = { RequestMethod.POST })
	public Response getDetails(@RequestBody String username)
	{
		return registerService.getDetails(username);
	}
	
	@ResponseBody
	@RequestMapping(value = "updateDetails", method = { RequestMethod.POST })
	public Response updateDetails(@RequestBody Register register)
	{
		return registerService.updateDetails(register);
	}
	
	@ResponseBody
	@RequestMapping(value = "removeDetails", method = { RequestMethod.POST })
	public Response removeDetails(@RequestBody String username)
	{
		return registerService.removeDetails(username);
	}
	
	@ResponseBody
	@RequestMapping(value = "changeData", method = { RequestMethod.POST })
	public Response changeData(@RequestBody Register register)
	{
		return registerService.changeData(register);
	}
	
	@ResponseBody
	@RequestMapping(value = "updateData", method = { RequestMethod.POST })
	public Response updateData(@RequestBody Register register)
	{
		return registerService.updateData(register);
	}
	
	@ResponseBody
	@RequestMapping(value = "getById", method = { RequestMethod.POST })
	public Response getById(@RequestBody String name)
	{
		return registerService.getById(name);
	}
	@ResponseBody
	@RequestMapping(value = "login", method = { RequestMethod.POST })
	public Response login(@RequestBody Register register)
	{
		return registerService.login(register);
	}
	
	@ResponseBody
	@RequestMapping(value = "verifyEmail", method = { RequestMethod.POST })
	public Response verifyEmail(@RequestBody String email)
	{
		return registerService.verifyEmail(email);
	}

	@ResponseBody
	@RequestMapping(value = "forgotpassword", method = {org.springframework.web.bind.annotation.RequestMethod.POST })
	public Response forgotpassword(@RequestBody Register register,HttpServletRequest httpServletRequest) throws JSONException
	{
		return this.registerService.forgotpassword(register);
	}
	@ResponseBody
	@RequestMapping(value = "getProblems", method = { RequestMethod.POST, RequestMethod.GET })
	public Response getProblems()
	{
		return registerService.getProblems();
	}
	@ResponseBody
	@RequestMapping(value = "getDistricts", method = { RequestMethod.POST, RequestMethod.GET })
	public Response getDistricts()
	{
		return registerService.getDistricts();
	}
	
	@ResponseBody
	@RequestMapping(value = { "/getColleges" }, method = { RequestMethod.POST })
	public Response getColleges(@RequestBody Integer districtId)
	{
		return registerService.getColleges(districtId);
	}
	@ResponseBody
	@RequestMapping(value = { "/getHospitals" }, method = { RequestMethod.POST })
	public Response getHospitals(@RequestBody Integer collegeId)
	{
		return registerService.getHospitals(collegeId);
	}
	@ResponseBody
	@RequestMapping(value = { "/getSpeciality" }, method = { RequestMethod.POST })
	public Response getSpeciality(@RequestBody String hospital)
	{
		return registerService.getSpeciality(hospital);
	}
	@ResponseBody
	@RequestMapping(value = { "/getData1" }, method = { RequestMethod.POST })
	public Response getData1(@RequestBody String problem)
	{
		return registerService.getData1(problem);
	}
	@ResponseBody
	@RequestMapping(value = { "/getData2" }, method = { RequestMethod.POST })
	public Response getData2(@RequestBody Register register)
	{
		return registerService.getData2(register);
	}
	@ResponseBody
	@RequestMapping(value = "addReceptionist", method = {
			org.springframework.web.bind.annotation.RequestMethod.POST })
	public Response addReceptionist(@RequestBody Register register,HttpServletRequest httpServletRequest) throws JSONException
	{
		return this.registerService.addReceptionist(register);
	}
	@ResponseBody
	@RequestMapping(value = "insertHospitals", method = { RequestMethod.POST })
	public Response insertHospitals(@RequestBody Hospital register)
	{
		return registerService.insertHospitals(register);
	}
	@ResponseBody
	@RequestMapping(value = "getHospitalDetails", method = { RequestMethod.POST })
	public Response getHospitalDetais()
	{
		return registerService.getHospitalDetails();
	}
	@ResponseBody
	@RequestMapping(value = "addHospitalDetails", method = { RequestMethod.POST })
	public Response addHospitalDetails(@RequestBody Register register)
	{
		return this.registerService.addHospitalDetails(register);
	}
	@ResponseBody
	@RequestMapping(value = "addappointment", method = { org.springframework.web.bind.annotation.RequestMethod.POST })
	public Response addappointment(@RequestBody Register register,HttpServletRequest httpServletRequest) throws JSONException
	{
		return this.registerService.addappointment(register);
	}
	@ResponseBody
	@RequestMapping(value = { "/getDoctorAppointment" }, method = { RequestMethod.POST })
	public Response getDoctorAppointment(@RequestBody Register register)
	{
		return registerService.getDoctorAppointment(register);
	}
	@ResponseBody
	@RequestMapping(value = "setDoctorsAppointment", method = { org.springframework.web.bind.annotation.RequestMethod.POST })
	public Response setDoctorsAppointment(@RequestBody Register register,HttpServletRequest httpServletRequest) throws JSONException
	{
		return this.registerService.setDoctorsAppointment(register);
	}
	@ResponseBody
	@RequestMapping(value = "addTreatmentData", method = { RequestMethod.POST })
	public Response addTreatmentData(@RequestBody Speciality register)
	{
		return this.registerService.addTreatmentData(register);
	}
	@ResponseBody
	@RequestMapping(value = { "/getAppointments" }, method = { RequestMethod.POST })
	public Response getAppointments(@RequestBody Register register)
	{
		return registerService.getAppointments(register);
	}
	@ResponseBody
	@RequestMapping(value = "delAppointment", method = { RequestMethod.POST })
	public Response delAppointment(@RequestBody Register register)
	{
		return this.registerService.delAppointment(register);
	}
}
