$(document).ready(function () {
    form = $("#saveCustomer");
    saveCustomerButton = $("#saveCustomerButton");
    citiesDropDown = $("#citiesDropDown");
    townsDropDown = $("#townsDropDown");
    tcknTextBox = $("#tcknTextBox");
    customerName = $("#customerName");
    customerLastName = $("#customerLastName");

    var hash = window.location.hash;
    if (hash === "#customerSaveFailed") {
        $("#failMessage").show("swing");
    }

    getCities();

    saveCustomerButton.click(function () {
        if (tcknTextBox.val().length !== 11) {
            alert('TCKN must be 11 characters');
        } else {
            saveCustomer();
        }
    });

    citiesDropDown.change(function () {
        getTowns();
    });


    function saveCustomer() {
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
            type: "POST",
            url: urlCustomerApi,
            data: JSON.stringify(jsonData),
            contentType: 'application/json',

            statusCode: {
                200: function () {
                    window.location.replace(contextPath + "registrationSuccess" + "#customerSaved");
                },
                400: function () {
                    window.location.replace(contextPath + "redirection" + "#customerSaveFailed");
                },
                500: function () {
                    window.location.replace(contextPath + "redirection" + "#customerSaveFailed");
                }
            }
        });
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
});
