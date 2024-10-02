# dar formato a un string, clase 6.1
nombre = "Ariel"
edad = 26
# mensaje_con_formato = 'Mi nombre es %s'%nombre
mensaje_con_formato = 'Mi nombre es %s y tengo %d años'% (nombre, edad)

# cramos una tupla, 6.2
persona = ('Carla', 'Gomez', 5000)
mensaje_con_formato = 'Hola %s %s .tu sueldo es %.2f'# % persona, aqui le pasamos el objeto
print(mensaje_con_formato % persona)
