var message = '<%=message%>';
console.log(message);
if (message !== 'null' && message !== '') {
    var messageContainer = document.getElementById("messageContainer");
    messageContainer.innerHTML = '<h2>' + message + '</h2>';
    var popup = document.getElementById("popup");
    popup.style.display = "block";
}