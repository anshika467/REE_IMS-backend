package com.myApp.Project_REE_IMS.controller;

import com.myApp.Project_REE_IMS.service.CertificateService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/generateCertificate")
@CrossOrigin(origins = "http://localhost:5173")
public class CertificateController {

    private final CertificateService certificateService;

    public CertificateController(CertificateService certificateService) {
        this.certificateService = certificateService;
    }

    @GetMapping("/download/{applicationId}")
    public ResponseEntity<byte[]> downloadCertificate(@PathVariable Integer applicationId) {
        byte[] pdf = certificateService.generateCertificatePdf(applicationId);
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=certificate.pdf");
        headers.add(HttpHeaders.CONTENT_TYPE, "application/pdf");

        return new ResponseEntity<>(pdf, headers, HttpStatus.OK);
    }

    @GetMapping("/dash")
    public String Dashboard() {
        return "Welcome Geeks!!!";
    }

}
