# Factory Method en SistemaPedidos

**Tipo:** creacional. **Autor:** Matías Pardo.

## Roles

| Rol | Clase | Responsabilidad |
| --- | --- | --- |
| Creator | `PedidoCreator` | Declara el factory method `crearPedido()` y el flujo común `registrarPedido()` (validar, crear, suscribir observadores) |
| ConcreteCreator | `PedidoLocalCreator`, `PedidoDeliveryCreator`, `PedidoRecojoCreator` | Cada uno decide qué tipo de pedido fabricar |
| Product | `Pedido` | Clase base abstracta de los pedidos |
| ConcreteProduct | `PedidoLocal`, `PedidoDelivery`, `PedidoRecojo` | Cada uno calcula su propio costo de envío |

## Cómo se agrega un nuevo tipo de pedido (ejemplo: `PedidoMesa`)

1. Crear `PedidoMesa extends Pedido` en `modelo/` e implementar `calcularCostoEnvio()` y `getTipo()`.
2. Crear `PedidoMesaCreator extends PedidoCreator` en `patrones/factory/` y devolver `new PedidoMesa(...)` en `crearPedido()`.
3. Usarlo desde el cliente. **No se modifica ninguna clase existente** (principio abierto/cerrado).

## Validaciones

`registrarPedido()` rechaza clientes vacíos y pedidos sin productos. `PedidoDeliveryCreator` exige una dirección.

## Pruebas

```bash
java -cp out pruebas.PruebaFactoryMethod
```

Verifican el tipo y el costo de envío de cada pedido, y las validaciones.
