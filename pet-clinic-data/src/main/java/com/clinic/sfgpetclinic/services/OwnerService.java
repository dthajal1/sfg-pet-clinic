package com.clinic.sfgpetclinic.services;

import com.clinic.sfgpetclinic.model.Owner;


public interface OwnerService extends CrudService<Owner, Long> {

    Owner findByLastName(String lastName);
}
