# Fichas de patrones

## 1. Factory Method

- **Clasificación:** Creacional (GoF)
- **Problema:** el sistema debe crear pedidos de distinto tipo (local, delivery, recojo). Con `if/else` en el
  código cliente, cada tipo nuevo obliga a modificar código que ya funciona.
- **Solución aplicada:** `PedidoCreator` declara el método fábrica `crearPedido()` y cada `ConcreteCreator`
  decide qué `Pedido` concreto instanciar.
- **Clases participantes:**

| Rol | Clase |
| --- | --- |
| Creator | `PedidoCreator` |
| ConcreteCreator | `PedidoLocalCreator`, `PedidoDeliveryCreator`, `PedidoRecojoCreator` |
| Product | `Pedido` |
| ConcreteProduct | `PedidoLocal`, `PedidoDelivery`, `PedidoRecojo` |

- **Ventajas:** cumple OCP (nuevo tipo = nuevas clases), reduce el acoplamiento entre el cliente y las clases concretas.
- **Desventajas:** aumenta el número de clases.

### Antes / después

```java
// ANTES (sin patrón): el cliente decide con if/else
Pedido p;
if (tipo.equals("LOCAL"))         p = new PedidoLocal(cliente, items);
else if (tipo.equals("DELIVERY")) p = new PedidoDelivery(cliente, items, dir);
else                              p = new PedidoRecojo(cliente, items);
// ...y hay que repetir/modificar esto en cada lugar donde se crea un pedido.

// DESPUÉS (Factory Method)
PedidoCreator creator = new PedidoDeliveryCreator(observadores, dir);
Pedido p = creator.registrarPedido(cliente, items);
```

## 2. Observer

- **Clasificación:** De comportamiento (GoF)
- **Problema:** al cambiar el estado de un pedido deben enterarse cliente, cocina e inventario. Si `Pedido`
  los llama directamente, queda acoplado a todos y agregar un área nueva obliga a modificarlo.
- **Solución aplicada:** `Pedido` implementa `Sujeto` y mantiene una lista de `Observador`; al ejecutar
  `cambiarEstado()` llama a `notificarObservadores()`.
- **Clases participantes:**

| Rol | Clase |
| --- | --- |
| Subject | `Sujeto` (interfaz), implementada por `Pedido` |
| Observer | `Observador` |
| ConcreteObserver | `NotificadorCliente`, `ControlInventario`, `PanelCocina` |

- **Ventajas:** bajo acoplamiento, nuevos observadores sin tocar `Pedido` (OCP), notificación automática.
- **Desventajas:** el orden de notificación no está garantizado; muchos observadores pueden afectar el rendimiento.

### Antes / después

```java
// ANTES: Pedido conoce y llama a cada área
public void cambiarEstado(EstadoPedido e) {
    this.estado = e;
    notificador.avisarCliente(this);
    inventario.descontar(this);
    cocina.mostrar(this);          // agregar un área nueva = modificar Pedido
}

// DESPUÉS: Pedido solo notifica a su lista de observadores
public void cambiarEstado(EstadoPedido e) {
    this.estado = e;
    notificarObservadores();
}
```
