package com.bijanghanei.firstjobapp.job.service;

import com.bijanghanei.firstjobapp.job.entity.Job;
import com.bijanghanei.firstjobapp.job.repository.JobRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class JobServiceImpl implements JobService{
//    private List<Job> jobs = new ArrayList<>();
    JobRepository jobRepository;
    public JobServiceImpl(JobRepository jobRepository){
        this.jobRepository = jobRepository;
    }
    @Override
    public List<Job> findAll() {
        return jobRepository.findAll();
    }

    @Override
    public void createJob(Job job) {
        jobRepository.save(job);
    }

    @Override
    public Job getJobById(Long id) {
        return jobRepository.findById(id).orElse(null);
    }

    @Override
    public boolean deleteJobById(Long id) {
        try{
            jobRepository.deleteById(id);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public boolean updateJob(Long id, Job job) {
        boolean updated = false;
        Job j = jobRepository.findById(id).orElse(null);

        if (j!=null){
            j.setTitle(job.getTitle());
            j.setDescription(job.getDescription());
            j.setLocation(job.getLocation());
            j.setMaxSalary(job.getMaxSalary());
            j.setMinSalary(job.getMinSalary());
            jobRepository.save(j);
            updated = true;
        }

        return updated;
    }
}
