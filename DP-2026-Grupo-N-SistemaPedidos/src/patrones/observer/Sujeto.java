package patrones.observer;

/**
 * PATRON OBSERVER - rol: Subject.
 * Contrato de quien mantiene la lista de observadores y los notifica.
 */
public interface Sujeto {
    void agregarObservador(Observador observador);
    void quitarObservador(Observador observador);
    void notificarObservadores();
}
