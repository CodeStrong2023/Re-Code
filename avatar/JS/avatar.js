let ataqueJugador
let ataqueEnemigo
let vidasJugador = 3 //Sabemos en el estado en comienzan estas variables
let vidasEnemigo = 3

document.querySelectorAll('input[type="radio"]').forEach(radio => {
    radio.addEventListener('change', function() {
        // Reinicia los estilos de todos los contenedores de personajes
        document.querySelectorAll('.personaje-img').forEach(div => {
            div.style.border = '2px solid transparent';
            div.style.transform = 'scale(1)';
        });

        // Aplica estilos al contenedor del personaje seleccionado
        const imgDiv = this.nextElementSibling; // Encuentra el div asociado
        imgDiv.style.border = '2px solid red';
        imgDiv.style.transform = 'scale(1.1)';
    });
});

function iniciarJuego() {
    let sectionSeleccionarAtaque = document.getElementById('seleccionar-ataque')
    sectionSeleccionarAtaque.style.display = 'none'
    let botonPersonajeJugador = document.getElementById('boton-personaje');
    botonPersonajeJugador.addEventListener('click', seleccionarPersonajeJugador);
    let sectionReiniciar = document.getElementById('reiniciar')
    sectionReiniciar.style.display = "none"

    document.getElementById("reglas-del-juego").style.display = "none";

    document.getElementById('boton-reglas').addEventListener('click', mostrarReglas);
   
    document.getElementById('boton-jugar').style.display = 'none';
    document.getElementById('seleccionar-personaje').style.display = 'block';

    let botonPunio = document.getElementById('boton-punio') //Ahora creamos un escuchador de eventos
    botonPunio.addEventListener('click', ataquePunio)
    let botonPatada = document.getElementById('boton-patada')
    botonPatada.addEventListener('click', ataquePatada)
    let botonBarrida = document.getElementById('boton-barrida')
    botonBarrida.addEventListener('click', ataqueBarrida)
    //creamos una nueva variable
    let botonReiniciar = document.getElementById('boton-reiniciar')
    botonReiniciar.addEventListener('click', reiniciarJuego)
}

function mostrarReglas() {
    document.getElementById("reglas-del-juego").style.display = "block";
    document.getElementById('boton-jugar').style.display = 'block';
    document.getElementById('boton-reglas').style.display = 'none';
    document.getElementById('seleccionar-personaje').style.display = 'none';
    document.getElementById('boton-jugar').addEventListener('click', seleccionarPersonajeJugador);
}

function seleccionarPersonajeJugador() {
    let sectionSeleccionarAtaque = document.getElementById('seleccionar-ataque');
    sectionSeleccionarAtaque.style.display = 'block'; // Mostramos la sección de ataques
    document.getElementById('boton-reglas').style.display = 'none';
    let sectionSeleccionarPersonaje = document.getElementById('seleccionar-personaje');
    sectionSeleccionarPersonaje.style.display = 'none'; // Ocultamos la selección de personajes

    let inputZuko = document.getElementById('zuko');
    let inputKatara = document.getElementById('katara');
    let inputAang = document.getElementById('aang');
    let inputToph = document.getElementById('toph');
    let spanPersonajeJugador = document.getElementById('personaje-jugador');

    let jugadorImagen = document.getElementById('jugador-ataque');
    let enemigoImagen = document.getElementById('enemigo-ataque');

    // Verificar qué personaje ha sido seleccionado y actualizar su imagen
    if (inputZuko.checked) {
        spanPersonajeJugador.innerHTML = 'Zuko';
        jugadorImagen.style.backgroundImage = "url('css/zuko.png')";
    } else if (inputKatara.checked) {
        spanPersonajeJugador.innerHTML = 'Katara';
        jugadorImagen.style.backgroundImage = "url('css/katara.png')";
    } else if (inputAang.checked) {
        spanPersonajeJugador.innerHTML = 'Aang';
        jugadorImagen.style.backgroundImage = "url('css/aang.png')";
    } else if (inputToph.checked) {
        spanPersonajeJugador.innerHTML = 'Toph';
        jugadorImagen.style.backgroundImage = "url('css/toph.png')";
    } else {
        mostrarModal('No se seleccionó un personaje. Por favor selecciona uno.');
        sectionSeleccionarAtaque.style.display = 'none';
        sectionSeleccionarPersonaje.style.display = 'block';
        return;
    }

    // Seleccionar enemigo aleatoriamente y actualizar su imagen
    let personajeAleatorio = aleatorio(1, 4); // Función para elegir personaje aleatorio
    let spanPersonajeEnemigo = document.getElementById('personaje-enemigo');

    if (personajeAleatorio == 1) {
        spanPersonajeEnemigo.innerHTML = 'Zuko';
        enemigoImagen.style.backgroundImage = "url('css/zuko.png')";
    } else if (personajeAleatorio == 2) {
        spanPersonajeEnemigo.innerHTML = 'Katara';
        enemigoImagen.style.backgroundImage = "url('css/katara.png')";
    } else if (personajeAleatorio == 3) {
        spanPersonajeEnemigo.innerHTML = 'Aang';
        enemigoImagen.style.backgroundImage = "url('css/aang.png')";
    } else {
        spanPersonajeEnemigo.innerHTML = 'Toph';
        enemigoImagen.style.backgroundImage = "url('css/toph.png')";
    }
}



