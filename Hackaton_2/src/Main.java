import hackaton.Agenda;
import hackaton.Contacto;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Inicialización del Scanner para entrada de usuario
        Scanner scan = new Scanner(System.in);  // Create a Scanner object
        Agenda a = null;

        // Configuración inicial del tamaño de la agenda
        System.out.println("Bienvenido al sistema de Agendas! \n¿De cuántos contactos quiere su agenda? Presione 0 para tamaño por defecto [10]");
        int tamaño = scan.nextInt();
        scan.nextLine(); // Limpiar el buffer del scanner

        // Creación de la agenda según la elección del usuario
        if (tamaño == 0) {
            a = new Agenda(10);  // Si el usuario presiona 0, se usa tamaño por defecto (10)
        } else {
            a = new Agenda(tamaño);  // Si elige otro tamaño, se usa ese tamaño
        }

        int opc; // Variable para almacenar la opción del menú

        // Menú principal en loop hasta que el usuario elija salir (opción 0)
        do {
            System.out.println("¿Qué desea hacer? \n1.-Añadir contacto \n2.-Listar contacto \n3.-Buscar contacto \n4.-Eliminar contacto \n5.-Verificar agenda llena \n6.-Espacios libres \n7.-Editar numero de telefono \n8.-Validar que un usuario coincida con la Agenda \n0.-Salir");
            opc = scan.nextInt();
            scan.nextLine(); // Limpiar el buffer del scanner

            switch(opc) {
                case 1: // Añadir nuevo contacto
                    String nombre;
                    String apellido;
                    String telefono;

                    // Validación del nombre (no debe contener espacios)
                    do {
                        System.out.println("Qué nombre tiene el contacto? ");
                        nombre = scan.nextLine();
                        if (nombre.contains(" ")){
                            System.out.println("El nombre no debe contener espacios. Inténtalo de nuevo.");
                        }
                    } while(nombre.contains(" "));

                    // Validación del apellido (no debe contener espacios)
                    do {
                        System.out.println("Qué apellido tiene el contacto? ");
                        apellido = scan.nextLine();
                        if (apellido.contains(" ")){
                            System.out.println("El apellido no debe contener espacios. Inténtalo de nuevo.");
                        }
                    } while(apellido.contains(" "));

                    // Verificar si el contacto ya existe
                    if (a.validarDuplicado(nombre,apellido)){
                        //System.out.println("Usuario Duplicado! intente de nuevo");
                        break;
                    }

                    // Validación del teléfono (no debe contener espacios)
                    do {
                        System.out.println("Qué número de teléfono tiene el contacto? ");
                        telefono = scan.nextLine();
                        if (telefono.contains(" ")){
                            System.out.println("El telefono no debe contener espacios. Inténtalo de nuevo.");
                        }
                    } while(telefono.contains(" "));

                    // Verificar si la agenda está llena
                    if(a.agendaLena()){
                        System.out.println("Agenda llena!!!!");
                        break;
                    }

                    // Creación y agregado del nuevo contacto
                    Contacto nuevoContacto = new Contacto(nombre,apellido,telefono);
                    a.agregarContacto(nuevoContacto);
                    break;

                case 2: // Listar todos los contactos
                    if (a.agendaVacia()){
                        System.out.println("Agenda Vacia!!!");
                    } else {
                        a.mostrarContactos();
                    }
                    break;

                case 3: // Buscar un contacto específico
                    System.out.println("Ingrese el nombre:");
                    String nombreBuscar = scan.nextLine();
                    System.out.println("Ingrese el apellido:");
                    String apellidoBuscar = scan.nextLine();
                    a.buscarContacto(nombreBuscar, apellidoBuscar);
                    break;

                case 4: // Eliminar un contacto
                    System.out.println("Ingrese el nombre del contacto a eliminar: ");
                    String nombreEliminar = scan.nextLine();
                    System.out.println("Ingrese el apellido del contacto a eliminar: ");
                    String apellidoEliminar = scan.nextLine();
                    a.eliminarContacto(nombreEliminar, apellidoEliminar);
                    break;

                case 5: // Verificar si la agenda está llena
                    if(a.agendaLena()){
                        System.out.println("Agenda llena!");
                    } else {
                        int espD = (a.getCapacidad()-a.getCantidadActual());
                        System.out.println("Cuenta con "+ espD +" Espacios disponibles");
                    }
                    break;

                case 6: // Mostrar espacios libres en la agenda
                    int espD = (a.getCapacidad()-a.getCantidadActual());
                    System.out.println("Cuenta con "+ espD +" Espacios disponibles");
                    break;

                case 7: // Editar número de teléfono de un contacto
                    System.out.println("Lista de contactos: ");
                    a.mostrarContactos();
                    System.out.println("Cuál teléfono desea modificar? ");
                    System.out.println("Ingrese el nombre:");
                    String nombreBuscart = scan.nextLine();
                    System.out.println("Ingrese el apellido:");
                    String apellidoBuscart = scan.nextLine();
                    a.modificarTelefono(nombreBuscart, apellidoBuscart);
                    break;

                case 8: // Validar si un contacto completo existe
                    System.out.println("Ingrese el nombre:");
                    String nombreBuscarCompleto = scan.nextLine();
                    System.out.println("Ingrese el apellido:");
                    String apellidoBuscarCompleto = scan.nextLine();
                    System.out.println("Ingrese el teléfono:");
                    String telefonoBuscarCompleto = scan.nextLine();
                    a.buscarContactoCompleto(nombreBuscarCompleto, apellidoBuscarCompleto, telefonoBuscarCompleto);
                    break;

                default: // Opción para salir del programa
                    System.out.println("Se ha finalizado el programa!");
            }
        } while(opc != 0); // Condición de salida del loop (opción 0)
    }
}