package com.clinic.sfgpetclinic.services.springdatajpa;

import com.clinic.sfgpetclinic.model.PetType;
import com.clinic.sfgpetclinic.repositories.PetTypeRepository;
import com.clinic.sfgpetclinic.services.PetTypeService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Profile("springdatajpa")
public class PetTypeSDJpaService implements PetTypeService {

    private final PetTypeRepository petTypeRepository;

    public PetTypeSDJpaService(PetTypeRepository petTypeRepository) {
        this.petTypeRepository = petTypeRepository;
    }

    @Override
    public PetType findById(Long aLong) {
        return petTypeRepository.findById(aLong).orElse(null );
    }

    @Override
    public PetType save(PetType object) {
        return petTypeRepository.save(object);
    }

    @Override
    public Set<PetType> findAll() {
        HashSet<PetType> petTypes = new HashSet<>();
        petTypeRepository.findAll().forEach(petTypes::add );
        return petTypes;
    }

    @Override
    public void deleteById(Long aLong) {
        petTypeRepository.deleteById(aLong);
    }

    @Override
    public void delete(PetType object) {
        petTypeRepository.delete(object);
    }
}
