package patrones.factory;

import java.util.List;

import modelo.Pedido;
import modelo.PedidoRecojo;
import modelo.Producto;
import patrones.observer.Observador;

/** PATRON FACTORY METHOD - rol: ConcreteCreator (fabrica PedidoRecojo). */
public class PedidoRecojoCreator extends PedidoCreator {
    public PedidoRecojoCreator(List<Observador> observadores) { super(observadores); }

    @Override
    protected Pedido crearPedido(String cliente, List<Producto> items) {
        return new PedidoRecojo(cliente, items);
    }
}
