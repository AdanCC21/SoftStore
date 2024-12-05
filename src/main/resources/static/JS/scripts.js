window.addEventListener('scroll', () => {
    const boton = document.querySelector('#elBoton');
    const scrollY = window.scrollY;

    // Actualiza la posición si necesitas que cambie dinámicamente (opcional)
    boton.style.bottom = `${20}px`;
});

