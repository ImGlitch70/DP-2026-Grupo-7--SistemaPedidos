import java.util.Arrays;
import java.util.List;

import modelo.EstadoPedido;
import modelo.Pedido;
import modelo.Producto;
import patrones.factory.PedidoCreator;
import patrones.factory.PedidoDeliveryCreator;
import patrones.factory.PedidoLocalCreator;
import patrones.observer.ControlInventario;
import patrones.observer.NotificadorCliente;
import patrones.observer.Observador;
import patrones.observer.PanelCocina;

/** Demo: Main -> Creator (Factory Method) -> Pedido (Subject) -> Observadores. */
public class Main {
    public static void main(String[] args) {
        List<Observador> observadores =
                Arrays.asList(new NotificadorCliente(), new ControlInventario(), new PanelCocina());
        List<Producto> items = Arrays.asList(new Producto("Pizza", 32.0), new Producto("Gaseosa", 6.0));

        PedidoCreator local = new PedidoLocalCreator(observadores);
        PedidoCreator delivery = new PedidoDeliveryCreator(observadores, "Av. Arequipa 123");

        System.out.println("=== Pedido en local ===");
        Pedido p1 = local.registrarPedido("Ana", items);
        p1.cambiarEstado(EstadoPedido.EN_PREPARACION);
        p1.cambiarEstado(EstadoPedido.LISTO);
        System.out.println("  Total: S/ " + p1.calcularTotal());

        System.out.println("=== Pedido delivery ===");
        Pedido p2 = delivery.registrarPedido("Luis", items);
        p2.cambiarEstado(EstadoPedido.EN_PREPARACION);
        p2.cambiarEstado(EstadoPedido.ENTREGADO);
        System.out.println("  Total: S/ " + p2.calcularTotal());
    }
}
