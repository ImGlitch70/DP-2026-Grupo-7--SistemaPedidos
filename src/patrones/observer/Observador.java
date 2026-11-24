package patrones.observer;

import modelo.Pedido;

/**
 * PATRON OBSERVER - rol: Observer.
 * Contrato que cumplen todos los que quieren enterarse de cambios en un Pedido.
 */
public interface Observador {
    void actualizar(Pedido pedido);
}
