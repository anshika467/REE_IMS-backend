package com.myApp.Project_REE_IMS.repository;

import com.myApp.Project_REE_IMS.model.Items_Details;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemsDetailsRepository extends JpaRepository<Items_Details, Integer> {
    List<Items_Details> findByApplicationId(Integer applicationId);
}
