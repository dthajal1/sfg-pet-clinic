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
        owner1.setFirstName("Diraj");
        owner1.setLastName("Magar");
        owner1.setId(1L);

        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setFirstName("Dipika");
        owner2.setLastName("Thajali");
        owner2.setId(2L);

        ownerService.save(owner2);
        System.out.println("Owners Loaded...");

        Vet vet1 = new Vet();
        vet1.setFirstName("Rashmi");
        vet1.setLastName("kumari");
        vet1.setId(1L);

        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Kul");
        vet2.setLastName("Bahadur");
        vet2.setId(2L);

        vetService.save(vet2);
        System.out.println("Vet Loaded....");

    }
}
