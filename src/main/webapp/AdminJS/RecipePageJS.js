function submitForm() {
        document.getElementById("myForm").submit();
    }
    window.addEventListener('load', function () {
        document.documentElement.style.zoom = '90%';
    });

    // Initialize Variables
    var closePopup = document.getElementById("popupclose");
    var overlay = document.getElementById("overlay");
    var popup = document.getElementById("popup");
    var button = document.getElementById("button");
    // Close Popup Event
    closePopup.onclick = function () {
        overlay.style.display = 'none';
        popup.style.display = 'none';
    };
    // Show Overlay and Popup
    button.onclick = function () {
        overlay.style.display = 'block';
        popup.style.display = 'block';
    };

    //sort buttons for tables

    function sortTableAlphabetical() {
        var table, rows, switching, i, x, y, shouldSwitch;
        table = document.getElementById("myTable");
        switching = true;
        /*Make a loop that will continue until
         no switching has been done:*/
        while (switching) {
            //start by saying: no switching is done:
            switching = false;
            rows = table.rows;
            /*Loop through all table rows (except the
             first, which contains table headers):*/
            for (i = 1; i < (rows.length - 1); i++) {
                //start by saying there should be no switching:
                shouldSwitch = false;
                /*Get the two elements you want to compare,
                 one from current row and one from the next:*/
                x = rows[i].getElementsByTagName("TD")[0];
                y = rows[i + 1].getElementsByTagName("TD")[0];
                //check if the two rows should switch place:
                if (x.innerHTML.toLowerCase() > y.innerHTML.toLowerCase()) {
                    //if so, mark as a switch and break the loop:
                    shouldSwitch = true;
                    break;
                }
            }
            if (shouldSwitch) {
                /*If a switch has been marked, make the switch
                 and mark that a switch has been done:*/
                rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
                switching = true;
            }
        }
    }

    //sort for active

    function sortTableInactive() {
        var table, rows, switching, i, x, y, shouldSwitch;
        table = document.getElementById("myTable");
        switching = true;
        /*Make a loop that will continue until
         no switching has been done:*/
        while (switching) {
            //start by saying: no switching is done:
            switching = false;
            rows = table.rows;
            /*Loop through all table rows (except the
             first, which contains table headers):*/
            for (i = 1; i < (rows.length - 1); i++) {
                //start by saying there should be no switching:
                shouldSwitch = false;
                /*Get the two elements you want to compare,
                 one from the current row and one from the next:*/
                x = rows[i + 1].getElementsByTagName("TD")[2];
                y = rows[i ].getElementsByTagName("TD")[2];
                //check if the two rows should switch place:
                if (x.innerHTML.toLowerCase() === "Active" && y.innerHTML.toLowerCase() !== "Active" ||
                        x.innerHTML.toLowerCase() > y.innerHTML.toLowerCase()) {
                    //if so, mark as a switch and break the loop:
                    shouldSwitch = true;
                    break;
                }
            }
            if (shouldSwitch) {
                /*If a switch has been marked, make the switch
                 and mark that a switch has been done:*/
                rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
                switching = true;
            }
        }
    }
    function sortTableActive() {
        var table, rows, switching, i, x, y, shouldSwitch;
        table = document.getElementById("myTable");
        switching = true;
        /*Make a loop that will continue until
         no switching has been done:*/
        while (switching) {
            //start by saying: no switching is done:
            switching = false;
            rows = table.rows;
            /*Loop through all table rows (except the
             first, which contains table headers):*/
            for (i = 1; i < (rows.length - 1); i++) {
                //start by saying there should be no switching:
                shouldSwitch = false;
                /*Get the two elements you want to compare,
                 one from the current row and one from the next:*/
                x = rows[i].getElementsByTagName("TD")[2];
                y = rows[i + 1].getElementsByTagName("TD")[2];
                //check if the two rows should switch place:
                if (x.innerHTML.toLowerCase() === "Active" && y.innerHTML.toLowerCase() !== "Active" ||
                        x.innerHTML.toLowerCase() > y.innerHTML.toLowerCase()) {
                    //if so, mark as a switch and break the loop:
                    shouldSwitch = true;
                    break;
                }
            }
            if (shouldSwitch) {
                /*If a switch has been marked, make the switch
                 and mark that a switch has been done:*/
                rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
                switching = true;
            }
        }
    }

    // search tab

    function mySearchFunction() {
        var input, filter, table, rows, i, txtValue;
        input = document.getElementById("myInput");
        filter = input.value.toUpperCase();
        table = document.getElementById("myTable");
        rows = table.rows;

        for (i = 1; i < rows.length; i++) {
            var cell = rows[i].getElementsByTagName("TD")[0]; // Assuming the username is in the first column (index 0)
            if (cell) {
                var usernameInput = cell.querySelector('input[name="recipeName"]');
                if (usernameInput) {
                    txtValue = usernameInput.value.toUpperCase();
                    if (txtValue.indexOf(filter) > -1) {
                        rows[i].style.display = "";
                    } else {
                        rows[i].style.display = "none";
                    }
                }
            }
        }

        sortTableBySearchVisibility();
    }

    function sortTableBySearchVisibility() {
        var table, rows, switching, i, x, y, shouldSwitch;
        table = document.getElementById("myTable");
        switching = true;

        while (switching) {
            switching = false;
            rows = table.rows;

            for (i = 1; i < rows.length - 1; i++) {
                shouldSwitch = false;
                x = rows[i].style.display;
                y = rows[i + 1].style.display;

                // Compare the display values
                if (x === "none" && y !== "none") {
                    shouldSwitch = true;
                    break;
                }
            }

            if (shouldSwitch) {
                rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
                switching = true;
            }
        }
    }

    function closeForm() {
        var popup = document.getElementById("popup");
        popup.style.display = "none";
    }
    var message = '<%=request.getAttribute("message")%>';
    console.log(message);
    if (message !== 'null' && message !== '') {
        var messageContainer = document.getElementById("messageContainer");
        messageContainer.innerHTML = message;
        var popup = document.getElementById("popup");
        popup.style.display = "block";
    }

//==========================de/activate hover========================================
    const submitButton = document.getElementById('submit-button');
    submitButton.addEventListener('click', function () {
        const currentValue = submitButton.value;
        if (currentValue === 'Activate') {
            submitButton.value = 'Deactivate';
        } else {
            submitButton.value = 'Activate';
        }
    });