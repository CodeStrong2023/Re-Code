
class Animal:
    def __init__(self, nombre):
        self.nombre = nombre

    def comer(self):
        return f"{self.nombre} está comiendo."

    def hacer_sonido(self):
        return f"{self.nombre} está haciendo un sonido."

    def moverse(self):
        return f"{self.nombre} se está moviendo."


class Perro(Animal):
    def hacer_sonido(self):
        return f"{self.nombre} dice: ¡Guau!"

    def moverse(self):
        return f"{self.nombre} está corriendo."


class Gato(Animal):
    def hacer_sonido(self):
        return f"{self.nombre} dice: ¡Miau!"

    def moverse(self):
        return f"{self.nombre} está caminando sigilosamente."


class Vaca(Animal):
    def hacer_sonido(self):
        return f"{self.nombre} dice: ¡Muu!"

    def moverse(self):
        return f"{self.nombre} está caminando lentamente."


class Pajaro(Animal):
    def hacer_sonido(self):
        return f"{self.nombre} dice: ¡Pío pío!"

    def mover(self):
        return f"{self.nombre} está volando."




def mostrar_comportamientos(animal):
    print(animal.comer())
    print(animal.hacer_sonido())
    print(animal.moverse())


if __name__ == "__main__":
    animales = [
        Perro("homero"),
        Gato("mishi"),
        Vaca("Lala"),
        Pajaro("colapinto"),
    ]

    for animal in animales:
        print(f"\nComportamientos de {animal.nombre}:")
        mostrar_comportamientos(animal)