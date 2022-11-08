window.onload = () => {
    this.sessionStorage.setItem("username", "stefanpopa");
    this.sessionStorage.setItem("password", "stefanpopa1");
}

var input = document.getElementsByTagName('input');
var login = document.getElementById('log-in');
var form = document.querySelector('form');
form.onsubmit = () =>{
    return false
}

login.onclick = () =>{
    if(input[0].value == sessionStorage.getItem("username") && input[1].value == sessionStorage.getItem("password")){
        form.onsubmit = () =>{
            return 1;
        }
    }
    else{
        document.getElementById("error-msg").innerHTML = "Username or Password is incorrect"
    }
}