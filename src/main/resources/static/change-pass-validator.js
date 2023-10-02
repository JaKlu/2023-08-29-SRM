function validate() {
    var oldPassword = document.getElementById("old-password");
    var oldPasswordError = document.getElementById("old-password-error");

    var newPassword = document.getElementById("new-password");
    var newPasswordError = document.getElementById("new-password-error");

    var newPassword2 = document.getElementById("new-password2");
    var newPassword2Error = document.getElementById("new-password2-error");


    var oldPasswordRegex = /^.{2,}$/;
    var newPasswordRegex = /^.{2,}$/;
    var newPassword2Regex = /^.{2,}$/;


    var result = true;


    if(!oldPasswordRegex.test(oldPassword.value)) {
        oldPasswordError.innerHTML = "Hasło musi mieć co najmniej 2 znaki";
        oldPasswordError.classList.add("error-on");
        result = false;
    } else {
        oldPasswordError.innerHTML = "";
    }

    if(!newPasswordRegex.test(newPassword.value)) {
        newPasswordError.innerHTML = "Hasło musi mieć co najmniej 2 znaki";
        newPasswordError.classList.add("error-on");
        result = false;
    } else {
        newPasswordError.innerHTML = "";
    }

    if(newPassword.value != newPassword2.value) {
        newPassword2Error.innerHTML =  "Nowe hasła muszą się zgadzać";
        newPassword2Error.classList.add("error-on");
        result = false;
    } else {
        newPassword2Error.innerHTML = "";
    }

    return result;
}