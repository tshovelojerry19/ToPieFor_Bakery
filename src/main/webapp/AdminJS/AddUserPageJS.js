window.addEventListener('load', function () {
    document.documentElement.style.zoom = '90%';
});

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
function enforceLength(input) {
    var value = input.value.replace(/\D/g, ''); // Remove non-digit characters
    if (value.length > 10) {
        value = value.slice(0, 10);
    }
    input.value = value;
}

