
var Contact = angular.module('ContactModule', []);
Contact.controller('ContactController', ['$http','$scope', function($http, $scope) {


// User  Contact Module
	
	$scope.insertFeedback = function(Data) {
		   if(Data.email==null || Data.feedback==null){
			   swal({
					  title: "WARNING!",
					  text: Data.name + " \'s Feedback is Incomplete :(",
					  icon: "error",
					}).then(() => {
						Andromeda.showContactPage();
					});
		   }else{
		    Data.reply=" ";
	        Data.status="Active";
	    	console.log(Data);
			$http.post('/hospital/Contact/insertFeedback', Data).then(
					function(response) {
						
						$scope.data = response.data;
						
						console.log( Data );
						if ($scope.data.successful) {
							swal({
								  title: "SUCCESS!",
								  text: Data.name + " \'s Feedback Sended Successfully :)",
								  icon: "success",
								}).then(() => {
									Andromeda.showContactPage();
									});
							
						} else {
							swal({
								  title: "OOPS!",
								  text: Data.name + " \'s Feedback Not Sended :(",
								  icon: "error",
								}).then(() => {
									Andromeda.showContactPage();
								});
						}
					}, function(errResponse) {
						swal({
							  title: "Warning!",
							  text: "Invalid Data Not Allowed :(",
							  icon: "warning",
							}).then(() => {
								Andromeda.showContactPage();
							});
						console.error('Error Occoured During Feedback Insertion Process...!');
					}
					
			
			);}
		};
		
		

		



	$http.post('/hospital/Contact/viewFeedbacks').then(
						function(response) {
									$scope.data = response.data;
									console.log($scope.data);
									if ($scope.data.successful) {
									$scope.feedbacks=$scope.data.responseObject;
									console.log($scope.feedbacks);
									} else {
										console.error('Error Occoured During Feedback Viewing Process...!');
											}
									}, function(errResponse) {
											console.error('Error Occoured During Feedback Viewing Process...!');
									});	

		
		
		
		

	$scope.deleteFeedback = function(Data) {
			console.log(Data);		
			$http.post('/hospital/Contact/deleteFeedback', Data).then(
			function(response) {
						$scope.data = response.data;
						if ($scope.data.successful) {
						swal({
							 title: "SUCCESS!",
							 text: Data.name + " \'s Feedback Removed Successfully :)",
							 icon: "success",
							})
							.then(() => { Andromeda.showFeedbackPage(); });		
						} else {
								swal({
									 title: "OOPS!",
									 text: Data.name + " \'s Feedback Not Removed :(",
									 icon: "error",
									}).then(() => {	Andromeda.showFeedbackPage(); });
								}
						}, function(errResponse) {
								console.error('Error Occoured During Feedback Deletion Process...!');
						});	
	};




	$scope.sendReply = function(Data) {
		swal({
			  title:"Enter Your Reply Here:",
			  content: "input",
			  button: {
				    text: "Send!",
				    closeModal: false,
				  },
				  icon:"info",
			})
			.then((reply) => {
				if(reply==null || reply==''){
					swal({
						  title: "OOPS!",
						  text: "Reply Not Sent :(",
						  icon: "error",
						}).then(() => {
							Andromeda.showFeedbackPage();
						});
				}else{
				Data.reply=reply;
		console.log(Data.reply);
		$http.post('/hospital/Contact/sendReply', Data).then(
				function(response) {
					
					$scope.data = response.data;
					
					if ($scope.data.successful) {
						swal({
							  title: "SUCCESS!",
							  text:  "Reply Sent to "+ Data.name +" Successfully :)",
							  icon: "success",
							}).then(() => {
								Andromeda.showFeedbackPage();
								});
						
					} else {
						swal({
							  title: "OOPS!",
							  text: "Reply Not Sent :(",
							  icon: "error",
							}).then(() => {
								Andromeda.showFeedbackPage();
							});
					}
				}, function(errResponse) {
					console.error('Error Occoured During Reply sending Process...!');
					Nav.showMessagesPage();
				}
				
		
		);}
			});
		};
}]);