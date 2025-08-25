package com.myApp.Project_REE_IMS.utils;

import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.Style;
import com.itextpdf.layout.borders.SolidBorder;
//import com.itextpdf.layout.element.*;
import com.itextpdf.layout.element.*;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;
import com.itextpdf.layout.properties.VerticalAlignment;
import com.myApp.Project_REE_IMS.model.Application;
import com.myApp.Project_REE_IMS.model.Items_Details;

import java.io.ByteArrayOutputStream;

public class Imports_PDF {

    private static void addRow(Table table, Style style, String key, String value) {
        table.addCell(new Cell()
                .add(new Paragraph(check(key)))
                .addStyle(style)
                .setTextAlignment(TextAlignment.LEFT)
                .setVerticalAlignment(VerticalAlignment.MIDDLE));


        table.addCell(new Cell()
                .add(new Paragraph(check(value)))
                .setVerticalAlignment(VerticalAlignment.MIDDLE)
                .setTextAlignment(TextAlignment.LEFT)
                .setBorder(new SolidBorder(1.3f)));
    }

    private static void addItems(Table table, Style style, String value) {
        table.addCell(new Cell()
                .add(new Paragraph(check(value)))
                .addStyle(style));
    }

    private static String check(String str) {
        return str == null ? "" : str;
    }

    public static byte[] buildCertificatePdf(Application exporter, java.util.List<Items_Details> items) {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            PdfWriter writer = new PdfWriter(baos);
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);

