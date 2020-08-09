package com.clinic.sfgpetclinic.bootstrap;

import com.clinic.sfgpetclinic.model.Owner;
import com.clinic.sfgpetclinic.model.Pet;
import com.clinic.sfgpetclinic.model.PetType;
import com.clinic.sfgpetclinic.model.Vet;
import com.clinic.sfgpetclinic.services.OwnerService;
import com.clinic.sfgpetclinic.services.PetTypeService;
import com.clinic.sfgpetclinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;

    private final VetService vetService;

    private final PetTypeService petTypeService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
    }

    @Override
    public void run(String... args) throws Exception {

        PetType dog = new PetType();
        dog.setName("dog");
        PetType savedDogPetType = petTypeService.save(dog);

        PetType cat = new PetType();
        cat.setName("cat");
        PetType savedCatPetType = petTypeService.save(cat);

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

        Vet vet1 = new Vet();
        vet1.setFirstName("Neymar");
        vet1.setLastName("Junior");

        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("David");
        vet2.setLastName("Alba");

        vetService.save(vet2);
        System.out.println("Vet Loaded....");

    }
}
