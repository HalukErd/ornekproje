$(saveCustomerButton.onclick(function () {
    $("form[name='saveCustomer']").validate({
        rules: {
            tcknTextBox: {
                required: true,
                length: 11
            },
            customerName: "required",
            customerLastName: "required",
            citiesDropDown: {
                required: true
            },
            townsDropDown: {
                required: true
            }
        },
        messages: {
            customerName: "Please enter your first name",
            customerLastName: "Please enter your last name",
            tcknTextBox: {
                required: "Please provide a tckn",
                length: "Your tckn must be 11 characters long"
            },
            citiesDropDown: {
                required: "Please select a city"
            },
            townsDropDown: {
                required: "Please select a town"
            }
        }
    });
}));