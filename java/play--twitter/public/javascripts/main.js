$(document).ready(function() {  
    $("#process-login").click(function(e) {
        e.preventDefault();
        document.forms["login-form"].submit();
    });
});