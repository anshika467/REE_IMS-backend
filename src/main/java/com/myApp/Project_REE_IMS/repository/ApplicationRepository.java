package com.myApp.Project_REE_IMS.repository;

import com.myApp.Project_REE_IMS.model.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Integer> {
    Application findByApplicationId(Integer applicationId);
}
