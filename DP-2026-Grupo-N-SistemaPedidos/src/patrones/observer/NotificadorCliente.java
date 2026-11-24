package patrones.observer;

import modelo.Pedido;

/** PATRON OBSERVER - rol: ConcreteObserver. Avisa al cliente cada cambio de estado. */
public class NotificadorCliente implements Observador {
    @Override
    public void actualizar(Pedido pedido) {
        System.out.println("  [Notificador] Hola " + pedido.getCliente()
                + ", tu pedido #" + pedido.getId() + " ahora esta: " + pedido.getEstado());
    }
}
