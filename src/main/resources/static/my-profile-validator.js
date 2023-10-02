function validate() {
    var login = document.getElementById("login");
    var loginError = document.getElementById("login-error");

    var name = document.getElementById("name");
    var nameError = document.getElementById("name-error");

    var surname = document.getElementById("surname");
    var surnameError = document.getElementById("surname-error");

    var birthdate = document.getElementById("birthdate");
    var birthdateError = document.getElementById("birthdate-error");

    var email = document.getElementById("email");
    var emailError = document.getElementById("email-error");

    var phoneNumber = document.getElementById("phoneNumber");
    var phoneNumberError = document.getElementById("phoneNumber-error");

    var street = document.getElementById("street");
    var streetError = document.getElementById("street-error");

    var zipCode = document.getElementById("zipCode");
    var zipCodeError = document.getElementById("zipCode-error");

    var city = document.getElementById("city");
    var cityError = document.getElementById("city-error");


    var loginRegex = /^.{2,}$/;
    var passwordRegex = /^.{2,}$/;
    var password2Regex = /^.{2,}$/;
    var nameRegex = /^.{2,}$/;
    var surnameRegex = /^.{2,}$/;
    var birthdateRegex = /^\d{4}[\/\-](0?[1-9]|1[012])[\/\-](0?[1-9]|[12][0-9]|3[01])$/;
    var emailRegex = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
    var phoneNumberRegex = /^(\d{3}[- .]?){2}\d{3}$/;
    var streetRegex = /^.+$/;
    var zipCodeRegex = /^(\d{2}[- ]?)\d{3}$/;
    var cityRegex = /^.+$/;


    var result = true;


    if(!loginRegex.test(login.value)) {
        loginError.innerHTML = "Login musi mieć co najmniej 2 znaki";
        loginError.classList.add("error-on");
        result = false;
    } else {
        loginError.innerHTML = "";
    }

    if(!nameRegex.test(name.value)) {
        nameError.innerHTML =  "Imię musi mieć co najmniej 2 znaki";
        nameError.classList.add("error-on");
        result = false;
    } else {
        nameError.innerHTML = "";
    }

    if(!surnameRegex.test(surname.value)) {
        surnameError.innerHTML =  "Nazwisko musi mieć co najmniej 2 znaki";
        surnameError.classList.add("error-on");
        result = false;
    } else {
        surnameError.innerHTML = "";
    }

    if(!birthdateRegex.test(birthdate.value)) {
        birthdateError.innerHTML = "Podaj poprawną datę urodzenia";
        birthdateError.classList.add("error-on");
        result = false;
    } else {
        birthdateError.innerHTML = "";
    }

    if(!emailRegex.test(email.value)) {
        emailError.innerHTML = "Podaj poprawny email";
        emailError.classList.add("error-on");
        result = false;
    } else {
        emailError.innerHTML = "";
    }

/*    if (!(genders[0].checked == true || genders[1].checked == true)) {
        genderError.innerHTML = "Wybierz płeć";
        genderError.classList.add("error-on");
        result = false;
    } else {
        genderError.innerHTML = "";
    }*/

    if(!phoneNumberRegex.test(phoneNumber.value)) {
        phoneNumberError.innerHTML = "Podaj poprawny 9-cyfrowy numer telefonu";
        phoneNumberError.classList.add("error-on");
        result = false;
    } else {
        phoneNumberError.innerHTML = "";
    }

    if(!streetRegex.test(street.value)) {
        streetError.innerHTML = "Ulica musi mieć co najmniej 1 znak";
        streetError.classList.add("error-on");
        result = false;
    } else {
        streetError.innerHTML = "";
    }

    if(!zipCodeRegex.test(zipCode.value)) {
        zipCodeError.innerHTML = "Podaj kod pocztowy w formacie 00-000";
        zipCodeError.classList.add("error-on");
        result = false;
    } else {
        zipCodeError.innerHTML = "";
    }

    if(!cityRegex.test(city.value)) {
        cityError.innerHTML = "Miasto musi mieć co najmniej 1 znak";
        cityError.classList.add("error-on");
        result = false;
    } else {
        cityError.innerHTML = "";
    }
    return result;
}