            PdfFont font = PdfFontFactory.createFont(StandardFonts.HELVETICA);
            PdfFont fontBold = PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD);
            document.setFont(font)
                    .setFontSize(8.3f)
                    .setFontColor(ColorConstants.BLACK);

            // ------------------------------------ TITLE --------------------------------------
            Paragraph title = new Paragraph("RENEWABLE ENERGY IMPORT REGISTRATION CERTIFICATE")
                    .setFontSize(14)
                    .setTextAlignment(TextAlignment.CENTER)
                    .setFont(fontBold);

            Paragraph govtBody = new Paragraph("Ministry of New and Renewable Energy")
                    .setTextAlignment(TextAlignment.CENTER)
                    .setFontSize(14);

            Paragraph country = new Paragraph("Government of India")
                    .setTextAlignment(TextAlignment.CENTER)
                    .setMarginBottom(13)
                    .setFontSize(14);

            document.add(title);
            document.add(govtBody);
            document.add(country);


            // ---------------------------- REGISTRATION DETAILS-------------------------------

            Table Registration_Info = new Table(6)
                    .setFontSize(8.3f)
                    .setFixedLayout()
                    .setMarginBottom(15f)
                    .useAllAvailableWidth();

            Registration_Info.addHeaderCell(new Cell(1, 6)
                    .add(new Paragraph("Registration Information"))
                    .setTextAlignment(TextAlignment.CENTER)
                    .setVerticalAlignment(VerticalAlignment.MIDDLE)
                    .setFontSize(13)
                    .setBorder(new SolidBorder(1.3f))
                    .setFontColor(new DeviceRgb(133, 17, 17)));

            Style headingStyle = new Style()
                    .setBorder(new SolidBorder(1.3f))
                    .setFontColor(new DeviceRgb(0, 0, 128));

            addRow(Registration_Info, headingStyle, "Reg No", exporter.getRegNo());
            addRow(Registration_Info, headingStyle, "Date", exporter.getDate());
            addRow(Registration_Info, headingStyle, "IEC", exporter.getIec());
            addRow(Registration_Info, headingStyle, "Exporter Name", exporter.getExporterName());
            addRow(Registration_Info, headingStyle, "Address", exporter.getExporterName());
            addRow(Registration_Info, headingStyle, "Country", exporter.getCountry());
            addRow(Registration_Info, headingStyle, "Country Of Origin", exporter.getCountryOfOrigin());
            addRow(Registration_Info, headingStyle, "Port of Exportation", exporter.getPortOfExportation());
            addRow(Registration_Info, headingStyle, "Purpose of Import", exporter.getPurposeOfImport());
            addRow(Registration_Info, headingStyle, "Expected Date of Export", exporter.getExpectedDateOfExport());
            addRow(Registration_Info, headingStyle, "Expected Date of Import", exporter.getExpectedDateOfImport());
            addRow(Registration_Info, headingStyle, "Expected Port of Entry", exporter.getExpectedPortOfEntry());
            addRow(Registration_Info, headingStyle, "Filler's Name", exporter.getFillerName());
            addRow(Registration_Info, headingStyle, "Mobile No", exporter.getMobileNo());

            Registration_Info.addCell(new Cell()
                    .add(new Paragraph("Fee Paid"))
                    .addStyle(headingStyle)
                    .setTextAlignment(TextAlignment.LEFT)
                    .setVerticalAlignment(VerticalAlignment.MIDDLE));


            Registration_Info.addCell(new Cell()
                    .add(new Paragraph(String.valueOf(exporter.getFeePaid()))
                    .setVerticalAlignment(VerticalAlignment.MIDDLE)
                    .setTextAlignment(TextAlignment.LEFT)
                    .setBorder(new SolidBorder(1.3f))));


            document.add(Registration_Info);


            // -------------------------- ITEMS DETAILS--------------------------------

            Table Items_Info = new Table(11)
                    .setFontSize(8.3f)
                    .setFixedLayout()
                    .setMarginBottom(15f)
                    .useAllAvailableWidth();

            // Headings_Style for Items
            Style headingStyle2 = new Style()
                    .setBorder(new SolidBorder(1.3f))
                    .setFontColor(new DeviceRgb(102, 0, 102));

            Style valueStyle = new Style()
                    .setFontSize(7.8f)
                    .setVerticalAlignment(VerticalAlignment.MIDDLE)
                    .setTextAlignment(TextAlignment.LEFT)
                    .setBorder(new SolidBorder(1.3f));

            String[] Items_Headings = {
                    "Sr No",
                    "HS Code & Description",
                    "IS Code", "Item Description",
                    "Technical Detail of Item",
                    "Special Property",
                    "Item Type",
                    "End Use",
                    "Manufactured in India",
                    "Quantity of Import",
                    "CIF Value of import in USD"
            };

            for (String header : Items_Headings) {
                Items_Info.addCell(new Cell()
                        .add(new Paragraph(header))
                        .addStyle(headingStyle2)
                        .setVerticalAlignment(VerticalAlignment.MIDDLE)
                        .setTextAlignment(TextAlignment.LEFT)
                );
            }

            int sr = 1;
            for(Items_Details item : items) {
                Items_Info.addCell(new Cell()
                        .add(new Paragraph(String.valueOf(sr++))
                        .setFontSize(7.8f)
                        .setVerticalAlignment(VerticalAlignment.MIDDLE)
                        .setTextAlignment(TextAlignment.LEFT)
                        .setBorder(new SolidBorder(1.3f))));

                addItems(Items_Info, valueStyle, item.getHsCodeDescription());
                addItems(Items_Info, valueStyle, item.getISCode());
                addItems(Items_Info, valueStyle, item.getItemDescription());
                addItems(Items_Info, valueStyle, item.getTechnicalDetailOfItem());
                addItems(Items_Info, valueStyle, item.getSpecialProperty());
                addItems(Items_Info, valueStyle, item.getItemType());
                addItems(Items_Info, valueStyle, item.getEndUse());
                addItems(Items_Info, valueStyle, item.getManufacturedInIndia());
                addItems(Items_Info, valueStyle, item.getQuantityOfImport() == null ? "" : String.valueOf(item.getQuantityOfImport()));
                addItems(Items_Info, valueStyle, item.getCifValueOfImport() == null ? "" : String.valueOf(item.getCifValueOfImport()));
            }

            document.add(Items_Info);

            // ------------------------ DECLARATION BOX -------------------------
            Div DeclarationDiv = new Div()
                    .setBorder(new SolidBorder(1.3f))
                    .setPaddingLeft(2)
                    .setKeepTogether(true)
                    .setWidth(UnitValue.createPercentValue(100));

            // Heading - Declaration
            Paragraph declarationHeading = new Paragraph("Declaration")
                    .setFont(fontBold);

            Paragraph statement = new Paragraph("1. I/We, hereby, declare/certify that: ")
                    .setMarginLeft(1)
                    .setMarginBottom(5);

            // List Of Statements
            List declarationList = new List()
                    .setSymbolIndent(5)
                    .setListSymbol("•");

            declarationList.add(new ListItem(
                    "The particulars and the statements made in this application are true " +
                            "and correct to the best of my / our knowledge and belief and nothing has been concealed or held there from. " +
                            "I/We fully understand that any information furnished in the application if found incorrect or false will render me/ us liable for any penal action or other consequences as may be prescribed in law or otherwise warranted."));

            declarationList.add(new ListItem(
                    "We would abide by any of the provisions under FTP, FT(D&R Act, 1992, as amended, or any other law established by any of " +
                            "the Ministries/Departments/Offices of the Central/State Governments relating to export of these items. I/We undertake to abide by the provisions " +
                            "of the FT (D &R) Act, 1992, as amended, the Rules and Orders framed there under, FTP, HBP, Appendices and Aayat Niryat Forms and ITC (HS)."));

            declarationList.add(new ListItem(
                    "none of the Proprietor/ Partner(s) / Director(s) / Karta / Trustee of the firm / company, as the case may be, is / are a Proprietor / Partner(s) " +
                            "/Director(s) / Karta / Trustee in any other firm / Company which has come to the adverse notice of DGFT or is in the caution list of RBI."
            ));

            declarationList.add(new ListItem(
                    "No export proceeds are outstanding beyond the prescribed period as laid down by RBI or such extended period for which RBI permission " +
                            "hasbeen obtained."
            ));

            declarationList.add(new ListItem(
                    "that I/We have updated the IEC profiles in ANF 1."
            ));

            declarationList.add(new ListItem(
                    "I/We hereby certify that: the entity for whom the application has been made have not been penalized under any of the following Acts (as amended " +
                            "from time to time): The Customs Act, 1962, The Central Excise Act 1944, Foreign Trade (Development &Regulation) Act 1992, as amended, " +
                            "and The Foreign Exchange Management Act,1999, The Conservation of Foreign Exchange, Prevention of Smuggling Activities Act, 1974 " +
                            "Weapons ofMass Destruction &their Delivery Systems (Prohibition of Unlawful Activities) Act, 2005"
            ));

            declarationList.add(new ListItem(
                    "I am authorised to verify and sign this declaration as per Paragraph 9.06 of the FTP."
            ));

            DeclarationDiv.add(declarationHeading);
            DeclarationDiv.add(statement);
            DeclarationDiv.add(declarationList);

            document.add(DeclarationDiv);

            document.close();
            System.out.println("Pdf Created Successfully!!!");

            return baos.toByteArray();

        } catch (Exception e) {
            throw new RuntimeException("Failed to generate Pdf", e);
        }
    }
}
