import hackaton.Agenda;
import hackaton.Contacto;

import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);  // Create a Scanner object
        Agenda a = null;

            System.out.println("Bienvenido al sistema de Agendas! de cuántos contactos quiere su agenda? Presione 0 para tamaño por defecto [10]");
            int tamaño = scan.nextInt();
            scan.nextLine();
            if (tamaño == 0) {
                a = new Agenda(10);  // Si el usuario presiona 0, se usa tamaño por defecto (10)
            } else {
                a = new Agenda(tamaño);  // Si elige otro tamaño, se usa ese tamaño
            }
            int opc;
        do {
        System.out.println("Bienvenido al sistema! Qué desea hacer? 1.- Añadir contacto 2.-Listar contacto 3.-Buscar contacto 5.-Verificar agenda llena 6.-Espacios libres 0.-Salir");
         opc = scan.nextInt();
            scan.nextLine();
        switch(opc) {
            case 1:
                String nombre;
                String apellido;
                String telefono;
                do {
                    System.out.println("Qué nombre tiene el contacto? ");
                    nombre = scan.nextLine();
                    if (nombre.contains(" ")){
                        System.out.println("El nombre no debe contener espacios. Inténtalo de nuevo.");
                    }
                }while(nombre.contains(" "));
                do {
                    System.out.println("Qué apellido tiene el contacto? ");
                    apellido = scan.nextLine();
                    if (apellido.contains(" ")){
                        System.out.println("El apellido no debe contener espacios. Inténtalo de nuevo.");
                    }
                }while(apellido.contains(" "));
                do {
                    System.out.println("Qué número de teléfono tiene el contacto? ");
                    telefono = scan.nextLine();
                    if (telefono.contains(" ")){
                        System.out.println("El telefono no debe contener espacios. Inténtalo de nuevo.");
                    }
                }while(telefono.contains(" "));
                Contacto nuevoContacto = new Contacto(nombre,apellido,telefono);
                if(a.agendaLena()){
                    System.out.println("Agenda llena!!!!");
                    break;
                }
                a.agregarContacto(nuevoContacto);

                break;
            case 2:
                a.mostrarContactos();
                break;

            case 3:
                System.out.println("Ingrese el nombre:");
                String nombreBuscar = scan.nextLine();
                System.out.println("Ingrese el apellido:");
                String apellidoBuscar = scan.nextLine();
                a.buscarContacto(nombreBuscar, apellidoBuscar);
                break;

            case 4:
                System.out.println("i es tres.");
                break;
            case 5:
                if(a.agendaLena()){
                    System.out.println("Agenda llena!");
                } else {
                    int espD = (a.getCapacidad()-a.getCantidadActual());
                    System.out.println("Cuenta con "+ espD +" Espacios disponibles");
                }
                break;
            case 6:
                int espD = (a.getCapacidad()-a.getCantidadActual());
                System.out.println("Cuenta con "+ espD +" Espacios disponibles");
                break;
            default:
                System.out.println("Se ha finalizado el programa!");
        }
        }while(opc != 0);
        }
    }