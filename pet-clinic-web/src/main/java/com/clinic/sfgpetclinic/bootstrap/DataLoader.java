package com.clinic.sfgpetclinic.bootstrap;

import com.clinic.sfgpetclinic.model.*;
import com.clinic.sfgpetclinic.services.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Slf4j
@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private final SpecialtyService specialtyService;
    private final VisitService visitService;

    public DataLoader(OwnerService ownerService, VetService vetService,
                      PetTypeService petTypeService, SpecialtyService specialtyService,
                      VisitService visitService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.specialtyService = specialtyService;
        this.visitService = visitService;
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("Data Loaded.... ##############################");
        int count = petTypeService.findAll().size();

        if (count == 0) {
            loadData();
        }

    }

    private void loadData() {
        PetType dog = new PetType();
        dog.setName("dog");
        PetType savedDogPetType = petTypeService.save(dog);

        PetType cat = new PetType();
        cat.setName("cat");
        PetType savedCatPetType = petTypeService.save(cat);

        Speciality radiology = new Speciality();
        radiology.setDescription("Radiology");
        Speciality savedRadiology = specialtyService.save(radiology);

        Speciality surgery = new Speciality();
        surgery.setDescription("Surgery");
        Speciality savedSurgery = specialtyService.save(surgery);

        Speciality dentistry = new Speciality();
        dentistry.setDescription("Dentistry");
        Speciality savedDentistry = specialtyService.save(dentistry);

        Owner owner1 = new Owner();
        owner1.setFirstName("Leonal");
        owner1.setLastName("Messi");
        owner1.setAddress("123 30th ave");
        owner1.setCity("San Siro");
        owner1.setTelephone("92387438974");
        Pet messisPet = new Pet();
        messisPet.setPetType(savedDogPetType);
        messisPet.setBirthDate(LocalDate.now());
        messisPet.setOwner(owner1);
        messisPet.setName("Bobby");
        owner1.getPets().add(messisPet);

//        @Builder functionality
//        Owner.builder().address("123").city("san pablo").firstName("diraj").id(1L).lastName("sjdf").build();

        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setFirstName("Cristiano");
        owner2.setLastName("Ronaldo");
        owner2.setAddress("231 20th ave");
        owner2.setCity("San Fransisco");
        owner2.setTelephone("123987423483");
        Pet ronaldosPet = new Pet();
        ronaldosPet.setName("Lucas");
        ronaldosPet.setPetType(savedCatPetType);
        ronaldosPet.setBirthDate(LocalDate.now());
        ronaldosPet.setOwner(owner2);
        owner2.getPets().add(ronaldosPet);

        ownerService.save(owner2);
        System.out.println("Owners Loaded...");

        Visit catVisit = new Visit();
        catVisit.setDate(LocalDate.now());
        catVisit.setPet(ronaldosPet);
        catVisit.setDescription("Sneezy Kitty");

        visitService.save(catVisit);

        System.out.println("Visit Loaded...");

        Vet vet1 = new Vet();
        vet1.setFirstName("Neymar");
        vet1.setLastName("Junior");
        vet1.getSpecialities().add(savedRadiology);

        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("David");
        vet2.setLastName("Alba");
        vet2.getSpecialities().add(savedSurgery);

        vetService.save(vet2);
        System.out.println("Vet Loaded....");
    }
}
