function generatePdf() {
        document.forms["pdfForm"].submit();
    }

    function submitForm() {
        document.getElementById("myForm").submit();
    }
    window.addEventListener('load', function () {
        document.documentElement.style.zoom = '90%';
    });

    const submitButton = document.getElementById('submit-button');
    submitButton.addEventListener('click', function () {
        const currentValue = submitButton.value;
        if (currentValue === 'Baked') {
            submitButton.value = 'To Be Made';
        } else {
            submitButton.value = 'Baked';
        }
    });

    function mySearchFunction() {
        var input, filter, gridContainer, gridItems, cardTitles, i, txtValue;
        input = document.getElementById("myInput");
        filter = input.value.toLowerCase();
        gridContainer = document.getElementsByClassName("grid-container")[0];
        gridItems = gridContainer.getElementsByClassName("grid-item");

        for (i = 0; i < gridItems.length; i++) {
            cardTitles = gridItems[i].getElementsByClassName("card-title");
            txtValue = cardTitles[0].textContent || cardTitles[0].innerText;

            if (txtValue.toLowerCase().indexOf(filter) > -1) {
                gridItems[i].style.display = "";
            } else {
                gridItems[i].style.display = "none";
            }
        }
    }