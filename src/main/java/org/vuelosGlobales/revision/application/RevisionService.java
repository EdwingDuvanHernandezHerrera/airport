package org.vuelosGlobales.revision.application;

import org.vuelosGlobales.employee.domain.EmployeeRelationshipDTO;
import org.vuelosGlobales.employee.infrastructure.EmployeeRepository;
import org.vuelosGlobales.revision.domain.Revision;
import org.vuelosGlobales.revision.domain.RevisionInfoDTO;
import org.vuelosGlobales.revision.infrastructure.RevisionRepository;
import org.vuelosGlobales.airline.domain.Airline;
import org.vuelosGlobales.airline.infrastructure.AirlineRepository;
import org.vuelosGlobales.plane.domain.PlaneStMdDTO;
import org.vuelosGlobales.plane.infrastructure.PlaneRepository;

import java.util.List;
import java.util.Optional;

public class RevisionService {
    private final RevisionRepository revisionRepository;
    private final PlaneRepository planeRepository;
    private final AirlineRepository airlineRepository;
    private final EmployeeRepository employeeRepository;

    public RevisionService(RevisionRepository revisionRepository, PlaneRepository planeRepository, AirlineRepository airlineRepository, EmployeeRepository employeeRepository) {
        this.revisionRepository = revisionRepository;
        this.planeRepository = planeRepository;
        this.airlineRepository = airlineRepository;
        this.employeeRepository = employeeRepository;
    }

    public int createRevision(Revision revision){
        return this.revisionRepository.save(revision);
    }

    public void updateRevision(Revision revision){
        this.revisionRepository.update(revision);
    }

    public Optional<Revision> getRevisionById(int id){
        return this.revisionRepository.findById(id);
    }

    public List<Revision> getAllRevisions(){
        return this.revisionRepository.findAll();
    }

    public void deleteRevision(int id){
        this.revisionRepository.delete(id);
    }

    public List<PlaneStMdDTO> getAllPlanes(boolean filter, int id){
        return this.planeRepository.findAllPlaneStMd(filter, id);
    }

    public Optional<PlaneStMdDTO> getPlaneById(int id){
        return this.planeRepository.findPlaneStMdById(id);
    }

    public List<Revision> getRevisionsByIdPlane(int id){
        return this.revisionRepository.findByIdPlane(id);
    }

    public List<Airline> getAllAirlines(){
        return this.airlineRepository.findAll();
    }

    public Optional<Airline> getAirlineById(int id){
        return this.airlineRepository.findById(id);
    }

    public List<EmployeeRelationshipDTO> getAllEployeesInfo(boolean filter, int id){
        return this.employeeRepository.findAllEmployeesInfo(filter, id);
    }

    public Optional<EmployeeRelationshipDTO> getEmployeeById(String id){
        return this.employeeRepository.findEmployeeInfoById(id);
    }

    public void saveRevisionEmpl(int idRevision, String idEmpl){
        this.revisionRepository.saveReviEmployee(idRevision, idEmpl);
    }

    public List<RevisionInfoDTO> getRevisionInfo(int id){
        return this.revisionRepository.findRevisionByPlane(id);
    }
}
