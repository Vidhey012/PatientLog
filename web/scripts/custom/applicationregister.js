var reg = angular.module('RegistrationModule', []);
reg.directive('dateInput', function(){
    return {
        restrict : 'A',
        scope : {
            ngModel : '='
        },
        link: function (scope) {
            if (scope.ngModel) scope.ngModel = new Date(scope.ngModel);
        }
    }
});
reg.controller('RegistrationController', ['$http','$scope', function($http, $scope) {
	
	
	$http.get('/hospital/register/getProblems').then(function(response) {
		$scope.data = response.data;
		if($scope.data.successful)
		{
			$scope.problem = $scope.data.responseObject;
		} 
		else
		{
			console.log("Client error while getting data");
		}
	},
	function(response)
	{
		console.log("Server error while getting data");
	});
	$http.get('/hospital/register/getDistricts').then(function(response) {
		$scope.data = response.data;
		if($scope.data.successful)
		{
			$scope.districts = $scope.data.responseObject;
		} 
		else
		{
			console.log("Client error while getting data");
		}
	},
	function(response)
	{
		console.log("Server error while getting data");
	});
	
	$scope.getData1 = function(totalObj)
	{
		$http.post('/hospital/register/getData1', totalObj.problem).then(function(response)
		{
			$scope.data = response.data;
			if($scope.data.successful)
			{
				$scope.data1 = $scope.data.responseObject;
				$scope.data = {object : totalObj};
			}
			else
			{
				console.log("Client error while getting data");
			}
		},
		function(response)
		{
			console.log("Server error while getting data");
		});
	}
	
	$scope.getData2 = function(totalObj)
	{
		$http.post('/hospital/register/getData2', totalObj).then(function(response)
		{
			$scope.data = response.data;
			if($scope.data.successful)
			{
				$scope.appointment = $scope.data.responseObject;
				$scope.data = {object : totalObj};
			}
			else
			{
				console.log("Client error while getting data");
			}
		},
		function(response)
		{
			console.log("Server error while getting data");
		});
	}

	$scope.getColleges = function(totalObj)
	{
		$http.post('/hospital/register/getColleges', totalObj.districtId).then(function(response)
		{
			$scope.data = response.data;
			if($scope.data.successful)
			{
				$scope.colleges = $scope.data.responseObject;
				$scope.data = {object : totalObj};
			}
			else
			{
				console.log("Client error while getting data");
			}
		},
		function(response)
		{
			console.log("Server error while getting data");
		});
	}
	
	$scope.getHospitals = function(totalObj)
	{
		$http.post('/hospital/register/getHospitals', totalObj.collegeId).then(function(response)
		{
			$scope.data = response.data;
			if($scope.data.successful)
			{
				$scope.hospital1 = $scope.data.responseObject;
				$scope.data = {object : totalObj};
			}
			else
			{
				console.log("Client error while getting data");
			}
		},
		function(response)
		{
			console.log("Server error while getting data");
		});
	}
	$scope.getSpeciality = function(totalObj)
	{
		$http.post('/hospital/register/getSpeciality', totalObj.hospital).then(function(response)
		{
			$scope.data = response.data;
			if($scope.data.successful)
			{
				$scope.speciality1 = $scope.data.responseObject;
				$scope.data = {object : totalObj};
			}
			else
			{
				console.log("Client error while getting data");
			}
		},
		function(response)
		{
			console.log("Server error while getting data");
		});
	};
	
		$scope.registration = function(Data) {
			if(Data.email==null || Data.mobileno==null|| Data.username==null || Data.password==null || Data.aadhar==null ||Data.dob==null || Data.email==''|| Data.mobileno=='' || Data.username=='' || Data.aadhar==''|| Data.password==''||Data.dob==''){
				swal({
					  title: "WARNING!",
					  text: "Please Check and RE-ENTER your Details..!",
					  icon: "warning",
					}).then(() => {
						Andromeda.showRegisterPage();
					});
			}
			else{
			swal("Your Request is Processing , Please Wait...", {
				  buttons: false,
				  timer: 6000,
				});
		console.log(Data);
		
		
		$http.post('/hospital/register/add', Data).then(
				function(response) {
					
					
					$scope.data = response.data;
					
					console.log( $scope.data );
					if ($scope.data.successful) {
						swal({
							  title: "SUCCESS!",
							  text: Data.username + "\'s Registration Done Successfully :)",
							  icon: "success",
							}).then(() => {
								Andromeda.showLoginPage();
								});
					} else {
						swal({
							  title: "OOPS!",
							  text: Data.username + "\'s Registration Failed :(",
							  icon: "error",
							});
					}
				}, function(errResponse) {
					swal({
						  title: "WARNING!",
						  text: "Please Check and RE-ENTER your Details..!",
						  icon: "warning",
						}).then(() => {
							Nav.showRegisterPage();
						});
					console.error('Error Occoured During Registration Process...!');
				}
				
		
		);  }                                                
	};
	
	
	$http.post('/hospital/register/viewaccounts').then(
			function(response) {
				
				
				$scope.data = response.data;
				
				console.log( $scope.data );
				if ($scope.data.successful) {
					$scope.userdetails = $scope.data.responseObject;
					console.log($scope.userdetails);
				} else {
					swal("Accounts Viewing Operation Failed...!");
				}
			}, function(errResponse) {
				console.error('Error Occoured In Accounts Viewing Process...!');
			}
			
	);
	
	$scope.getDetails = function(username) {
		console.log(username);
		
		
		$http.post('/hospital/register/getDetails', username).then(
				function(response) {
					
					
					$scope.data = response.data;
					
					console.log( $scope.data );
					if ($scope.data.successful) {
						$scope.data={object:$scope.data.responseObject};
					} else {
						swal("Account Viewing Operation Failed...!");
					}
				}, function(errResponse) {
					console.error('Error Occoured In Account Viewing Process...!');
				}
				
		
		);
	};
	
	$scope.updateDetails = function(singleUserData){
		console.log(singleUserData.email);
		$http.post('/hospital/register/updateDetails', singleUserData).then(
				function(response) {
					$scope.data = response.data;
					if ($scope.data.successful) {
						swal(singleUserData.username + "\'s Details Updated Successfully...!");
						window.location.reload();
					} else {
						swal(singleUserData.username + "\'s Details Updation Failed...!");
					}
				}, function(errResponse) {
					console.error('Error Occoured In Updation Process...!');
				});
	};
	
	$scope.removeDetails = function(username) {
		var flag=confirm("Do You Really Want To Remove " + username + "\'s Details...?");
		if(flag)
		{
			console.log(username);		
			$http.post('/hospital/register/removeDetails', username).then(
					function(response) {
						$scope.data = response.data;
						if ($scope.data.successful) {
							swal(username + "\'s Details Removed Successfully...!");
							window.location.reload();
						} else {
							swal(username + "\'s Details not Deleted...!");
						}
					}, function(errResponse) {
						console.error('Error Occoured In Deletion Process...!');
					});	
		}
	};
	
	$scope.login = function(object) {
			console.log(object);
			$http.post('/hospital/register/login', object).then(
					function(response) {
						$scope.data = response.data;
						
						if ($scope.data.successful) {
							swal("You Are Currently Logined As " + object.username + "...!");
							Andromeda.showAfterLoginPage();
						} else {
							swal("Incorrect User-Name Or Password...!");
						}
					}, function(errResponse) {
						console.error('Error Occoured In Login Process...!');
					});	
			};
			
			$scope.forgotpassword=function(){
				swal({
					  title:"Enter Your Email Id Here:",
					  content: "input",
					  button: {
						    text: "Send!",
						    closeModal: false,
						  },
				       icon:"info",
					})
					.then((email) => {
						console.log(email);
						console.log("1111111111111111111111111111111111111111111111111111111111111111111111111111111");
						$http.post('/hospital/register/verifyEmail', email).then(
								function(response) {
									$scope.data = response.data;
									
									console.log( $scope.data.responseObject );
									if ($scope.data.successful) {
										console.log("1111111111111111111111111111111111111111111111111111111111111111111111111111111");
										$http.post('/hospital/register/forgotpassword', $scope.data.responseObject).then(
												function(response) {
													
													$scope.data = response.data;
													
													console.log( $scope.data );
													if ($scope.data.successful) {
														swal({
															  title: "SUCCESS!",
															  text: "Email Sent Sucessfully :)",
															  icon: "success",
															}).then(() => {window.location.reload();});
														
													} else {
														swal({
															  title: "OOPS!",
															  text: "Email Entered Doesn't Exist :(",
															  icon: "error",
															}).then(() => {window.location.reload();});
													}
													
												}), function(errResponse) {
											        swal.stopLoading();
													console.error('Error Occoured During Sending User Details..!');
												}
									} else {
										swal({
											  title: "OOPS!",
											  text: "Email Entered Doesn't Exist :(",
											  icon: "error",
											}).then(() => {window.location.reload();});
									}
								}, function(errResponse) {
									window.location.reload();
									console.error('Error Occoured During Email Checking Process...!');
								}
								
						
						);
					});
			};			
					
			$scope.changePassword=function(Data){
				console.log(Data);
				if(Data.oldpassword==null || Data.oldpassword=='' || Data.newpassword==null || Data.newpassword=='' || Data.password==null || Data.password==''){
					swal({
						  title: "OOPS!",
						  text: "Invalid Details :(",
						  icon: "error",
						}).then(() => {Nav.showChangePasswordPage();});
				}else{
				$http.post('/hospital/register/getProfile', uid).then(
						function(response) {
							if(response.data.responseObject.password==Data.oldpassword){
								if(Data.newpassword==Data.password){
									Data.username=uname;
									Data.email=response.data.responseObject.email;
									console.log(Data);
									swal("Your Request is Processing , Please Wait...", {
										  buttons: false,
										  timer: 6000,
										});
									$http.post('/hospital/register/changePassword', Data).then(
											function(response) {
												$scope.data = response.data;
												
												console.log( $scope.data );
												if ($scope.data.successful) {
													swal({
														  title: "SUCCESS!",
														  text: "Password Updated Sucessfully :)",
														  icon: "success",
														}).then(() => {Nav.showChangePasswordPage();});
													
												} else {
													swal({
														  title: "OOPS!",
														  text: "Password Updation Failed :(",
														  icon: "error",
														}).then(() => {Nav.showChangePasswordPage();});
												}
											}), function(errResponse) {
												console.error('Error Occoured During Password Changing Process...!');
											}
								}else{
									swal({
										  title: "OOPS!",
										  text: "Passwords entered are Mismatched :(",
										  icon: "error",
										}).then(() => {Nav.showChangePasswordPage();});
								}
							}else{
								swal({
									  title: "OOPS!",
									  text: "Entered Password is Invalid :(",
									  icon: "error",
									}).then(() => {Nav.showChangePasswordPage();});
							}
						}), function(errResponse) {
							console.error('Error Occoured During Password Verifying Process...!');
						}}
			};
			
			
/*------------------------------------------------------------------ADD HOSPITALS OPERATIONS--------------------------------------------------------------------------------*/				
			
			$scope.insertHospitals = function(Data) {
				console.log(Data);
				
				
				$http.post('/hospital/register/insertHospitals', Data).then(
						function(response) {
							
							
							$scope.data = response.data;
							
							console.log( $scope.data );
							if ($scope.data.successful) {
								alert(Data.username + "\'s Hospital Registration Done Successfully...!");
								window.location.reload();
							} else {
								alert(Data.username + "\'s Hospital Registration Failed...!");
							}
						}, function(errResponse) {
							alert("Account with Hospital Name \'" + Data.username + "\' is Already Existed , Better Login Or Try Another Hospital Name To Register...!");
							console.error('Error Occoured In Registration Process...!');
						}
						
				
				);
			};
			
			$scope.addReceptionist = function(Data) {
				if(Data.email==null || Data.password==null || Data.hospital==null || Data.email==''|| Data.password==''|| Data.hospital==''){
					swal({
						  title: "WARNING!",
						  text: "Please Check and RE-ENTER your Details..!",
						  icon: "warning",
						}).then(() => {
							$scope.getHospitalDetails(Data);
							Andromeda.showHospitalPage();
						});
				}
				else{
				swal("Your Request is Processing , Please Wait...", {
					  buttons: false,
					  timer: 6000,
					});
				Data.username=Data.hospital;
				Data.type="Hospital";
			console.log(Data);
			
			
			$http.post('/hospital/register/addReceptionist', Data).then(
					function(response) {
						
						
						$scope.data = response.data;
						
						console.log( $scope.data );
						if ($scope.data.successful) {
							swal({
								  title: "SUCCESS!",
								  text: Data.username + "\'s Registration Done Successfully :)",
								  icon: "success",
								}).then(() => {
									Andromeda.showHospitalPage();
									});
						} else {
							swal({
								  title: "OOPS!",
								  text: Data.username + "\'s Registration Failed :(",
								  icon: "error",
								});
						}
					}, function(errResponse) {
						swal({
							  title: "WARNING!",
							  text: "Please Check and RE-ENTER your Details..!",
							  icon: "warning",
							}).then(() => {
								Andromeda.showHospitalPage();
							});
						console.error('Error Occoured During Registration Process...!');
					}
					
			
			);  }                                                
		};
		
		$scope.addHospitalDetails = function(Data) {
			if(Data.email==null || Data.password==null || Data.hospital==null || Data.email==''|| Data.password==''|| Data.hospital==''){
				swal({
					  title: "WARNING!",
					  text: "Please Check and RE-ENTER your Details..!",
					  icon: "warning",
					}).then(() => {
						$scope.getHospitalDetails(Data);
						Andromeda.showHospitalPage();
					});
			}
			else{
			swal("Your Request is Processing , Please Wait...", {
				  buttons: false,
				  timer: 6000,
				});
			Data.username=Data.hospital;
			Data.type="Hospital";
		console.log(Data);
		
		
		$http.post('/hospital/register/addHospitalDetails', Data).then(
				function(response) {
					
					
					$scope.data = response.data;
					
					console.log( $scope.data );
					if ($scope.data.successful) {
						swal({
							  title: "SUCCESS!",
							  text: Data.username + "\'s Registration Done Successfully :)",
							  icon: "success",
							}).then(() => {
								Andromeda.showHospitalPage();
								});
					} else {
						swal({
							  title: "OOPS!",
							  text: Data.username + "\'s Registration Failed :(",
							  icon: "error",
							});
					}
				}, function(errResponse) {
					swal({
						  title: "WARNING!",
						  text: "Please Check and RE-ENTER your Details..!",
						  icon: "warning",
						}).then(() => {
							Andromeda.showHospitalPage();
						});
					console.error('Error Occoured During Registration Process...!');
				}
				
		
		);  }                                                
	};
	
	

		
		$scope.updateHospitalDetails = function(singleUserData){
			singleUserData.username=singleUserData.hospital;
			console.log(singleUserData.email);
			$http.post('/hospital/register/updateDetails', singleUserData).then(
					function(response) {
						$scope.data = response.data;
						if ($scope.data.successful) {
							swal(singleUserData.username + "\'s Details Updated Successfully...!");
							window.location.reload();
						} else {
							swal(singleUserData.username + "\'s Details Updation Failed...!");
						}
					}, function(errResponse) {
						console.error('Error Occoured In Updation Process...!');
					});
		};
		
			$http.post('/hospital/register/getHospitalDetails').then(
					function(response) {
						$scope.data = response.data;
						if ($scope.data.successful) {
							$scope.hospitaldetails = $scope.data.responseObject;
							$scope.data = {object : singleUserData};
						} else {
							swal("Hospital Accounts Viewing Operation Failed...!");
						}
					}, function(errResponse) {
						console.error('Error Occoured In Hospital Accounts Viewing Process...!');
					});
	
			// View Data from Database                      
			email=Andromeda.getSessionValue("email");
			$http.post('/hospital/register/getById',email).then(function(response) {
				$scope.data = response.data;
				if ($scope.data.successful) {
					$scope.data ={object: $scope.data.responseObject};
				} else {
					alert("Can't view the Data");
				}
			}, function(errResponse) {
				console.error('Error while viewing notes');
			});
/*-------------------------------------------------------------------------------------------Appointments---------------------------------------------------------*/
			$scope.addappointment = function(Data) {
				if(Data.email==null || Data.mobileno==null || Data.hospital==null|| Data.doctor==null || Data.problem==null || Data.date==null || Data.problem=='' || Data.dat=='' || Data.doctor=='' || Data.email==''|| Data.mobileno==''|| Data.hospital==''){
					swal({
						  title: "WARNING!",
						  text: "Please Check and RE-ENTER your Details..!",
						  icon: "warning",
						}).then(() => {
							
							Andromeda.showAppointmentPage();
						});
				}
				else{
				swal("Your Request is Processing , Please Wait...", {
					  buttons: false,
					  timer: 6000,
					});
				Data.status='Terminated';
				
			console.log(Data);
			
			
			$http.post('/hospital/register/addappointment', Data).then(
					function(response) {
						
						
						$scope.data = response.data;
						
						console.log( $scope.data );
						if ($scope.data.successful) {
							swal({
								  title: "SUCCESS!",
								  text: Data.username + "\'s Appointment Request Done Successfully :)",
								  icon: "success",
								}).then(() => {
									Andromeda.showAppointmentPage();
									});
						} else {
							swal({
								  title: "OOPS!",
								  text: Data.username + "\'s No Appointment Available in ths day :(",
								  icon: "error",
								});
						}
					}, function(errResponse) {
						swal({
							  title: "WARNING!",
							  text: "Please Check and RE-ENTER your Details..!",
							  icon: "warning",
							}).then(() => {
								Andromeda.showAppointmentPage();
							});
						console.error('Error Occoured During Appointment Request Process...!');
					}
					
			
			);  }                                                
		};
		$scope.getDoctorAppointment = function(totalObj)
		{
			totalObj.status='Terminated';
			$http.post('/hospital/register/getDoctorAppointment', totalObj).then(function(response)
			{
				$scope.data = response.data;
				if($scope.data.successful)
				{
					$scope.dappointments = $scope.data.responseObject;
					$scope.data = {object : totalObj};
				}
				else
				{
					console.log("Client error while getting data");
				}
			},
			function(response)
			{
				console.log("Server error while getting data");
			});
		}
		$scope.setDoctorsAppointment = function(Data) {
			if(Data.time==null || Data.time==''){
				swal({
					  title: "WARNING!",
					  text: "Please Check and RE-ENTER your Details..!",
					  icon: "warning",
					}).then(() => {
						
						Andromeda.showReceptionistAppointmentPage();
					});
			}
			else{
			swal("Your Request is Processing , Please Wait...", {
				  buttons: false,
				  timer: 6000,
				});
			Data.status='Active';
			
		console.log(Data);
		
		
		$http.post('/hospital/register/setDoctorsAppointment', Data).then(
				function(response) {
					
					
					$scope.data = response.data;
					
					console.log( $scope.data );
					if ($scope.data.successful) {
						swal({
							  title: "SUCCESS!",
							  text: Data.username + "\'s Appointment set Successfully :)",
							  icon: "success",
							}).then(() => {
								Andromeda.showReceptionistAppointmentPage();
								});
					} else {
						swal({
							  title: "OOPS!",
							  text: Data.username + "\'s Appointment is not set Successfully :(",
							  icon: "error",
							});
					}
				}, function(errResponse) {
					swal({
						  title: "WARNING!",
						  text: "Please Check and RE-ENTER your Details..!",
						  icon: "warning",
						}).then(() => {
							Andromeda.showReceptionistAppointmentPage();
						});
					console.error('Error Occoured During Appointment setting Process...!');
				}
				
		
		);  }                                                
	};
	$scope.getAppointments = function(totalObj)
	{
		totalObj.status='Active';
		$http.post('/hospital/register/getAppointments', totalObj).then(function(response)
		{
			$scope.data = response.data;
			if($scope.data.successful)
			{
				$scope.appoint = $scope.data.responseObject;
				$scope.data = {object : totalObj};
			}
			else
			{
				console.log("Client error while getting data");
			}
		},
		function(response)
		{
			console.log("Server error while getting data");
		});
	}
	$scope.delAppointment = function(Data) {
		if(Data.time==null || Data.time==''){
			swal({
				  title: "WARNING!",
				  text: "Please Check and RE-ENTER your Details..!",
				  icon: "warning",
				}).then(() => {
					
					Andromeda.showViewAppointmentPage();;
				});
		}
		else{
		swal("Your Request is Processing , Please Wait...", {
			  buttons: false,
			  timer: 6000,
			});
		Data.status='Active';
		
	console.log(Data);
	
	
	$http.post('/hospital/register/delAppointment', Data).then(
			function(response) {
				
				
				$scope.data = response.data;
				
				console.log( $scope.data );
				if ($scope.data.successful) {
					swal({
						  title: "SUCCESS!",
						  text: Data.username + "\'s Appointment set to completed Successfully :)",
						  icon: "success",
						}).then(() => {
							Andromeda.showViewAppointmentPage();
							});
				} else {
					swal({
						  title: "OOPS!",
						  text: Data.username + "\'s Appointment is not set to completed Successfully :(",
						  icon: "error",
						});
				}
			}, function(errResponse) {
				swal({
					  title: "WARNING!",
					  text: "Please Check .!",
					  icon: "warning",
					}).then(() => {
						Andromeda.showViewAppointmentPage();
					});
				console.error('Error Occoured During Appointment setting completed Process...!');
			}
			
	
	);  }                                                
};
	/*--------------------------------------------------------------------------Add treatments---------------------------------------------------------------------------------*/

	$scope.addTreatmentData = function(Data) {
		console.log(Data);
		
		
		$http.post('/hospital/register/addTreatmentData', Data).then(
				function(response) {
					
					
					$scope.data = response.data;
					
					console.log( $scope.data );
					if ($scope.data.successful) {
						alert(Data.hospital + "\'s Add Treatment Done Successfully...!");
						Andromeda.showSpecialityPage();
					} else {
						alert(Data.hospital + "\'s Add Treatment Failed...!");
					}
				}, function(errResponse) {
					alert("Account with Hospital Name \'" + Data.name + "\' is Already Existed , Better Login Or Try Another Treatment Name To Register...!");
					console.error('Error Occoured In Add Treatment Process...!');
				}
				
		
		);
	};
	
}]);