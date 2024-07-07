package org.vuelosGlobales.employee.application;

import org.vuelosGlobales.employee.domain.Employee;
import org.vuelosGlobales.employee.domain.EmployeeRelationshipDTO;
import org.vuelosGlobales.employee.infrastructure.EmployeeRepository;
import org.vuelosGlobales.role.domain.Role;
import org.vuelosGlobales.role.infrastrustura.RoleRepository;
import org.vuelosGlobales.airline.domain.Airline;
import org.vuelosGlobales.airline.infrastructure.AirlineRepository;
import org.vuelosGlobales.airport.domain.AirportCityDTO;
import org.vuelosGlobales.airport.infrastructure.AirportRepository;

import java.util.List;
import java.util.Optional;

public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final RoleRepository roleRepository;
    private final AirlineRepository airlineRepository;
    private final AirportRepository airportRepository;

    public EmployeeService(EmployeeRepository employeeRepository, RoleRepository roleRepository, AirlineRepository airlineRepository, AirportRepository airportRepository) {
        this.employeeRepository = employeeRepository;
        this.roleRepository = roleRepository;
        this.airlineRepository = airlineRepository;
        this.airportRepository = airportRepository;
    }

    public void createEmployee(Employee employee){
        this.employeeRepository.save(employee);
    }

    public void updateEmployee(Employee employee){
        this.employeeRepository.update(employee);
    }

    public Optional<Employee> getEmployeeById(String id){
        return this.employeeRepository.findById(id);
    }

    public List<Employee> getAllEmployees(){
        return this.employeeRepository.findAll();
    }

    public void deleteEmployee(String id){
        this.employeeRepository.delete(id);
    }

    public List<Role> getAllRoles(){
        return this.roleRepository.findAll();
    }

    public Optional<Role> getRoleById(int id){
        return this.roleRepository.findById(id);
    }

    public List<Airline> getAllAirlines(){
        return this.airlineRepository.findAll();
    }

    public Optional<Airline> getAirlineById(int id){
        return this.airlineRepository.findById(id);
    }

    public List<AirportCityDTO> getAllAirport(){
        return this.airportRepository.findAllAirportCity();
    }

    public Optional<AirportCityDTO> getAirportById(String id){
        return this.airportRepository.findAirportCityById(id);
    }

    public List<EmployeeRelationshipDTO> getAllEmployeesInfo(boolean filter, int id){
        return this.employeeRepository.findAllEmployeesInfo(filter, id);
    }

    public Optional<EmployeeRelationshipDTO> getEmployeeInfoById(String id){
        return this.employeeRepository.findEmployeeInfoById(id);
    }
}
