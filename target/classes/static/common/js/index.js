var token;
var userId;
var usersIds = [];

document.getElementById("login").onclick = function () {
	
	var username = document.getElementById("username").value;
	var password = document.getElementById("password").value;
	var data = '{"username": "' + username + '", "password": "' + password + '"}';
	var xmlhttp = new XMLHttpRequest();
	
	xmlhttp.onreadystatechange = function() {
		if ( this.readyState == XMLHttpRequest.DONE ) {
			var response = JSON.parse(xmlhttp.responseText);
			
			if (response.success) {
				token = response.token;
				userId = response.id;
				document.getElementById("block").className = "hidden";
				document.getElementById("validated").className = "";
			} else {
				alert("Incorrect credentials.");
			}
		}
	};
	xmlhttp.open( "POST", "/api/user/login", true );
	xmlhttp.setRequestHeader("Content-Type", "application/json");
	xmlhttp.send( data );
};

document.getElementById("logout").onclick = function () {
	var data = '{"token" : "' + token + '"}';
	var xmlhttp = new XMLHttpRequest();
	
	xmlhttp.onreadystatechange = function() {
		if ( this.readyState == XMLHttpRequest.DONE ) {
			var response = JSON.parse(xmlhttp.responseText);
			
			if (response.success) {
				token = "";
				userId = "";
				document.getElementById("block").className = "";
				document.getElementById("validated").className = "hidden";
			} else {
				alert("Internal Server Error.");
			}
		}
	};
	xmlhttp.open( "POST", "/api/user/logout/" + userId, true );
	xmlhttp.setRequestHeader("Content-Type", "application/json");
	xmlhttp.send( data );
}

document.getElementById("createUser").onclick = function () {
	var username = document.getElementById("regUsername").value;
	var phone = document.getElementById("regPhone").value;
	var password = document.getElementById("regPassword").value;
	var data = '{"username" : "' + username + '", "phone" : "' + phone + '", "password" : "' + password + '", "token" : "' + token + '"}';
	var xmlhttp = new XMLHttpRequest();

	xmlhttp.onreadystatechange = function() {
		if ( this.readyState == XMLHttpRequest.DONE ) {
			var response = JSON.parse(xmlhttp.responseText);
			
			if (response.success) {
				
			} else {
				alert(response.message);
			}
		}
	};

	xmlhttp.open( "PUT", "/api/user/add", true );
	xmlhttp.setRequestHeader("Content-Type", "application/json");
	xmlhttp.send( data );
}

setInterval(function myMethod() {
	if(token != "" && typeof token !== "undefined") {
		var xmlhttp = new XMLHttpRequest();

		xmlhttp.onreadystatechange = function() {
			if ( this.readyState == XMLHttpRequest.DONE ) {
				var response = JSON.parse(xmlhttp.responseText);
				
				if (response.length > 0) {
					var userList = document.getElementById("userList");
					
					response.forEach(function(item, index) {
						if (typeof usersIds !== "undefined" && usersIds.indexOf(item.id)  == -1) {
							userList.innerHTML += item.id + ", " + item.username + ", " + item.phone + "\n";
							usersIds.push(item.id);
						}
					});
				}
			}
		};

		xmlhttp.open( "GET", "/api/users/" + token, true );
		xmlhttp.send();
	}
}, 500);

























