package modelo;

import java.util.List;

/** PATRON FACTORY METHOD - rol: ConcreteProduct (pedido enviado a domicilio). */
public class PedidoDelivery extends Pedido {
    private static final double COSTO_ENVIO = 5.0;
    private final String direccion;

    public PedidoDelivery(String cliente, List<Producto> items, String direccion) {
        super(cliente, items);
        this.direccion = direccion;
    }

    @Override public double calcularCostoEnvio() { return COSTO_ENVIO; }
    @Override public String getTipo() { return "DELIVERY (" + direccion + ")"; }
}
