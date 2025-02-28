function generatePdf() {
    document.forms["pdfForm"].submit();
}

function submitForm() {
    document.getElementById("myForm").submit();
}
window.addEventListener('load', function () {
    document.documentElement.style.zoom = '90%';
});

//    const submitButton = document.getElementById('submit-button');
//    submitButton.addEventListener('click', function () {
//        const currentValue = submitButton.value;
//        if (currentValue === 'Delivered') {
//            submitButton.value = 'Baked';
//        } else {
//            submitButton.value = 'Delivered';
//        }
//    });



//    function searchOrders() {
//        var input = document.getElementById("search-input").value;
//        var orderItems = document.getElementsByClassName("grid-item");
//
//        for (var i = 0; i < orderItems.length; i++) {
//            var orderItem = orderItems[i];
//            var deliveryDate = orderItem.getElementsByClassName("card-title")[1].innerText;
//
//            // Convert the deliveryDate to a date object
//            var deliveryDateObj = new Date(deliveryDate);
//
//            // Format the input date in the same format as the delivery date
//            var inputDate = new Date(input).toLocaleDateString();
//
//            if (inputDate === deliveryDateObj.toLocaleDateString()) {
//                orderItem.style.display = "block";
//            } else {
//                orderItem.style.display = "none";
//            }
//        }
//    }

function searchParameterOrders() {
    var startDate = document.getElementById("start-date").value;
    var endDate = document.getElementById("end-date").value;
    var orderItems = document.getElementsByClassName("grid-item");

    for (var i = 0; i < orderItems.length; i++) {
        var orderItem = orderItems[i];
        var deliveryDate = orderItem.getElementsByClassName("card-title")[1].innerText.toLowerCase();

        if (isWithinRange(deliveryDate, startDate, endDate)) {
            orderItem.style.display = "block";
        } else {
            orderItem.style.display = "none";
        }
    }

    // Reset the input fields
    document.getElementById("start-date").value = "";
    document.getElementById("end-date").value = "";
}

function isWithinRange(dateString, startDate, endDate) {
    var deliveryDate = new Date(dateString);
    var start = new Date(startDate);
    var end = new Date(endDate);

    return deliveryDate >= start && deliveryDate <= end;
}


// Get today's date
var today = new Date().toISOString().split('T')[0];

// Set today's date as the default value for the date input fields
document.getElementById("start-date").value = today;
document.getElementById("end-date").value = today;
document.getElementById("search-input").value = today;
