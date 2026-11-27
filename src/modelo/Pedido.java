package modelo;

import java.util.ArrayList;
import java.util.List;

import patrones.observer.Observador;
import patrones.observer.Sujeto;

/**
 * PATRON FACTORY METHOD - rol: Product (clase base de los pedidos que se fabrican).
 * PATRON OBSERVER       - rol: Subject (implementa Sujeto y notifica cuando cambia el estado).
 */
public abstract class Pedido implements Sujeto {
    private static int contador = 0;

    private final int id;
    private final String cliente;
    private final List<Producto> items;
    private final List<Observador> observadores = new ArrayList<>();
    private EstadoPedido estado = EstadoPedido.CREADO;

    protected Pedido(String cliente, List<Producto> items) {
        this.id = ++contador;
        this.cliente = cliente;
        this.items = new ArrayList<>(items);
    }

    /** Cada tipo de pedido calcula su propio costo de envio. */
    public abstract double calcularCostoEnvio();

    public abstract String getTipo();

    public double calcularTotal() {
        double subtotal = items.stream().mapToDouble(Producto::getPrecio).sum();
        return subtotal + calcularCostoEnvio();
    }

    public void cambiarEstado(EstadoPedido nuevoEstado) {
        this.estado = nuevoEstado;
        notificarObservadores(); // Observer: avisa a todos los interesados
    }

    @Override
    public void agregarObservador(Observador observador) { observadores.add(observador); }

    @Override
    public void quitarObservador(Observador observador) { observadores.remove(observador); }

    @Override
    public void notificarObservadores() {
        for (Observador o : observadores) {
            o.actualizar(this);
        }
    }

    @Override
    public String toString() {
        return "Pedido #" + id + " [" + getTipo() + "] cliente=" + cliente + ", estado=" + estado;
    }

    public int getId() { return id; }
    public String getCliente() { return cliente; }
    public List<Producto> getItems() { return items; }
    public EstadoPedido getEstado() { return estado; }
}
