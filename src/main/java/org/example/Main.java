package org.example;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("example-unit");

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        System.out.println("Entity Manager iniciado");

        Cliente cliente1 = Cliente.builder()
                .nombre("Matias")
                .apellido("Roiz")
                .dni(44248428)
                .build();

        Factura factura1 = Factura.builder()
                .numero(25)
                .fecha("04-09-2024")
                .cliente(cliente1)
                .build();

        Domicilio domicilio1 = Domicilio.builder()
                .nombreCalle("Ruta Provincial 60")
                .numero(5000)
                .build();

        domicilio1.setCliente(cliente1);
        cliente1.setDomicilio(domicilio1);

        Categoria categoria1 = Categoria.builder()
                  .denominacion("Bebidas")
                  .build();
        Categoria categoria2 = Categoria.builder()
                .denominacion("Lacteos")
                .build();
        Categoria categoria3 = Categoria.builder()
                .denominacion("Almacen")
                .build();
        Categoria categoria4 = Categoria.builder()
                .denominacion("Limpieza")
                .build();

        Articulo articulo1 = Articulo.builder()
                 .cantidad(120)
                 .denominacion("Coca Cola 2lts")
                 .precio(3500)
                 .build();
        articulo1.getCategorias().add(categoria1);
        categoria1.getArticulos().add(articulo1);

        Articulo articulo2 = Articulo.builder()
                .cantidad(50)
                .denominacion("Yogurt Saché Vainilla")
                .precio(2000)
                .build();
        articulo2.getCategorias().add(categoria2);
        categoria2.getArticulos().add(articulo2);

        Articulo articulo3 = Articulo.builder()
                .cantidad(45)
                .denominacion("Yogurt Saché Frutilla")
                .precio(2000)
                .build();
        articulo2.getCategorias().add(categoria2);
        categoria2.getArticulos().add(articulo2);

        Articulo articulo4 = Articulo.builder()
                .cantidad(200)
                .denominacion("Harina Trigo 0000")
                .precio(1120)
                .build();
        articulo3.getCategorias().add(categoria3);
        categoria3.getArticulos().add(articulo3);

        Articulo articulo5 = Articulo.builder()
                .cantidad(75)
                .denominacion("Jabon Ropa Skip 1lt")
                .precio(7000)
                .build();
        articulo5.getCategorias().add(categoria4);
        categoria4.getArticulos().add(articulo5);

        DetalleFactura detalleFactura1 = DetalleFactura.builder()
                       .build();
        detalleFactura1.setArticulo(articulo1);
        detalleFactura1.setCantidad(2);
        detalleFactura1.setSubtotal(7000);
        articulo1.getDetalleFactura().add(detalleFactura1);
        detalleFactura1.setFactura(factura1);
        factura1.getDetalles().add(detalleFactura1);

        DetalleFactura detalleFactura2 = DetalleFactura.builder()
                .build();
        detalleFactura2.setArticulo(articulo3);
        detalleFactura2.setCantidad(3);
        detalleFactura2.setSubtotal(6000);
        articulo3.getDetalleFactura().add(detalleFactura2);
        detalleFactura2.setFactura(factura1);
        factura1.getDetalles().add(detalleFactura2);

        factura1.setTotal(13000);

        entityManager.persist(factura1);


        //Descomentar las siguiente 3 lineas (114-115-116) para realizar una modificación en la tabla Factura
        // Factura factura1 = entityManager.find(Factura.class, 1L);
        //factura1.setNumero(55);
        //entityManager.merge(factura1);

        //Descomentar las siguientes 2 líneas (119-120) para realizar una eliminación de la tabla Factura
        //Factura factura1 = entityManager.find(Factura.class, 1L);
        //entityManager.remove(factura1);

        entityManager.flush();
        entityManager.getTransaction().commit();
        entityManager.close();
        entityManagerFactory.close();
    }
}
