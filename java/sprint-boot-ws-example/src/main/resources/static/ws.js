var socket = new WebSocket("ws://" + location.host + "/testHandler?userId=" + Math.random());

var writeToLog = function(msg) {
    var messageBox = document.getElementById("messageBox");
    messageBox.insertRow().textContent = msg;
};

var sendMessage = function() {
    var content = document.getElementById("textMessage").value;
    socket.send(content);
};

var stopAcceptingMessages = function() {
    socket.close()
};

socket.onopen = function() {
    writeToLog("Socket is opened");
};

socket.onclose = function() {
    writeToLog("Socket is closed");
};

socket.onmessage = function(msg) {
    writeToLog(msg.data)
};
