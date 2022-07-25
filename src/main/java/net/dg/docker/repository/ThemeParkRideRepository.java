package net.dg.docker.repository;

import net.dg.docker.model.ThemeParkRide;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ThemeParkRideRepository extends CrudRepository<ThemeParkRide, Long> {

    List<ThemeParkRide> findByName(String name);
}
