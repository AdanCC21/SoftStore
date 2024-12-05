const doneElement = document.getElementById('Done');

function showDoneAndRedirect() {
    
    
    doneElement.classList.add("spawn");
    setTimeout(() => {
        window.location.href = "/Articles";
    }, 1000);
}

addEventListener('load', showDoneAndRedirect);
