package patrones.factory;

import java.util.List;

import modelo.Pedido;
import modelo.PedidoDelivery;
import modelo.Producto;
import patrones.observer.Observador;

/** PATRON FACTORY METHOD - rol: ConcreteCreator (fabrica PedidoDelivery). */
public class PedidoDeliveryCreator extends PedidoCreator {
    private final String direccion;

    public PedidoDeliveryCreator(List<Observador> observadores, String direccion) {
        super(observadores);
        this.direccion = direccion;
    }

    @Override
    protected Pedido crearPedido(String cliente, List<Producto> items) {
        return new PedidoDelivery(cliente, items, direccion);
    }
}
