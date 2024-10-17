function hola(nombre){
    return new Promise(function(resolve, reject){
        setTimeout(function(){
            console.log('Hola '+nombre);
            miCallback();
        }, 1000);
    })
}

function hablar(callbackHablar){
    setTimeout(function(){
        console.log('Bla bla bla bla...');
        callbackHablar();
    }, 1000);
}

function adios(nombre){
    return new Promise((resolve, reject) => {
        setTimeout(function(){
            console.log('Adios '+nombre);
            resolve();
        }, 1500);
    });
}

// llamando al proceso
console.log('Iniciando el proceso...');

hola('Carlos')
    .then(adios)
    .then(nombre => {
        console.log('Terminando el proceso...');
    })