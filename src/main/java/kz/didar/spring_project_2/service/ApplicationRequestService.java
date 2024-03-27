package kz.didar.spring_project_2.service;

import kz.didar.spring_project_2.model.ApplicationRequest;

import java.util.List;

public interface ApplicationRequestService {
    List<ApplicationRequest> getApplicationRequests();
    List<ApplicationRequest> getHandledApplicationRequests();
    List<ApplicationRequest> getUnhandledApplicationRequests();
    ApplicationRequest getApplicationRequest(Long id);
    void addApplicationRequest(ApplicationRequest applicationRequest);
    void handleApplicationRequest(Long id);
    void deleteApplicationRequest(Long id);
}
