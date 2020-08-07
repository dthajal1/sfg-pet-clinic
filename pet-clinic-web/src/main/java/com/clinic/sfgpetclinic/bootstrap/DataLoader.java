package com.clinic.sfgpetclinic.bootstrap;

import com.clinic.sfgpetclinic.model.Owner;
import com.clinic.sfgpetclinic.model.Vet;
import com.clinic.sfgpetclinic.services.OwnerService;
import com.clinic.sfgpetclinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;

    private final VetService vetService;

    public DataLoader(OwnerService ownerService, VetService vetService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
    }

    @Override
    public void run(String... args) throws Exception {
        Owner owner1 = new Owner();
        owner1.setFirstName("Leonal");
        owner1.setLastName("Messi");

        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setFirstName("Cristiano");
        owner2.setLastName("Ronaldo");

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
