function hola(nombre){
    return new Promise(function(resolve, reject){
        setTimeout(function(){
            console.log('Hola '+nombre);
            miCallback();
        }, 1000);
    })
}

function hablar(nombre){
    return new Promise((resolve, reject) => {
        setTimeout(function(){
            console.log('Bla bla bla bla...');
            resolve(nombre);
        }, 1000);
    });
}

function adios(nombre){
    return new Promise((resolve, reject) => {
        setTimeout(function(){
            console.log('Adios '+nombre);
            // resolve();
            reject('Hay un error');
        }, 1500);
    });
}

// llamando al proceso
console.log('Iniciando el proceso...');

hola('Carlos')
    .then(hablar)
    .then(adios)
    .then(nombre => {
        console.log('Terminando el proceso...');
    })
    .catch(error => {
        console.error('Ha habido un error');
        console.error(error);
    })