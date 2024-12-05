const title = document.getElementById('WelcomeTitle');

function Redirect (){
    title.classList.add('spawn');
    setTimeout(() =>{
        window.location.href="/Articles";
    },3000);
}

addEventListener('load',Redirect);