package com.myApp.Project_REE_IMS.service;

import com.myApp.Project_REE_IMS.model.Application;
import com.myApp.Project_REE_IMS.model.Items_Details;
import com.myApp.Project_REE_IMS.repository.ApplicationRepository;
import com.myApp.Project_REE_IMS.repository.ItemsDetailsRepository;
import com.myApp.Project_REE_IMS.utils.Imports_PDF;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CertificateService {

    private final ApplicationRepository applicationRepo;
    private final ItemsDetailsRepository itemsDetailsRepo;

    public CertificateService(ApplicationRepository applicationRepo, ItemsDetailsRepository itemsDetailsRepo) {
        this.applicationRepo = applicationRepo;
        this.itemsDetailsRepo = itemsDetailsRepo;
    }

    public byte[] generateCertificatePdf(Integer applicationId) {
        Application exporter = applicationRepo.findByApplicationId(applicationId);
        if(exporter == null) {
            throw new RuntimeException("Exporter not Found!!! for applicationId: " + applicationId);
        }

        List<Items_Details> items = itemsDetailsRepo.findByApplicationId(applicationId);
        return Imports_PDF.buildCertificatePdf(exporter, items);
    }
}
