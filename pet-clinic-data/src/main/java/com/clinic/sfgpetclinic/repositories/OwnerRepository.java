package com.clinic.sfgpetclinic.repositories;

import com.clinic.sfgpetclinic.model.Owner;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface OwnerRepository extends CrudRepository<Owner, Long> {

    Owner findByLastName(String lastName);

    List<Owner> findAllByLastNameLike(String lastName);
}
