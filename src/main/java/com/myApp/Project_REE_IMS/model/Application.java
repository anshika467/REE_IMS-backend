package com.myApp.Project_REE_IMS.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "application", schema = "exim")
@Data
public class Application {

    @Id
    @Column(name = "application_id")
    private Integer applicationId;

    @Column(name = "registration_code")
    private String regNo;

    @Column(name = "apply_on")
    private String date;

    @Column(name = "iec_code")
    private String iec;

    @Column(name = "exporter_name")
    private String exporterName;

    @Column(name = "exporter_address")
    private String address;

    @Column(name = "exporter_country_code")
    private String country;

    @Column(name = "origin_country_code")
    private String countryOfOrigin;

    @Column(name = "exportation_port")
    private String portOfExportation;

    @Column(name = "import_purpose")
    private String purposeOfImport;

    @Column(name = "export_date_exp")
    private String expectedDateOfExport;

    @Column(name = "import_date_exp")
    private String expectedDateOfImport;

    @Column(name = "entry_port_name_exp")
    private String expectedPortOfEntry;

    @Column(name = "applicant_name")
    private String fillerName;

    @Column(name = "applicant_contact")
    private String mobileNo;

    @Column(name = "application_fee_inr")
    private Double feePaid;
}
