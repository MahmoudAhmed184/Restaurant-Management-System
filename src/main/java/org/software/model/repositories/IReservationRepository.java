package org.software.model.repositories;

import org.software.model.Reservation;

import java.util.List;

public interface IReservationRepository extends Repository<Reservation, Integer> {
    List<Reservation> getAll();

    Reservation getById(Integer id);

    Reservation create(Reservation reservation);

    Reservation update(Reservation reservation);

    Reservation delete(Reservation reservation);
}
