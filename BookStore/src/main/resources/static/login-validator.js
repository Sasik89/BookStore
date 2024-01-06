function validate(){

    var login = document.getElementById("login");
    var info = document.getElementById("info");
    var password = document.getElementById("password");


    var loginRegex = /^.{5,}$/;
    var passwordRegex = /.{5,}$/;


    var result = true;
    var infoResult = "";

        if(!loginRegex.test(login.value)) {
            infoResult = infoResult + "Zły login!!! <br>";
            login.style.background = "#fcc2c2";
             result = false;
        }   else {
             login.style.background = "#ffffff";
        }

    if(!passwordRegex.test(password.value)) {
        infoResult = infoResult + "Złe hasło!!! <br>";
        password.style.background = "#fcc2c2";
         result = false;
    }   else {
         password.style.background = "#ffffff";
    }

    info.innerHTML = infoResult;
    return result;

    }