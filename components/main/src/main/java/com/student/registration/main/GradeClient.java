package com.student.registration.main;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.student.registration.domain.Grade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestOperations;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GradeClient {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final String gradesApiUrl;

    private final RestOperations restOperations;

    private final Map<Long, List<Grade>> gradesCache = new HashMap<>();

    public GradeClient(RestOperations restOperations, String gradesApiUrl) {
        this.restOperations = restOperations;
        this.gradesApiUrl = gradesApiUrl;
    }


    //@HystrixCommand(fallbackMethod = "getGradesForStudentFromCache")
    public List<Grade> getGradesForStudent(long studentId){
        List<Grade> gradeList = restOperations.exchange(this.gradesApiUrl +"/grades/"+studentId, HttpMethod.GET, null, new ParameterizedTypeReference<List<Grade>>() {}).getBody();
        gradesCache.put(studentId, gradeList);
        return gradeList;
    }

    public List<Grade> getGradesForStudentFromCache(long id){
        logger.info("Getting grades for Student with id {} from cache", id);
        return gradesCache.get(id);
    }

    public List<Grade> updateGradesForStudent(List<Grade> grades){
        return restOperations.postForEntity(this.gradesApiUrl +"/grades", grades, List.class).getBody();
    }

}
