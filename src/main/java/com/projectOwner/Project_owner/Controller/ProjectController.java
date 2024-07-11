package com.projectOwner.Project_owner.Controller;

import com.projectOwner.Project_owner.DTO.ProjectDto;
import com.projectOwner.Project_owner.DTO.SearchDateStart;
import com.projectOwner.Project_owner.Service.JasperExportService;
import com.projectOwner.Project_owner.Service.ProjectService;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "project")
@CrossOrigin(origins = "http://localhost:8081", allowCredentials = "true")
public class ProjectController {

    @Autowired
    private ProjectService service;

    @Autowired
    private JasperExportService exportService;

    @GetMapping
    public List<ProjectDto> getAll(){
        return service.getAllData();
    }

    @PostMapping
    public ProjectDto addData(@RequestBody ProjectDto projectDto){
        return service.saveData(projectDto);
    }

    @GetMapping("/{id}")
    public ProjectDto getByID(@PathVariable("id") Long id){
        return service.getById(id);
    }

    @PostMapping("/member/{UserId}")
    public List<ProjectDto> getAllByMember(@PathVariable("UserId") Long id){
        return service.getAllDataByMember(id);
    }

    @PostMapping("/startDate")
    public List<ProjectDto> getByDateStart(@RequestBody SearchDateStart searchDateStart){
        return service.getByDateStart(searchDateStart.getStartDate(), searchDateStart.getEndDate());
    }

    @PostMapping("/endDate")
    public List<ProjectDto> getByDateEnd(@RequestBody SearchDateStart searchDateStart){
        return service.getByDateEnd(searchDateStart.getStartDate(), searchDateStart.getEndDate());
    }

    @PostMapping("/customDate")
    public List<ProjectDto> getByDateCustom(@RequestBody SearchDateStart searchDateStart){
        return service.getByDateCustom(searchDateStart.getStartDate(), searchDateStart.getEndDate());
    }

    @PutMapping()
    public ProjectDto putProject(@RequestBody ProjectDto projectDto){
        return service.saveData(projectDto);
    }

    @DeleteMapping("/{id}")
    public void deleteProject(@PathVariable("id") Long id){
        service.deleteData(id);
    }

    @GetMapping("/report/{id}")
    public @ResponseBody byte[] reportExport(@PathVariable("id") Long id) throws JRException, IOException {
        String format = "pdf";
        return exportService.ExportReportProject(format, id);
    }

}
