customersTable = $("#customersTable");

$(document).ready(function () {
    var tableString = '';
    var startRow = '<tr><td>';
    var midRow = '</td><td>';
    var endRow = '</td></tr>';

    urlToGetAllCustomers = contextPath + "api/customers";

    $.get(urlToGetAllCustomers, function (responseAllCustomersJson) {
        $('#thetable tr').not(':first').not(':last').remove();
        $.each(responseAllCustomersJson, function (index, customerJson) {

            tableString += startRow +
                customerJson.tckn + midRow +
                customerJson.name + midRow +
                customerJson.lastName + midRow +
                customerJson.address.city + midRow +
                customerJson.address.town + midRow +
                "<input type='button' id='"+ customerJson.tckn +"' value='Delete' class=btn-delete></button>" + endRow;
        });
        $("table tr:first").after(tableString);
    })


})

$(document).on('click', '.btn-delete', function () {
    deleteCustomer(this.id);
    $(this).closest('tr').remove();
})

function deleteCustomer(tckn) {
    urlToDelete = contextPath + "api/customers/" + tckn;

    $.ajax({
        type: "DELETE",
        url: urlToDelete
    });
}