function ataquePunio() { //Modificamos la variable global ataqueJugador
    ataqueJugador = 'Punio'
    ataqueAleatorioEnemigo()
}

function ataquePatada() { //Modificamos la variable global ataqueJugador
    ataqueJugador = 'Patada'
    ataqueAleatorioEnemigo()
}

function ataqueBarrida() { //Modificamos la variable global ataqueJugador
    ataqueJugador = 'Barrida'
    ataqueAleatorioEnemigo()
}

function ataqueAleatorioEnemigo() {//Ahora ocupando la variable global nueva le decimos el ataque y necesitamos la función aleatorio
    let ataqueAleatorio = aleatorio(1, 3)

    if (ataqueAleatorio == 1) {
        ataqueEnemigo = 'Punio'
    } else if (ataqueAleatorio == 2) {
        ataqueEnemigo = 'Patada'
    } else {
        ataqueEnemigo = 'Barrida'
    }
    combate()
}

function combate() {
    let spanVidasJugador = document.getElementById('vidas-jugador')
    let spanVidasEnemigo = document.getElementById('vidas-enemigo')

    //COMBATE
    if (ataqueEnemigo == ataqueJugador) {
        crearMensaje("EMPATE")
    } else if (ataqueJugador == 'Punio' && ataqueEnemigo == 'Barrida') {
        crearMensaje("GANASTE")
        vidasEnemigo--
        spanVidasEnemigo.innerHTML = vidasEnemigo
    } else if (ataqueJugador == 'Patada' && ataqueEnemigo == 'Punio') {
        crearMensaje("GANASTE")
        vidasEnemigo--
        spanVidasEnemigo.innerHTML = vidasEnemigo
    } else if (ataqueJugador == 'Barrida' && ataqueEnemigo == 'Patada') {
        crearMensaje("GANASTE")
        vidasEnemigo--
        spanVidasEnemigo.innerHTML = vidasEnemigo
    } else {
        crearMensaje("PERDISTE")
        vidasJugador--
        spanVidasJugador.innerHTML = vidasJugador
    }
    //Revisar vidas
    revisarVidas()
}

function revisarVidas(){
    if(vidasEnemigo == 0){
        //Ganamos
        crearMensajeFinal("FELICITACIONES!!! HAS GANADO 🤩🥳🎉")
    } else if(vidasJugador == 0){
        //Perdimos
        crearMensajeFinal("QUE PENA, HAS PERDIDO 😢😭😭😭")
    }
}

function crearMensajeFinal(resultado) {
    let sectionReiniciar = document.getElementById('reiniciar');
    sectionReiniciar.style.display = "block";

    let sectionMensaje = document.getElementById('mensajes');
    sectionMensaje.style.display = 'block';  // Mostrar la sección de mensajes
    let parrafo = document.createElement('p');

    parrafo.innerHTML = resultado;

    sectionMensaje.appendChild(parrafo);

    // Deshabilitar botones de ataque
    document.getElementById('boton-punio').disabled = true;
    document.getElementById('boton-patada').disabled = true;
    document.getElementById('boton-barrida').disabled = true;
}

function crearMensaje(resultado) {
    let sectionMensaje = document.getElementById('mensajes');
    sectionMensaje.style.display = 'block';  // Mostrar la sección de mensajes
    let parrafo = document.createElement('p');

    parrafo.innerHTML = 'Tu personaje atacó con ' + ataqueJugador + ', el personaje del enemigo atacó con ' + ataqueEnemigo + ' ' + resultado;

    sectionMensaje.appendChild(parrafo);
}

function reiniciarJuego(){
    location.reload()
}

function aleatorio(min, max) {
    return Math.floor(Math.random() * (max - min + 1) + min)
}



window.addEventListener('load', iniciarJuego)