function soyAsincrona(){
    setTimeout(function(miCallback){
        console.log('Hola, soy una función asíncrona');
    }, 1000);
}

console.log('Iniciando proceso...');
soyAsincrona(function(miCallback){
    console.log('Terminando proceso...');
});