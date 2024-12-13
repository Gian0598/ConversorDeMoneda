package principal;

import api.conversorApi;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        conversorApi api = new conversorApi();

        while (true) {
            System.out.println("*************************************");
            System.out.println("Sea Bienvenido al conversor de moneda");
            System.out.println("1) Dolar =>>> Peso Argentino");
            System.out.println("2) Peso Argentino =>>> Dolar");
            System.out.println("3) Dolar =>>> Real Brasileño");
            System.out.println("4) Real Brasileño =>>> Dolar");
            System.out.println("5) Dolar =>>> Peso Colombiano");
            System.out.println("6) Peso Colombiano =>>> Dolar");
            System.out.println("7) SALIR");

            int opcion = 0;

            try {
                opcion = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Opción inválida. Por favor, ingrese un número.");
                scanner.next(); // Limpiar la entrada inválida
                continue;
            }

            if (opcion == 7) {
                System.out.println("¡Gracias por usar el conversor de moneda!");
                break;
            } else if (opcion < 1 || opcion > 7) {
                System.out.println("Opción no válida. Intente de nuevo.");
                continue;
            }

            System.out.print("Ingrese la cantidad a convertir: ");
            double cantidad = 0;

            try {
                cantidad = scanner.nextDouble();
            } catch (InputMismatchException e) {
                System.out.println("Cantidad inválida. Por favor, ingrese un número válido.");
                scanner.next(); // Limpiar la entrada inválida
                continue;
            }

            String monedaOrigen = "";
            String monedaDestino = "";

            switch (opcion) {
                case 1 -> {
                    monedaOrigen = "USD";
                    monedaDestino = "ARS";
                }
                case 2 -> {
                    monedaOrigen = "ARS";
                    monedaDestino = "USD";
                }
                case 3 -> {
                    monedaOrigen = "USD";
                    monedaDestino = "BRL";
                }
                case 4 -> {
                    monedaOrigen = "BRL";
                    monedaDestino = "USD";
                }
                case 5 -> {
                    monedaOrigen = "USD";
                    monedaDestino = "COP";
                }
                case 6 -> {
                    monedaOrigen = "COP";
                    monedaDestino = "USD";
                }
                default -> {
                    System.out.println("Opción no válida. Intente de nuevo.");
                    continue;
                }
            }

            try {
                double tasaCambio = api.obtenerTasaCambio(monedaOrigen, monedaDestino);
                double resultado = cantidad * tasaCambio;
                System.out.printf("El valor " + cantidad + "[%s] corresponde al valor final de => " + resultado + "[%s]%n", monedaOrigen, monedaDestino);
                System.out.println("*************************************");
            } catch (IOException | InterruptedException e) {
                System.out.println("Error al obtener la tasa de cambio: " + e.getMessage());
            }
        }
        scanner.close();
    }
}
