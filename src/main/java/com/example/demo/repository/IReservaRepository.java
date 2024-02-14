package com.example.demo.repository;

import com.example.demo.models.Producto;
import com.example.demo.models.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface IReservaRepository extends JpaRepository <Reserva, Long>{

    @Query(
            value = "SELECT * FROM bookings b WHERE "
                    + "NOT (DATE(?1) <= start_date AND DATE(?1) <= end_date AND DATE(?2) <= start_date AND DATE(?2) <= end_date) AND "
                    + "NOT (DATE(?1) >= start_date AND DATE(?1) >= end_date AND DATE(?2) >= start_date AND DATE(?2) >= end_date) AND product_id = ?3 "
                    + "LIMIT 1",
            nativeQuery = true
    )
    Reserva checkIfHasNoOtherBooking(String start_date, String end_date, Long id);

    @Query(
            value = "SELECT * FROM bookings b WHERE user_id = ?1",
            nativeQuery = true
    )
    List<Reserva> getBookingByUser(Long id);
}
