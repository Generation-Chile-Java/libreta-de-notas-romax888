import java.util.*;

public class LibretaDeNotas {
    private HashMap<String, ArrayList<Double>> calificaciones;
    private Scanner scanner;

    public LibretaDeNotas() {
        calificaciones = new HashMap<>();
        scanner = new Scanner(System.in);
    }

    public void ingresarDatos() {
        System.out.print("Ingrese la cantidad de alumnos: ");
        int cantidadAlumnos = scanner.nextInt();
        while (cantidadAlumnos <= 0) {
            System.out.print("Cantidad inválida. Ingrese un número positivo: ");
            cantidadAlumnos = scanner.nextInt();
        }

        System.out.print("Ingrese la cantidad de notas por alumno: ");
        int cantidadNotas = scanner.nextInt();
        while (cantidadNotas <= 0) {
            System.out.print("Cantidad inválida. Ingrese un número positivo: ");
            cantidadNotas = scanner.nextInt();
        }

        scanner.nextLine(); // limpiar buffer

        for (int i = 0; i < cantidadAlumnos; i++) {
            System.out.print("Ingrese el nombre del alumno: ");
            String nombre = scanner.nextLine();
            ArrayList<Double> notas = new ArrayList<>();

            for (int j = 0; j < cantidadNotas; j++) {
                System.out.printf("Ingrese la nota %d para %s: ", j + 1, nombre);
                double nota = scanner.nextDouble();
                while (nota < 1.0 || nota > 7.0) {
                    System.out.print("Nota inválida. Ingrese una nota entre 1.0 y 7.0: ");
                    nota = scanner.nextDouble();
                }
                notas.add(nota);
            }
            scanner.nextLine(); // limpiar buffer
            calificaciones.put(nombre, notas);
        }
    }

    public void mostrarPromedios() {
        for (Map.Entry<String, ArrayList<Double>> entry : calificaciones.entrySet()) {
            String alumno = entry.getKey();
            ArrayList<Double> notas = entry.getValue();
            double promedio = calcularPromedio(notas);
            double max = Collections.max(notas);
            double min = Collections.min(notas);
            System.out.printf("%s -> Promedio: %.2f, Máxima: %.2f, Mínima: %.2f\n", alumno, promedio, max, min);
        }
    }

    private double calcularPromedio(ArrayList<Double> notas) {
        double suma = 0;
        for (double nota : notas) {
            suma += nota;
        }
        return suma / notas.size();
    }

    private double calcularPromedioCurso() {
        double sumaTotal = 0;
        int cantidadNotas = 0;
        for (ArrayList<Double> notas : calificaciones.values()) {
            for (double nota : notas) {
                sumaTotal += nota;
                cantidadNotas++;
            }
        }
        return sumaTotal / cantidadNotas;
    }

    public void menu() {
        int opcion;
        do {
            System.out.println("\nMenú de Opciones:");
            System.out.println("1. Mostrar Promedio de Notas por Estudiante");
            System.out.println("2. Verificar si una Nota es Aprobatoria o Reprobatoria");
            System.out.println("3. Verificar si una Nota está por Sobre o Bajo el Promedio del Curso");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // limpiar buffer

            switch (opcion) {
                case 1:
                    mostrarPromedios();
                    break;
                case 2:
                    System.out.print("Ingrese el nombre del estudiante: ");
                    String nombre = scanner.nextLine();
                    if (calificaciones.containsKey(nombre)) {
                        System.out.print("Ingrese la nota a evaluar: ");
                        double nota = scanner.nextDouble();
                        if (nota >= 4.0) {
                            System.out.println("Nota Aprobatoria.");
                        } else {
                            System.out.println("Nota Reprobatoria.");
                        }
                    } else {
                        System.out.println("Estudiante no encontrado.");
                    }
                    scanner.nextLine();
                    break;
                case 3:
                    System.out.print("Ingrese el nombre del estudiante: ");
                    String estudiante = scanner.nextLine();
                    if (calificaciones.containsKey(estudiante)) {
                        System.out.print("Ingrese la nota a comparar: ");
                        double nota = scanner.nextDouble();
                        double promedioCurso = calcularPromedioCurso();
                        if (nota >= promedioCurso) {
                            System.out.println("Nota sobre el promedio del curso.");
                        } else {
                            System.out.println("Nota bajo el promedio del curso.");
                        }
                    } else {
                        System.out.println("Estudiante no encontrado.");
                    }
                    scanner.nextLine();
                    break;
                case 0:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción inválida. Intente nuevamente.");
            }
        } while (opcion != 0);
    }

    public static void main(String[] args) {
        LibretaDeNotas app = new LibretaDeNotas();
        app.ingresarDatos();
        app.menu();
    }
}
