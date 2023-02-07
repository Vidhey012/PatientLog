var Andromeda={
		
		showPage : function(path, targetDiv) {
			var jqxhr = jQuery.post(path, function(data) {
				jQuery("#" + targetDiv).html(data);
			});
		},	
		
		showHomePage : function() {
			var path = "/hospital/html/home1.html";
			Andromeda.showPage(path, "name");
		},
		
		showLoginPage : function() {
			var path = "/hospital/html/login.html";
			Andromeda.showPage(path, "replaceDiv");
		},
		
		showRegisterPage : function() {
			var path = "/hospital/html/register.html";
			Andromeda.showPage(path, "replaceDiv");
		},
		
		showAboutPage : function() {
			var path = "/hospital/html/aboutus.html";
			Andromeda.showPage(path, "replaceDiv");
		},
		
		showContactPage : function() {
			var path = "/hospital/html/contact.html";
			Andromeda.showPage(path, "replaceDiv");
		},
		
		showAccountsPage : function() {
			var path = "/hospital/html/accounts.html";
			Andromeda.showPage(path, "actionSpace");
		},
		
		showAfterLoginPage : function() {
			var path = "/hospital/html/afterlogin.html";
			Andromeda.showPage(path, "actionSpace");
		},
		
		showForgotPasswordPage : function() {
			var path = "/hospital/html/forgotPassword.html";
			Andromeda.showPage(path, "replaceDiv");
		},
		
		showAdminLoginPage : function() {
			var path = "/hospital/html/admin.html";
			Andromeda.showPage(path, "name");
		},
		showDoctorLoginPage : function() {
			var path = "/hospital/html/doctorLogin.html";
			Andromeda.showPage(path, "name");
		},
		
		showUserLoginPage : function() {
	 		var path = "/hospital/html/userLogin.html";
			Andromeda.showPage(path, "name");
		},
		
		showServicePage : function() {
			var path = "/hospital/html/service.html";
			Andromeda.showPage(path, "backDiv");
		},
		
		showReportsPage : function() {
			var path = "/hospital/html/reports.html";
			Andromeda.showPage(path, "backDiv");
		},
		
		showBillsPage : function() {
			var path = "/hospital/html/bills.html";
			Andromeda.showPage(path, "backDiv");
		},
		
		showAppointmentPage : function() {
			var path = "/hospital/html/appointment.html";
			Andromeda.showPage(path, "backDiv");
		},
		
		showProfilePage : function() {
			var path = "/hospital/html/profile.html";
			Andromeda.showPage(path, "backDiv");
		},
		setSessionValue : function(key, value) {
			sessionStorage.setItem(key, value);
		},

		getSessionValue : function(key) {
			return sessionStorage.getItem(key);
		},
		
		removeSessionValue : function(key) {
			sessionStorage.removeItem(key);
		},

		showError : function(errorMessage) {
			var message = "<div class=\"alert alert-danger\"><strong>Error: </strong>"
					+ errorMessage + "</div>";
			jQuery("#errorDiv").html(message);
		},

		logout : function() {
			
			var email = Andromeda.getSessionValue("email") || "";
			Andromeda.setSessionValue("email", null);
			Andromeda.setSessionValue("password", null);
			var data = {
				email : email
			};  
			Andromeda.post('/hospital/login/logout', data);
			Andromeda.showHomePage();
		},
	   
		post : function(url, data) {
			var responseData = null;

			jQuery.ajax({
				url : url,
				type : 'post',
				data : JSON.stringify(data), // Stringified Json Object
				dataType : 'json',
				async : false, // Cross-domain requests and dataType: "jsonp"
				// requests do not support synchronous operation
				cache : false, // This will force requested pages not to be cached
				// by the browser
				processData : false, // To avoid making query String instead of
				// JSON
				contentType : "application/json; charset=utf-8",
				success : function(data) {
					responseData = data;
				}
			});

			return responseData;
		},

		isUserLoggedIn : function() {
			/*console.log("userrrrr:"+Andromeda.getSessionValue("userName"));
			console.log("contextttttt:"+Andromeda.getSessionValue("context"));*/
			var email = Andromeda.getSessionValue("email") || "";
			var password = Andromeda.getSessionValue("password") || "";
			var type = Andromeda.getSessionValue("type") || "";

			var login = {
				email : email,
				password : password,
				type : type
				
			};

			return Andromeda.post('/hospital/login/loggedin', login) || false;
		}  	
};