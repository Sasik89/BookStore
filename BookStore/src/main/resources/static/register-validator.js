function validate(){

    var name = document.getElementById("name");
    var surname = document.getElementById("surname");
    var email = document.getElementById("email");
    var login = document.getElementById("login");
    var info = document.getElementById("info");
    var password = document.getElementById("password");
    var password2 = document.getElementById("password2");

    var nameRegex = /^[A-Z]{1}[a-z]+$/;
    var surnameRegex = /^[A-Z][a-z]+([ -][A-Z][a-z]+)?$/;
    var loginRegex = /^.{5,}$/;
    var passwordRegex = /.{5,}$/;
    var emailRegex = /^[\w\.-]+@[\w\.-]+\.[A-Za-z0-9]{2,3}$/;

    var result = true;
    var infoResult = "";

    if(!nameRegex.test(name.value)) {
        infoResult = infoResult + "Złe imie !!! <br>";
        name.style.background = "#fcc2c2";
        result = false;
    }   else {
         name.style.background = "#ffffff";
    }

    if(!surnameRegex.test(surname.value)) {
        infoResult = infoResult + "Złe nazwisko!!! <br>";
        surname.style.background = "#fcc2c2";
         result = false;
    }   else {
         surname.style.background = "#ffffff";
    }

    if(!emailRegex.test(email.value)) {
        infoResult = infoResult + "Zły email!!! <br>";
        email.style.background = "#fcc2c2";
         result = false;
    }   else {
         email.style.background = "#ffffff";
    }

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

    if(password.value != password2.value){
        infoResult = infoResult + "Hasła się nie zgadzają <br>!!!";
        password.style.background = "#fcc2c2";
        password2.style.background = "#fcc2c2";
        result = false;
    } else if(!passwordRegex.test(password2.value)) {
        infoResult = infoResult + "Żle powtórzone hasło <br>!!!";
        password2.style.background = "#fcc2c2";
        result = false;
    } else {
        password.style.background = "#ffffff";
        password2.style.background = "#ffffff";
    }

    info.innerHTML = infoResult;
    return result;

}
