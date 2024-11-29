const socket = new WebSocket('ws://localhost:8080/notifications');

// Manejar mensajes desde el servidor
socket.onmessage = function(event) {
    console.log('Notificación recibida:', event.data);
    // Puedes actualizar un componente de la página con el mensaje
    const notificationArea = document.getElementById('notifications');
    notificationArea.innerHTML += `<p>${event.data}</p>`;
};
