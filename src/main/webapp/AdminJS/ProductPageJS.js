 function submitForm() {
        document.getElementById("myForm").submit();
    }
    window.addEventListener('load', function () {
        document.documentElement.style.zoom = '90%';
    });

    //sort buttons for Products

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

    //sort by Price

    function sortTablePrice() {
        var table, rows, switching, i, x, y, shouldSwitch;
        table = document.getElementById("myTable");
        switching = true;
        // Continue until no switching has been done
        while (switching) {
            // Start by assuming no switching is required
            switching = false;
            rows = table.rows;
            // Loop through all table rows (except the first, which contains table headers)
            for (i = 1; i < (rows.length - 1); i++) {
                // Assume no switching is needed for the current iteration
                shouldSwitch = false;
                // Get the two elements you want to compare, one from the current row and one from the next
                x = rows[i].getElementsByTagName("TD")[1];
                y = rows[i + 1].getElementsByTagName("TD")[1];
                // Extract numerical values from the table cells
                var xValue = parseFloat(x.textContent);
                var yValue = parseFloat(y.textContent);
                // Check if the two rows should switch places
                if (xValue < yValue) {
                    // If so, mark as a switch and break the loop
                    shouldSwitch = true;
                    break;
                }
            }

            if (shouldSwitch) {
                // If a switch has been marked, make the switch and mark that a switch has been done
                rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
                switching = true;
            }
        }
    }

    //sort buttons for Recipe

    function sortTableAlphabeticalRecipe() {
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
                x = rows[i].getElementsByTagName("TD")[2];
                y = rows[i + 1].getElementsByTagName("TD")[2];
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

    //sort buttons for Category

    function sortTableAlphabeticalCategory() {
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
                x = rows[i].getElementsByTagName("TD")[4];
                y = rows[i + 1].getElementsByTagName("TD")[4];
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

    //sort for Show
    function sortTableDontShow() {
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
                x = rows[i + 1].getElementsByTagName("TD")[5];
                y = rows[i].getElementsByTagName("TD")[5];
                //check if the two rows should switch place:
                if (x.innerHTML.toLowerCase() === "DontShow" && y.innerHTML.toLowerCase() !== "DontShow" ||
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
    //sort for Show
    function sortTableShow() {
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
                x = rows[i].getElementsByTagName("TD")[5];
                y = rows[i + 1].getElementsByTagName("TD")[5];
                //check if the two rows should switch place:
                if (x.innerHTML.toLowerCase() === "Show" && y.innerHTML.toLowerCase() !== "Show" ||
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
                var usernameInput = cell.querySelector('input[name="productName"]');
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

//        sortTableBySearchVisibility();
    }

//    function sortTableBySearchVisibility() {
//        var table, rows, switching, i, x, y, shouldSwitch;
//        table = document.getElementById("myTable");
//        switching = true;
//
//        while (switching) {
//            switching = false;
//            rows = table.rows;
//
//            for (i = 1; i < rows.length - 1; i++) {
//                shouldSwitch = false;
//                x = rows[i].style.display;
//                y = rows[i + 1].style.display;
//
//                // Compare the display values
//                if (x === "none" && y !== "none") {
//                    shouldSwitch = true;
//                    break;
//                }
//            }
//
//            if (shouldSwitch) {
//                rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
//                switching = true;
//            }
//        }
//    }

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
        if (currentValue === 'Show') {
            submitButton.value = 'DontShow';
        } else {
            submitButton.value = 'Show';
        }
    });