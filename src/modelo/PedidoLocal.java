package modelo;

import java.util.List;

/** PATRON FACTORY METHOD - rol: ConcreteProduct (pedido para consumir en el local). */
public class PedidoLocal extends Pedido {
    public PedidoLocal(String cliente, List<Producto> items) { super(cliente, items); }

    @Override public double calcularCostoEnvio() { return 0.0; }
    @Override public String getTipo() { return "LOCAL"; }
}
