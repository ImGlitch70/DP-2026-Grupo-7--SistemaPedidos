package patrones.factory;

import java.util.List;

import modelo.Pedido;
import modelo.Producto;
import patrones.observer.Observador;

/**
 * PATRON FACTORY METHOD - rol: Creator.
 * Define crearPedido() (el factory method) y el flujo comun de registro,
 * que tambien suscribe los observadores (une Factory Method + Observer).
 */
public abstract class PedidoCreator {
    private final List<Observador> observadores;

    protected PedidoCreator(List<Observador> observadores) {
        this.observadores = observadores;
    }

    /** Factory Method: cada subclase decide que tipo de Pedido crear. */
    protected abstract Pedido crearPedido(String cliente, List<Producto> items);

    public Pedido registrarPedido(String cliente, List<Producto> items) {
        if (cliente == null || cliente.isBlank()) {
            throw new IllegalArgumentException("El cliente es obligatorio");
        }
        if (items == null || items.isEmpty()) {
            throw new IllegalArgumentException("El pedido debe tener al menos un producto");
        }
        Pedido pedido = crearPedido(cliente, items);
        observadores.forEach(pedido::agregarObservador);
        pedido.notificarObservadores();
        return pedido;
    }
}
