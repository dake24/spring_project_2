package kz.didar.spring_project_2.service;

import kz.didar.spring_project_2.model.ApplicationRequest;
import kz.didar.spring_project_2.repository.ApplicationRequestRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ApplicationRequestServiceImpl implements ApplicationRequestService {
    private ApplicationRequestRepository applicationRequestRepository;

    @Override
    public List<ApplicationRequest> getApplicationRequests() {
        return applicationRequestRepository.findAll();
    }

    @Override
    public List<ApplicationRequest> getHandledApplicationRequests() {
        return applicationRequestRepository.findAll().stream().filter(ApplicationRequest::isHandled).toList();
    }

    @Override
    public List<ApplicationRequest> getUnhandledApplicationRequests() {
        return applicationRequestRepository.findAll().stream().filter(a -> !a.isHandled()).toList();
    }

    @Override
    public ApplicationRequest getApplicationRequest(Long id) {
        return applicationRequestRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid request Id:" + id));
    }

    @Override
    public void addApplicationRequest(ApplicationRequest applicationRequest) {
        applicationRequestRepository.save(applicationRequest);
    }

    @Override
    public void handleApplicationRequest(Long id) {
        ApplicationRequest applicationRequest = applicationRequestRepository.findById(id).orElse(null);
        if (applicationRequest != null) {
            applicationRequest.setHandled(true);
            applicationRequestRepository.save(applicationRequest);
        }
    }

    @Override
    public void deleteApplicationRequest(Long id) {
        applicationRequestRepository.deleteById(id);
    }
}
