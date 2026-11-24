package patrones.observer;

import modelo.EstadoPedido;
import modelo.Pedido;

/** PATRON OBSERVER - rol: ConcreteObserver. Muestra en cocina los pedidos que deben prepararse. */
public class PanelCocina implements Observador {
    @Override
    public void actualizar(Pedido pedido) {
        if (pedido.getEstado() == EstadoPedido.CREADO) {
            System.out.println("  [Cocina] Nuevo pedido " + pedido.getTipo() + " #" + pedido.getId());
        }
    }
}
