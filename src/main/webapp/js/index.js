document.addEventListener('DOMContentLoaded', function (){
    let password = document.getElementById("inputPasswordSignUp"),
        confirmPassword = document.getElementById("inputPasswordRepeatSignUp");
    function checkPassword(){
        let check = /^(?=.*[A-Z].*[A-Z])(?=.*[0-9].*[0-9])(?=.*[a-z].*[a-z].*[a-z]).{6,}$/;
        if(!password.value.match(check)){
            password.setCustomValidity("Wrong password format. " + "Your password must contain from 6 to 20 characters, have Latin characters in lower and upper case and at least 1 number");
        } else{
            password.setCustomValidity('');
        }
    }

    function validatePassword(){
        if(password.value !==confirmPassword.value){
            confirmPassword.setCustomValidity("Passwords don't match");
        } else{
            confirmPassword.setCustomValidity('');
        }
    }

    password.onkeyup = checkPassword();
    confirmPassword.onkeyup = validatePassword();
},false);