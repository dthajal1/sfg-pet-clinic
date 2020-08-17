package com.clinic.sfgpetclinic.controllers;

import com.clinic.sfgpetclinic.model.Owner;
import com.clinic.sfgpetclinic.model.PetType;
import com.clinic.sfgpetclinic.services.OwnerService;
import com.clinic.sfgpetclinic.services.PetTypeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;

@Controller
@RequestMapping("/owners/{ownerId}")
public class PetController {

    private static final String VIEWS_OWNER_CREATE_OR_UPDATE_FORM = "owners/createOrUpdateOwnerForm";

    private final PetTypeService petTypeService;
    private final OwnerService ownerService;

    public PetController(PetTypeService petTypeService, OwnerService ownerService) {
        this.petTypeService = petTypeService;
        this.ownerService = ownerService;
    }

    //collection of petTypes will be available in the model as types
    //we can use these petTypes whenever we want inside the thymeleaf templates
    @ModelAttribute("types")
    public Collection<PetType> populatePetTypes() {
        return petTypeService.findAll();
    }

    //Owner will be available in the model as owner
    //we can use this owner whenever we want inside the thymeleaf templates
    @ModelAttribute("owner")
    public Owner findOwner(@PathVariable Long ownerId) {
        return ownerService.findById(ownerId);
    }

    //This will bind all the properties listed in the template to the respective fields
    //except the id
    @InitBinder
    public void initOwnerBinder(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }
}
