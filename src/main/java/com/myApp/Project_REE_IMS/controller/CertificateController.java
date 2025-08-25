package com.myApp.Project_REE_IMS.controller;

import com.myApp.Project_REE_IMS.service.CertificateService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/generateCertificate")
public class CertificateController {


    private final CertificateService certificateService;

    public CertificateController(CertificateService certificateService) {
        this.certificateService = certificateService;
    }

    @GetMapping("/{applicationId}/download")
    public ResponseEntity<byte[]> downloadCertificate(@PathVariable Integer applicationId) {
        byte[] pdf = certificateService.generateCertificatePdf(applicationId);
        String fileName = "certificate_" + applicationId + ".pdf";

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName)
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdf);
    }

    @GetMapping("/dash")
    public String Dashboard() {
        return "Welcome Geeks!!!";
    }

}
