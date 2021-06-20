$(document).ready(function () {

    findCustomerButton = $("#findCustomerButton");
    updateCustomerButton = $("#updateCustomerButton");
    citiesDropDown = $("#citiesDropDown");
    townsDropDown = $("#townsDropDown");
    tcknTextBox = $("#tcknTextBox");
    customerName = $("#customerName");
    customerLastName = $("#customerLastName");

    getCities();

    findCustomerButton.click(function () {

        updatePropDisabledStatus();
        urlGetCustomer = contextPath + "api/customers/" + tcknTextBox.val();
        $.get(urlGetCustomer, function (responseJson) {
            customerName.val(responseJson.name);
            customerLastName.val(responseJson.lastName);
            citiesDropDown.val(responseJson.address.city);
            getTownsAndSelect(responseJson.address.town)
        }).fail(function () {
            alert('Customer Get Failed');
        })
    });

    updateCustomerButton.click(function () {
        urlCustomerApi = contextPath + "api/customers";
        tcknValue = tcknTextBox.val();
        nameValue = customerName.val();
        lastNameValue = customerLastName.val();
        cityValue = citiesDropDown.val();
        townValue = townsDropDown.val();

        jsonData = {
            tckn: tcknValue,
            name: nameValue,
            lastName: lastNameValue,
            address: {
                city: cityValue,
                town: townValue
            }
        }

        $.ajax({
            type: "PUT",
            url: urlCustomerApi,
            data: JSON.stringify(jsonData),
            contentType: 'application/json'
        }).done(function () {
            // redirectionSuccess();
            $(location).attr('href', contextPath + "registrationSuccess", 'myheader', 'success');
        }).fail(function () {
            window.location.href = contextPath + "saveCustomer";
        });
    });

    citiesDropDown.change(function () {
        getTowns();
    });


    function updatePropDisabledStatus() {
        findCustomerButton.prop('disabled', true)
        customerName.prop('disabled', false);
        customerLastName.prop('disabled', false);
        citiesDropDown.prop('disabled', false);
        townsDropDown.prop('disabled', false);
        updateCustomerButton.prop('disabled', false);
    }

    function getCities() {
        urlGetCities = contextPath + "api/cityandtowns/getcities";

        $.get(urlGetCities, function (responseJson) {

            $.each(responseJson, function (index, city) {
                $("<option>").text(city).appendTo(citiesDropDown);
            });
        }).fail(function () {
            alert('failed to load cities');
        });
    }

    function getTowns() {
        urlGetTowns = contextPath + "api/cityandtowns/gettowns/" + citiesDropDown.val();

        $.get(urlGetTowns, function (responseJson) {

            townsDropDown.empty();

            $.each(responseJson, function (index, town) {
                $("<option>").text(town).appendTo(townsDropDown);
            });
        }).fail(function () {
            alert('failed to load towns');
        });
    }

    function getTownsAndSelect(townToSelect) {
        urlGetTowns = contextPath + "api/cityandtowns/gettowns/" + citiesDropDown.val();

        $.get(urlGetTowns, function (responseJson) {
            townsDropDown.empty();

            $.each(responseJson, function (index, town) {
                $("<option>").text(town).appendTo(townsDropDown);
            });
            townsDropDown.val(townToSelect);
        }).fail(function () {
            alert('failed to load towns');
        });
    }
});