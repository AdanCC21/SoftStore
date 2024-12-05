window.addEventListener('scroll', () => {
    const boton = document.querySelector('#shoppingCart');
    const scrollY = window.scrollY;

    // Actualiza la posición si necesitas que cambie dinámicamente (opcional)
    boton.style.bottom = `${20}px`;
});


document.querySelectorAll('.TableItem').forEach(item => {
    const h3 = item.querySelector('h3'); // Selecciona el h3 dentro de cada <a>

    const img = item.querySelector('img'); // Selecciona la imagen dentro de cada <a>
    if (h3 && img) {
        const h3Text = h3.textContent.trim(); // Obtén el texto del h3
        img.src = `/assets/${h3Text}.jpg`; // Cambia el src de la imagen
    }
});
