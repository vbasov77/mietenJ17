package mieten17.services;

import mieten17.models.Address;
import mieten17.repositories.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService {
    @Autowired
    private AddressRepository addressRepository;

    public void save(Address address) {
        addressRepository.save(address);
    }


}
