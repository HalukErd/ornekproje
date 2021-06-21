$(document).ready(function () {
    var hash = window.location.hash;
    if (hash === "#customerSaveFailed") {
        window.location.replace(contextPath + "saveCustomer#customerSaveFailed");
    } else if (hash === "#customerUpdateFailed") {
        window.location.replace(contextPath + "updateCustomer#customerUpdateFailed");
    }
})