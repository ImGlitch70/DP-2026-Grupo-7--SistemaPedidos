package patrones.factory;

import java.util.List;

import modelo.Pedido;
import modelo.PedidoLocal;
import modelo.Producto;
import patrones.observer.Observador;

/** PATRON FACTORY METHOD - rol: ConcreteCreator (fabrica PedidoLocal). */
public class PedidoLocalCreator extends PedidoCreator {
    public PedidoLocalCreator(List<Observador> observadores) { super(observadores); }

    @Override
    protected Pedido crearPedido(String cliente, List<Producto> items) {
        return new PedidoLocal(cliente, items);
    }
}
