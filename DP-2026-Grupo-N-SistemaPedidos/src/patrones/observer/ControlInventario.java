package patrones.observer;

import modelo.EstadoPedido;
import modelo.Pedido;

/** PATRON OBSERVER - rol: ConcreteObserver. Descuenta stock cuando el pedido pasa a preparacion. */
public class ControlInventario implements Observador {
    @Override
    public void actualizar(Pedido pedido) {
        if (pedido.getEstado() == EstadoPedido.EN_PREPARACION) {
            System.out.println("  [Inventario] Descontando stock de " + pedido.getItems().size()
                    + " producto(s) del pedido #" + pedido.getId());
        }
    }
}
