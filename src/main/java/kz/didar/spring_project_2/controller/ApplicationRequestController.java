package kz.didar.spring_project_2.controller;

import kz.didar.spring_project_2.model.ApplicationRequest;
import kz.didar.spring_project_2.service.ApplicationRequestService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@AllArgsConstructor
public class ApplicationRequestController {
    private ApplicationRequestService applicationRequestService;

    @GetMapping("/")
    public String getAllRequests(Model model) {
        List<ApplicationRequest> requests = applicationRequestService.getApplicationRequests();
        model.addAttribute("requests", requests);
        return "all_requests";
    }

    @GetMapping("/handled")
    public String getAllHandledRequests(Model model) {
        List<ApplicationRequest> requests = applicationRequestService.getHandledApplicationRequests();
        model.addAttribute("requests", requests);
        return "handled_requests";
    }

    @GetMapping("/unhandled")
    public String getAllUnhandledRequests(Model model) {
        List<ApplicationRequest> requests = applicationRequestService.getUnhandledApplicationRequests();
        model.addAttribute("requests", requests);
        return "unhandled_requests";
    }

    // Добавление заявки
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("request", new ApplicationRequest());
        model.addAttribute("courses", List.of("Java", "Front", "UX/UI"));
        return "add_request";
    }

    @PostMapping("/add")
    public String addRequest(@ModelAttribute("request") ApplicationRequest request) {
        applicationRequestService.addApplicationRequest(request);
        return "redirect:/";
    }

    // Обработка заявки
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") long id, Model model) {
        ApplicationRequest request = applicationRequestService.getApplicationRequest(id);
        model.addAttribute("request", request);
        return "edit_request";
    }

    @PostMapping("/handle/{id}")
    public String editRequest(@PathVariable("id") long id) {
        applicationRequestService.handleApplicationRequest(id);
        return "redirect:/";
    }

    // Удаление заявки
    @GetMapping("/delete/{id}")
    public String deleteRequest(@PathVariable("id") long id) {
        applicationRequestService.deleteApplicationRequest(id);
        return "redirect:/";
    }
}

