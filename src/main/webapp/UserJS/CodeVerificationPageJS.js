var message = '<%=request.getAttribute("message")%>';
console.log(message);
if (message !== 'null' && message !== '') {
    document.getElementById("errorMsg").innerHTML = message;
}