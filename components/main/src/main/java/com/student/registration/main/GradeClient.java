package com.student.registration.main;

import com.student.registration.domain.Grade;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class GradeClient {

    @Value("${grades.api.url}")
    String gradesApi;

    RestTemplate restTemplate = new RestTemplate();

    public List<Grade> getGradesForStudent(long studentId){
        System.out.println("calling it through client");
        return restTemplate.getForEntity(gradesApi+"/grades/"+studentId, List.class).getBody();
    }


    public List<Grade> updateGradesForStudent(List<Grade> grades){
        return restTemplate.postForEntity(gradesApi+"/grades", grades, List.class).getBody();
    }

}
