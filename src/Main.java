import java.util.Arrays;
import java.util.List;

import modelo.EstadoPedido;
import modelo.Pedido;
import modelo.Producto;
import patrones.factory.PedidoCreator;
import patrones.factory.PedidoDeliveryCreator;
import patrones.factory.PedidoLocalCreator;
import patrones.factory.PedidoRecojoCreator;
import patrones.observer.ControlInventario;
import patrones.observer.NotificadorCliente;
import patrones.observer.Observador;
import patrones.observer.PanelCocina;
import patrones.observer.RegistroAuditoria;

/** Demo: Main -> Creator (Factory Method) -> Pedido (Subject) -> Observadores. */
public class Main {
    public static void main(String[] args) {
        RegistroAuditoria auditoria = new RegistroAuditoria();
        List<Observador> observadores = Arrays.asList(
                new NotificadorCliente(), new ControlInventario(), new PanelCocina(), auditoria);
        List<Producto> items = Arrays.asList(new Producto("Pizza", 32.0), new Producto("Gaseosa", 6.0));

        // Factory Method: tres creators, tres tipos de pedido
        PedidoCreator local = new PedidoLocalCreator(observadores);
        PedidoCreator delivery = new PedidoDeliveryCreator(observadores, "Av. Arequipa 123");
        PedidoCreator recojo = new PedidoRecojoCreator(observadores);

        System.out.println("=== 1. Pedido en local ===");
        Pedido p1 = local.registrarPedido("Ana", items);
        p1.cambiarEstado(EstadoPedido.EN_PREPARACION);
        p1.cambiarEstado(EstadoPedido.LISTO);
        System.out.println("  Total: S/ " + p1.calcularTotal());

        System.out.println("=== 2. Pedido delivery ===");
        Pedido p2 = delivery.registrarPedido("Luis", items);
        p2.cambiarEstado(EstadoPedido.EN_PREPARACION);
        p2.cambiarEstado(EstadoPedido.ENTREGADO);
        System.out.println("  Total: S/ " + p2.calcularTotal());

        System.out.println("=== 3. Pedido para recoger ===");
        Pedido p3 = recojo.registrarPedido("Marta", items);
        p3.cambiarEstado(EstadoPedido.LISTO);
        System.out.println("  Total: S/ " + p3.calcularTotal());

        System.out.println("=== 4. Observer: quitar un observador en ejecucion ===");
        Pedido p4 = local.registrarPedido("Pedro", items);
        p4.quitarObservador(observadores.get(2)); // se desuscribe PanelCocina
        p4.cambiarEstado(EstadoPedido.EN_PREPARACION);
        System.out.println("  (PanelCocina ya no recibe avisos de #" + p4.getId() + ")");

        System.out.println("=== 5. Validaciones del Creator ===");
        try {
            local.registrarPedido("Sofia", List.of());
        } catch (IllegalArgumentException e) {
            System.out.println("  Error controlado: " + e.getMessage());
        }

        System.out.println("=== 6. Historial de auditoria (ConcreteObserver) ===");
        auditoria.getRegistros().forEach(r -> System.out.println("  " + r));
    }
}
