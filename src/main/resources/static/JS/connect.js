let stompClient = null;

function connect() {
    const socket = new SockJS('/chat');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function () {
        stompClient.subscribe('/topic/messages', function (message) {
            showMessage(message.body);
        });
    });
}

function sendMessage() {
    const message = document.getElementById("messageInput").value;
    stompClient.send("/app/sendMessage", {}, message);
    document.getElementById("messageInput").value = '';
}

function showMessage(message) {
    const messagesDiv = document.getElementById("messages");
    const messageElement = document.createElement("div");
    messageElement.textContent = message;
    messagesDiv.appendChild(messageElement);
}

connect();