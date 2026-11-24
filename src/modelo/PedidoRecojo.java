package modelo;

import java.util.List;

/** PATRON FACTORY METHOD - rol: ConcreteProduct (el cliente recoge su pedido). */
public class PedidoRecojo extends Pedido {
    public PedidoRecojo(String cliente, List<Producto> items) { super(cliente, items); }

    @Override public double calcularCostoEnvio() { return 0.0; }
    @Override public String getTipo() { return "RECOJO"; }
}
