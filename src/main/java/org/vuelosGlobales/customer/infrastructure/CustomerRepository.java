package org.vuelosGlobales.customer.infrastructure;

import org.vuelosGlobales.customer.domain.Customer;
import org.vuelosGlobales.customer.domain.CustomerDocuDTO;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository {

    void save(Customer customer);
    void update(Customer customer);
    Optional<Customer> findById(int id);
    List<Customer> findAll();
    void delete(int id);

    List<CustomerDocuDTO> getAllCustDoc();
    Optional<CustomerDocuDTO> getCustDocById(int id);
}
