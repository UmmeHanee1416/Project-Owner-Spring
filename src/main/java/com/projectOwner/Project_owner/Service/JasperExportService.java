package com.projectOwner.Project_owner.Service;

import com.projectOwner.Project_owner.DTO.ProjectDto;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class JasperExportService {

    @Autowired
    private ProjectService service;

    public byte[] ExportReportProject(String format, Long id) throws IOException, JRException {
        List<ProjectDto> projectDtoList = service.getAllData(id);
        File file = ResourceUtils.getFile("classpath:ProjectOwner.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(projectDtoList);
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        JasperPrint print = JasperFillManager.fillReport(jasperReport,map,dataSource);
        if (format.equalsIgnoreCase("pdf")){
            JasperExportManager.exportReportToPdfFile(print,"C:\\Users\\ZAHID\\Desktop\\jasper\\project-report.pdf");
        }
        return Files.readAllBytes(Paths.get("C:\\Users\\ZAHID\\Desktop\\jasper\\project-report.pdf"));
    }
}