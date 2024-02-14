package com.example.demo.repository;

import com.example.demo.models.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProductoRepository extends JpaRepository <Producto, Long> {

    @Query(
            value = "WITH products_with_bookings as "
                    +"(SELECT product_id FROM 0521PTC8N2db_GRUPO5.bookings b "
                    +"WHERE "
                    +"NOT (DATE(?1) <= start_date AND DATE(?1) <= end_date AND DATE(?2) <= start_date AND DATE(?2) <= end_date) AND "
                    +"NOT (DATE(?1) >= start_date AND DATE(?1) >= end_date AND DATE(?2) >= start_date AND DATE(?2) >= end_date) "
                    +"GROUP BY product_id) "
                    +"SELECT p.* FROM 0521PTC8N2db_GRUPO5.products p "
                    +"LEFT JOIN products_with_bookings pwb ON p.id = pwb.product_id "
                    +"WHERE pwb.product_id IS NULL",
            nativeQuery = true
    )
    List<Producto> getProductsWithoutBook(String start_date, String end_date);

    @Query(
            value = "WITH products_with_bookings as "
                    +"(SELECT product_id FROM 0521PTC8N2db_GRUPO5.bookings b "
                    +"WHERE "
                    +"NOT (DATE(?1) <= start_date AND DATE(?1) <= end_date AND DATE(?2) <= start_date AND DATE(?2) <= end_date) AND "
                    +"NOT (DATE(?1) >= start_date AND DATE(?1) >= end_date AND DATE(?2) >= start_date AND DATE(?2) >= end_date) "
                    +"GROUP BY product_id) "
                    +"SELECT p.* FROM 0521PTC8N2db_GRUPO5.products p "
                    +"LEFT JOIN products_with_bookings pwb ON p.id = pwb.product_id "
                    +"WHERE pwb.product_id IS NULL AND city_id = ?3",
            nativeQuery = true
    )
    List<Producto> getProductsWithoutBookAndCity(String start_date, String end_date, Long city_id);

    @Query(
            value = "SELECT * FROM products p ORDER BY RAND() LIMIT 8;",
            nativeQuery = true
    )
    List<Producto> getProductsRandom();
}
