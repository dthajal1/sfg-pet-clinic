package com.clinic.sfgpetclinic.repositories;

import com.clinic.sfgpetclinic.model.Pet;
import org.springframework.data.repository.CrudRepository;

public interface PetRepository extends CrudRepository<Pet, Long> {
}
