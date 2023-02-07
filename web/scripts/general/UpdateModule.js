
var Registration = angular.module('UpdateModule', []);
Registration.controller('RegController', ['$http','$scope', function($http, $scope) {
	
			
			//Update User Details
			$scope.changeData = function(UserData){
				/*alert(UserData.username);*/
				$http.post('/hospital/register/changeData', UserData).then(
						function(response) {
							$scope.data = response.data;
							if ($scope.data.successful) {
								/*alert("User password is updated");*/
							} else {
								alert("Data not updated");
							}
						}, function(errResponse) {
							console.error('Error while fetching notes');
						});
			};

			$scope.updateData = function(UserData){
				/*alert(UserData.username);*/
				$http.post('/hospital/register/updateData', UserData).then(
						function(response) {
							$scope.data = response.data;
							if ($scope.data.successful) {
								/*alert("User profile is updated");*/
							} else {
								alert("Data not updated");
							}
						}, function(errResponse) {
							console.error('Error while fetching notes');
						});
			};
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

		
		} ]);
