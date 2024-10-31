// console.log() // Muestra en consola el mensaje que se le pase como argumento

// console.error() // Muestra en consola un mensaje de error

// setTimeout(() => {}) // Ejecuta una función después de un tiempo determinado

// setInterval(() => {}) // Ejecuta una función cada cierto tiempo

// setImmdiate(() => {}) // Ejecuta una función al final de la ejecución del event loop actual

// console.log(global) // Muestra en consola un objeto con todas las variables globales

// console.log(setInterval) // Muestra en consola la función setInterval

// let i = 0;
// let intervalo = setInterval(() => {
//     console.log('Hola');
//     if(i === 3){
//         clearInterval(intervalo);
//     }
//     i++;
// }, 1000);

setInmmidiate(() => {
    console.log('Hola');
});

// require();

console.log(__filename);

global.miVariable = 'elValor';
console.log(miVariable);