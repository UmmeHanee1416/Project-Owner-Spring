package com.projectOwner.Project_owner.Service;

import com.projectOwner.Project_owner.DTO.ProjectDto;
import com.projectOwner.Project_owner.Entity.Project;
import com.projectOwner.Project_owner.Entity.Status;
import com.projectOwner.Project_owner.Entity.User;
import com.projectOwner.Project_owner.Repository.ProjectRepo;
import com.projectOwner.Project_owner.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProjectService {
    @Autowired
    private ProjectRepo projectRepo;
    @Autowired
    private UserRepo userRepo;

    public List<ProjectDto> getAllData(){
        List<Project> projects = projectRepo.findProjects();
        List<ProjectDto> projectDtos = new ArrayList<>();
        for (Project project: projects){
           ProjectDto projectDto = new ProjectDto();
           mapToDto(projectDto,project);
           projectDtos.add(projectDto);
        }
        return projectDtos;
    }

    public List<ProjectDto> getAllData(Long id){
        List<Project> projects = projectRepo.findProjectsReport(id);
        List<ProjectDto> projectDtos = new ArrayList<>();
        for (Project project: projects){
            ProjectDto projectDto = new ProjectDto();
            mapToDto(projectDto,project);
            projectDtos.add(projectDto);
        }
        return projectDtos;
    }

    public ProjectDto saveData(ProjectDto projectDto){
        Project project = new Project();
        Set<Long> members = projectDto.getMembers();
        if (members.size()>5){
            throw new RuntimeException("Members should not be more than 5..");
        } else {
            mapToEntity(projectDto,project);
            projectRepo.save(project);
            return projectDto;
        }
    }

    public ProjectDto getById(Long id){
    ProjectDto projectDto = new ProjectDto();
    Project project = projectRepo.findById(id).get();
    mapToDto(projectDto,project);
    return projectDto;
    }

    public void deleteData(Long id){
        Project project = projectRepo.findById(id).get();
        if (project != null){
            project.setDeleted(true);
            projectRepo.save(project);
        } else {
            throw new RuntimeException("Project not found");
        }
    }

    public Project mapToEntity(ProjectDto projectDto, Project project){
        if (project != null){
            project.setId(projectDto.getId());
        }
    project.setName(projectDto.getName());
    project.setIntro(projectDto.getIntro());
    project.setOwner(userRepo.findById(projectDto.getOwner()).get());
    project.setStatus(Status.valueOf(projectDto.getStatus()));
    project.setStartDateTime(projectDto.getStartDateTime());
    project.setEndDateTime(projectDto.getEndDateTime());
    final List<User> users = userRepo.findAllById(projectDto.getMembers() == null ? Collections.emptyList() : projectDto.getMembers());
    if (users.size() != (projectDto.getMembers() == null ? 0 : projectDto.getMembers().size())) {
        throw new RuntimeException("one of members not found");
    }
    project.setMembers(new HashSet<>(users));
    return project;
    }

    public ProjectDto mapToDto(ProjectDto projectDto, Project project){
        if (projectDto != null){
            projectDto.setId(project.getId());
            projectDto.setName(project.getName());
            projectDto.setIntro(project.getIntro());
            projectDto.setOwner(project.getOwner().getUserId());
            projectDto.setOwnerName(project.getOwner().getUsername());
            projectDto.setStatus(project.getStatus().toString());
            projectDto.setStartDateTime(project.getStartDateTime());
            projectDto.setEndDateTime(project.getEndDateTime());
            projectDto .setMembers(project.getMembers() == null ? null : project.getMembers().stream()
                    .map(User::getUserId)
                    .collect(Collectors.toSet()));
            projectDto .setMemberNames(project.getMembers() == null ? null : project.getMembers().stream()
                    .map(User::getUsername)
                    .collect(Collectors.toSet()));
        }
        return projectDto;
    }

    public List<ProjectDto> getByDateStart(String startDate0, String endDate1) {
        List<Project> projects = projectRepo.getByDateStart(startDate0, endDate1);
        List<ProjectDto> projectDtos = new ArrayList<>();
        for (Project project: projects){
            ProjectDto projectDto = new ProjectDto();
            mapToDto(projectDto,project);
            projectDtos.add(projectDto);
        }
        return projectDtos;
    }

    public List<ProjectDto> getByDateEnd(String endDate0, String endDate1) {
        List<Project> projects = projectRepo.getByDateEnd(endDate0, endDate1);
        List<ProjectDto> projectDtos = new ArrayList<>();
        for (Project project: projects){
            ProjectDto projectDto = new ProjectDto();
            mapToDto(projectDto,project);
            projectDtos.add(projectDto);
        }
        return projectDtos;
    }

    public List<ProjectDto> getByDateCustom(String startDate, String endDate) {
        List<Project> projects = projectRepo.getByDateCustom(startDate, endDate);
        List<ProjectDto> projectDtos = new ArrayList<>();
        for (Project project: projects){
            ProjectDto projectDto = new ProjectDto();
            mapToDto(projectDto,project);
            projectDtos.add(projectDto);
        }
        return projectDtos;
    }

    public List<ProjectDto> getAllDataByMember(Long id) {
        List<Project> projects = projectRepo.findProjectsByMember(id);
        List<ProjectDto> projectDtos = new ArrayList<>();
        for (Project project: projects){
            ProjectDto projectDto = new ProjectDto();
            mapToDto(projectDto,project);
            projectDtos.add(projectDto);
        }
        return projectDtos;
    }
}
