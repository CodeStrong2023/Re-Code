function hola(nombre, miCallback){
    setTimeout(function(){
        console.log('Hola '+nombre);
        miCallback();
    }, 1000);
}

function hablar(callbackHablar){
    setTimeout(function(){
        console.log('Bla bla bla bla...');
        callbackHablar();
    }, 1000);
}

function adios(nombre, otroCallback){
    setTimeout(function(){
        console.log('Adios '+nombre);
        otroCallback();
    }, 1500);
}

function conversacion(nombre, veces, callback){
    hablar(function(){
        conversacion();
    })
}

// --Proceso principal
console.log('Iniciando proceso...');
hola('Carlos', adios);

// hablar('Carlos', function(){
//     hablar(function(){
//         hablar(function(){
//             hablar(function(){
//                 adios(nombre, function(){
//                     console.log('Terminando proceso...');
//                 });
//             });
//         });
//     });
// });