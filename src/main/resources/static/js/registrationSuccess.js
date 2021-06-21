$(document).ready(function () {
    var hash = window.location.hash
    alert(hash);
    if(hash === "#customerSaved") {
        alert('You successfully saved customer!');
    }
});