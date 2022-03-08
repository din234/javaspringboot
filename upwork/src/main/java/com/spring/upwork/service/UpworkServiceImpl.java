package com.spring.upwork.service;

import com.spring.upwork.model.Job;
import com.spring.upwork.repo.JobRepoSQL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpworkServiceImpl {
    private final JobRepoSQL jobRepoSQL;

    @Autowired
    public UpworkServiceImpl(JobRepoSQL jobRepoSQL) {
        this.jobRepoSQL = jobRepoSQL;
    }

    public Job addJob(Job work){
        return jobRepoSQL.save(work);
    }
}
