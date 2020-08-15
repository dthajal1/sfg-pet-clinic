package com.clinic.sfgpetclinic.services.springdatajpa;

import com.clinic.sfgpetclinic.model.Owner;
import com.clinic.sfgpetclinic.repositories.OwnerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OwnerSDJpaServiceTest {

    public static final String LAST_NAME = "smith";

    @Mock
    OwnerRepository ownerRepository;

    @InjectMocks
    OwnerSDJpaService ownerSDJpaService;

    Owner returnedOwner;

    Long id = 1L;

    @BeforeEach
    void setUp() {
        returnedOwner = Owner.builder().id(id).lastName(LAST_NAME).build();
    }

    @Test
    void findByLastName() {

        when(ownerRepository.findByLastName(LAST_NAME)).thenReturn(returnedOwner);

        Owner owner = ownerSDJpaService.findByLastName(LAST_NAME);

        assertEquals(LAST_NAME, owner.getLastName());

        verify(ownerRepository, times(1)).findByLastName(any());
    }

    @Test
    void findById() {
        when(ownerRepository.findById(anyLong())).thenReturn(Optional.of(returnedOwner));

        Owner owner = ownerSDJpaService.findById(id);

        assertNotNull(owner);
    }

    @Test
    void findByIdNotFound() {
        when(ownerRepository.findById(anyLong())).thenReturn(Optional.empty());

        Owner owner = ownerSDJpaService.findById(id);

        assertNull(owner);
    }

    @Test
    void save() {
        Owner owner = Owner.builder().id(2L).build();

        when(ownerRepository.save(any())).thenReturn(owner);

        Owner savedOwner = ownerSDJpaService.save(owner);

        assertNotNull(savedOwner);
    }

    @Test
    void findAll() {
        Set<Owner> returnedOwners = new HashSet<>();
        returnedOwners.add(Owner.builder().id(1L).build());
        returnedOwners.add(Owner.builder().id(2L).build());

        when(ownerRepository.findAll()).thenReturn(returnedOwners);

        Set<Owner> owners = ownerSDJpaService.findAll();

        assertNotNull(owners);

        assertEquals(2, owners.size());
    }

    @Test
    void deleteById() {
        ownerSDJpaService.deleteById(id);

        //default is 1 time
        verify(ownerRepository).deleteById(anyLong());
    }

    @Test
    void delete() {
        ownerSDJpaService.delete(returnedOwner);

        verify(ownerRepository).delete(any());
    }
}