package com.myApp.Project_REE_IMS.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "items_details")
@Data
public class Items_Details {

    @Transient
    private String srNo;

    @Id
    @Column(name = "item_id")
    private Integer itemId;

    @Column(name = "application_id")
    private Integer applicationId;

    @Column(name = "hs_code_desc")
    private String hsCodeDescription;

    @Column(name = "domestic_grade_id")
    private String ISCode;

    @Column(name = "item_description")
    private String itemDescription;

    @Column(name = "technical_detail")
    private String technicalDetailOfItem;

    @Column(name = "special_properties")
    private String specialProperty;

    @Column(name = "project_type_name")
    private String itemType;

    @Column(name = "end_use_name")
    private String endUse;

    @Column(name = "available_india")
    private String manufacturedInIndia;

    @Column(name = "import_quantity")
    private String quantityOfImport;

    @Column(name = "cif_usd")
    private Double cifValueOfImport;
}
