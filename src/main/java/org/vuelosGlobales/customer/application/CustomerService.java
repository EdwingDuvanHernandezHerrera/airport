package org.vuelosGlobales.customer.application;

import org.vuelosGlobales.customer.domain.CustomerDocuDTO;
import org.vuelosGlobales.customer.infrastructure.CustomerRepository;
import org.vuelosGlobales.customer.domain.Customer;
import org.vuelosGlobales.document.domain.Document;
import org.vuelosGlobales.document.infrastructure.DocumentRepository;

import java.util.List;
import java.util.Optional;

public class CustomerService {
    private final CustomerRepository customerRepository;
    private final DocumentRepository documentRepository;

    public CustomerService(CustomerRepository customerRepository, DocumentRepository documentRepository) {
        this.customerRepository = customerRepository;
        this.documentRepository = documentRepository;
    }

    public void createCustomer(Customer customer){
        this.customerRepository.save(customer);
    }

    public void updateCustomer(Customer customer){
        this.customerRepository.update(customer);
    }

    public Optional<Customer> getCustomerById(int id){
        return this.customerRepository.findById(id);
    }

    public List<Customer> getAllCustomers(){
        return this.customerRepository.findAll();
    }

    public void deleteCustomer(int id){
        this.customerRepository.delete(id);
    }
    public List<Document> getAllDocuments(){
        return documentRepository.findAll();
    }

    public Optional<Document> getDocumentById(int id){
        return documentRepository.findById(id);
    }

    public List<CustomerDocuDTO> getAllCustDoc(){
        return this.customerRepository.getAllCustDoc();
    }

    public Optional<CustomerDocuDTO> getCusDocById(int id){
        return this.customerRepository.getCustDocById(id);
    }
}
