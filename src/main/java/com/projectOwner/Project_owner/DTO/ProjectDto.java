package com.projectOwner.Project_owner.DTO;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@RequiredArgsConstructor
public class ProjectDto {
    private Long id;
    private String name;
    private String intro;
    private Long owner;
    private String ownerName;
    private String status;
    private LocalDate startDateTime;
    private LocalDate endDateTime;
    private Set<Long> members = new HashSet<>();
    private Set<String> memberNames = new HashSet<>();
}
