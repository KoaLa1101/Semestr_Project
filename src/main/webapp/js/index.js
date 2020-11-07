document.addEventListener('DOMContentLoaded', function (){
    let password = document.getElementById("inputPasswordSignUp"),
        confirmPassword = document.getElementById("inputPasswordRepeatSignUp");


    function validatePassword(){
        if(password.value !==confirmPassword.value){
            confirmPassword.setCustomValidity("Passwords don't match");
        } else{
            confirmPassword.setCustomValidity('');
        }
    }

    confirmPassword.onkeyup = validatePassword();
},false);