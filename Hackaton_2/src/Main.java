import hackaton.Agenda;
import hackaton.Contacto;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Inicialización del Scanner para entrada de usuario
        Scanner scan = new Scanner(System.in);  // Create a Scanner object
        Agenda a = null;
        String entrada;
        int tamaño = -1;
        // Configuración inicial del tamaño de la agenda

        System.out.println("Bienvenido al sistema de Agendas! \n¿De cuántos contactos quiere su agenda? Presione 0 para tamaño por defecto [10]");
        do {
            entrada = scan.nextLine();

            if (!entrada.matches("\\d+")) {
                System.out.println("Ingrese un tamaño válido (solo números):");
            } else {
                tamaño = Integer.parseInt(entrada);
                break;
            }
        } while (!entrada.matches("\\d+"));
        //tamaño = scan.nextInt();
        // Limpiar el buffer del scanner

        // Creación de la agenda según la elección del usuario
        if (tamaño == 0) {
            a = new Agenda(10);  // Si el usuario presiona 0, se usa tamaño por defecto (10)
        } else {
            a = new Agenda(tamaño);  // Si elige otro tamaño, se usa ese tamaño
        }
        System.out.println("El tamaño de su agenda es: " + tamaño);
        int opc; // Variable para almacenar la opción del menú

        // Menú principal en loop hasta que el usuario elija salir (opción 0)
        do {
            System.out.println("¿Qué desea hacer? \n1.-Añadir contacto \n2.-Listar contacto \n3.-Buscar contacto \n4.-Eliminar contacto \n5.-Verificar agenda llena \n6.-Espacios libres \n7.-Editar numero de telefono \n8.-Validar que un usuario coincida con la Agenda \n9.-Generar Agenda! \n0.-Salir");
            opc = scan.nextInt();
            scan.nextLine(); // Limpiar el buffer del scanner

            switch(opc) {
                case 1: // Añadir nuevo contacto
                    String nombre;
                    String apellido;
                    String telefono;
                    boolean esDuplicado;
                    // Verificar si la agenda está llena
                    if(a.agendaLena()){
                        System.out.println("Agenda llena!!!!");
                        break;
                    }
                    do {
                    // Validación del nombre (no debe contener espacios ni numeros y el telefono solo debe aceptar numeros)

                        System.out.println("Qué nombre tiene el contacto? ");
                        do {
                        nombre = scan.nextLine();
                        if (nombre.contains(" ")){
                            System.out.println("El nombre no debe contener espacios. Inténtalo de nuevo.");
                        }
                        else if (nombre.matches(".*\\d.*")) {
                            System.out.println("El nombre no debe contener números. Inténtalo de nuevo.");
                        }
                    } while(nombre.contains(" ")|| nombre.matches(".*\\d.*"));

                    // Validación del apellido (no debe contener espacios)

                        System.out.println("Qué apellido tiene el contacto? ");
                        do {
                        apellido = scan.nextLine();
                        if (apellido.contains(" ")){
                            System.out.println("El apellido no debe contener espacios. Inténtalo de nuevo.");
                        }else if (apellido.matches(".*\\d.*")) {
                            System.out.println("El apellido no debe contener números. Inténtalo de nuevo.");
                        }
                    } while (apellido.contains(" ") || apellido.matches(".*\\d.*"));

                        // Validar duplicado después de tener nombre y apellido
                        esDuplicado = a.validarDuplicado(nombre, apellido);
                        if (esDuplicado) {
                            System.out.println("Ese contacto ya existe en la agenda, intenta de nuevo con uno diferente.\n");
                        }

                    } while (esDuplicado);
                    // Verificar si el contacto ya existe


                    // Validación del teléfono (no debe contener espacios)

                        System.out.println("¿Qué número de teléfono tiene el contacto?");
                    do {
                        telefono = scan.nextLine();
                        if (telefono.contains(" ")) {
                            System.out.println("El teléfono no debe contener espacios. Inténtalo de nuevo.");
                        } else if (!telefono.matches("\\d+")) {
                            System.out.println("El teléfono debe contener solo números. Inténtalo de nuevo.");
                        }
                    } while (telefono.contains(" ") || !telefono.matches("\\d+"));



                    // Creación y agregado del nuevo contacto
                    Contacto nuevoContacto = new Contacto(nombre, apellido, telefono);
                    a.agregarContacto(nuevoContacto);

                    //System.out.println("¡Contacto agregado exitosamente!");
                    break;

                case 2: // Listar todos los contactos
                    if (a.agendaVacia()){
                        System.out.println("Agenda Vacia!!!");
                    } else {
                        a.mostrarContactos();
                    }
                    break;

                case 3: // Buscar un contacto específico
                    String nombreBuscar;
                    String apellidoBuscar;

                    System.out.println("Ingrese el nombre:");
                    do{
                    nombreBuscar = scan.nextLine();
                        if (nombreBuscar.contains(" ")){
                            System.out.println("El nombre no debe contener espacios. Inténtalo de nuevo.");
                        }
                        else if (nombreBuscar.matches(".*\\d.*")) {
                            System.out.println("El nombre no debe contener números. Inténtalo de nuevo.");
                        }
                    } while(nombreBuscar.contains(" ")|| nombreBuscar.matches(".*\\d.*"));

                        System.out.println("Ingrese el apellido:");
                    do{
                        apellidoBuscar = scan.nextLine();
                        if (apellidoBuscar.contains(" ")){
                            System.out.println("El apellido no debe contener espacios. Inténtalo de nuevo.");
                        }
                        else if (apellidoBuscar.matches(".*\\d.*")) {
                            System.out.println("El apellido no debe contener números. Inténtalo de nuevo.");
                        }
                    } while(apellidoBuscar.contains(" ")|| apellidoBuscar.matches(".*\\d.*"));
                    a.buscarContacto(nombreBuscar, apellidoBuscar);
                    break;

                case 4: // Eliminar un contacto
                    String nombreEliminar;
                    String apellidoEliminar;

                        System.out.println("Ingrese el nombre:");
                    do{
                        nombreEliminar = scan.nextLine();
                        if (nombreEliminar.contains(" ")){
                            System.out.println("El nombre no debe contener espacios. Inténtalo de nuevo.");
                        }
                        else if (nombreEliminar.matches(".*\\d.*")) {
                            System.out.println("El nombre no debe contener números. Inténtalo de nuevo.");
                        }
                    } while(nombreEliminar.contains(" ")|| nombreEliminar.matches(".*\\d.*"));

                        System.out.println("Ingrese el apellido:");
                    do{
                        apellidoEliminar = scan.nextLine();
                        if (apellidoEliminar.contains(" ")){
                            System.out.println("El apellido no debe contener espacios. Inténtalo de nuevo.");
                        }
                        else if (apellidoEliminar.matches(".*\\d.*")) {
                            System.out.println("El apellido no debe contener números. Inténtalo de nuevo.");
                        }
                    } while(apellidoEliminar.contains(" ")|| apellidoEliminar.matches(".*\\d.*"));
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
                    String nombreBuscart;
                    String apellidoBuscart;
                    System.out.println("Lista de contactos: ");
                    a.mostrarContactos();
                    System.out.println("Cuál teléfono desea modificar? ");

                        System.out.println("Ingrese el nombre:");
                    do{
                        nombreBuscart = scan.nextLine();
                        if (nombreBuscart.contains(" ")){
                            System.out.println("El nombre no debe contener espacios. Inténtalo de nuevo.");
                        }
                        else if (nombreBuscart.matches(".*\\d.*")) {
                            System.out.println("El nombre no debe contener números. Inténtalo de nuevo.");
                        }
                    } while(nombreBuscart.contains(" ")|| nombreBuscart.matches(".*\\d.*"));

                        System.out.println("Ingrese el apellido:");
                    do{
                        apellidoBuscart = scan.nextLine();
                        if (apellidoBuscart.contains(" ")){
                            System.out.println("El apellido no debe contener espacios. Inténtalo de nuevo.");
                        }
                        else if (apellidoBuscart.matches(".*\\d.*")) {
                            System.out.println("El apellido no debe contener números. Inténtalo de nuevo.");
                        }
                    } while(apellidoBuscart.contains(" ")|| apellidoBuscart.matches(".*\\d.*"));
                    a.modificarTelefono(nombreBuscart, apellidoBuscart);
                    break;

                case 8: // Validar si un contacto completo existe
                    String nombreBuscarCompleto;
                    String apellidoBuscarCompleto;
                    String telefonoBuscarCompleto;
                    System.out.println("Ingrese el nombre:");
                    do{
                        nombreBuscarCompleto = scan.nextLine();
                        if (nombreBuscarCompleto.contains(" ")){
                            System.out.println("El nombre no debe contener espacios. Inténtalo de nuevo.");
                        }
                        else if (nombreBuscarCompleto.matches(".*\\d.*")) {
                            System.out.println("El nombre no debe contener números. Inténtalo de nuevo.");
                        }
                    } while(nombreBuscarCompleto.contains(" ")|| nombreBuscarCompleto.matches(".*\\d.*"));
                    System.out.println("Ingrese el apellido:");
                    do{
                        apellidoBuscarCompleto = scan.nextLine();
                        if (apellidoBuscarCompleto.contains(" ")){
                            System.out.println("El apellido no debe contener espacios. Inténtalo de nuevo.");
                        }
                        else if (apellidoBuscarCompleto.matches(".*\\d.*")) {
                            System.out.println("El apellido no debe contener números. Inténtalo de nuevo.");
                        }
                    } while(apellidoBuscarCompleto.contains(" ")|| apellidoBuscarCompleto.matches(".*\\d.*"));

                    System.out.println("Ingrese el teléfono:");
                    do {
                        telefonoBuscarCompleto = scan.nextLine();
                        if (telefonoBuscarCompleto.contains(" ")) {
                            System.out.println("El teléfono no debe contener espacios. Inténtalo de nuevo.");
                        } else if (!telefonoBuscarCompleto.matches("\\d+")) {
                            System.out.println("El teléfono debe contener solo números. Inténtalo de nuevo.");
                        }
                    } while (telefonoBuscarCompleto.contains(" ") || !telefonoBuscarCompleto.matches("\\d+"));
                    a.buscarContactoCompleto(nombreBuscarCompleto, apellidoBuscarCompleto, telefonoBuscarCompleto);
                    break;
                case 9: // Generar HTML con la agenda
                    try {
                        Contacto[] contactos = a.getContactos(); // Asegúrate de tener este método en tu clase Agenda
                        StringBuilder html = new StringBuilder();
                        html.append("<!DOCTYPE html>\n");
                        html.append("<html lang='es'>\n<head>\n<meta charset='UTF-8'>\n<title>Agenda de Contactos</title>\n");
                        html.append("<style>table { border-collapse: collapse; width: 80%; margin: 20px auto; } th, td { border: 1px solid #ccc; padding: 10px; text-align: center; } th { background-color: #f2f2f2; }</style>\n");
                        html.append("</head>\n<body>\n<h1 style='text-align:center;'>Agenda de Contactos</h1>\n<table>\n<tr><th>Nombre</th><th>Apellido</th><th>Teléfono</th></tr>\n");

                        for (Contacto c : contactos) {
                            if (c != null) {
                                html.append("<tr><td>").append(c.getNombre()).append("</td><td>")
                                        .append(c.getApellido()).append("</td><td>")
                                        .append(c.getTelefono()).append("</td></tr>\n");
                            }
                        }

                        html.append("</table>\n</body>\n</html>");

                        // Guardar en archivo
                        FileWriter fileWriter = new FileWriter("agenda.html");
                        fileWriter.write(html.toString());
                        fileWriter.close();

                        System.out.println("¡Se ha generado el archivo agenda.html exitosamente!");

                    } catch (IOException e) {
                        System.out.println("Error al generar el archivo HTML: " + e.getMessage());
                    }
                    break;
                default: // Opción para salir del programa
                    System.out.println("Se ha finalizado el programa!");
            }
        } while(opc != 0); // Condición de salida del loop (opción 0)
    }
}