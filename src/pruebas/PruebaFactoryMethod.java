package pruebas;

import java.util.List;

import modelo.Pedido;
import modelo.Producto;
import patrones.factory.PedidoDeliveryCreator;
import patrones.factory.PedidoLocalCreator;
import patrones.factory.PedidoRecojoCreator;
import patrones.observer.Observador;

/** Pruebas simples (sin JUnit) del patron Factory Method. */
public class PruebaFactoryMethod {
    private static int fallos = 0;

    private static void verificar(String nombre, boolean condicion) {
        System.out.println((condicion ? "OK     " : "FALLO  ") + nombre);
        if (!condicion) fallos++;
    }

    private static void pruebasTipos() {
        List<Observador> ninguno = List.of();
        List<Producto> items = List.of(new Producto("Pizza", 32.0), new Producto("Gaseosa", 6.0));

        Pedido local = new PedidoLocalCreator(ninguno).registrarPedido("Ana", items);
        verificar("Local: tipo LOCAL", local.getTipo().equals("LOCAL"));
        verificar("Local: envio 0", local.calcularCostoEnvio() == 0.0);
        verificar("Local: total 38.0", local.calcularTotal() == 38.0);

        Pedido delivery = new PedidoDeliveryCreator(ninguno, "Av. Arequipa 123").registrarPedido("Luis", items);
        verificar("Delivery: tipo DELIVERY", delivery.getTipo().startsWith("DELIVERY"));
        verificar("Delivery: envio 5.0", delivery.calcularCostoEnvio() == 5.0);
        verificar("Delivery: total 43.0", delivery.calcularTotal() == 43.0);

        Pedido recojo = new PedidoRecojoCreator(ninguno).registrarPedido("Marta", items);
        verificar("Recojo: tipo RECOJO", recojo.getTipo().equals("RECOJO"));
        verificar("Recojo: envio 0", recojo.calcularCostoEnvio() == 0.0);
    }

    public static void main(String[] args) {
        pruebasTipos();
        if (fallos > 0) {
            System.out.println("Pruebas con fallos: " + fallos);
            System.exit(1);
        }
        System.out.println("Todas las pruebas pasaron");
    }
}
