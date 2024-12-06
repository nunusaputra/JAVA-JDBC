package Controllers;

import DBConnection.DbConnection;
import Models.ApplyJob;
import Repository.implement.ApplyJobRepository;

public class ApplyJobControllers {
    ApplyJobRepository applyJobRepository = new ApplyJobRepository(DbConnection.getConnection());

    public void post() {
        ApplyJob applyJob = new ApplyJob();
        applyJob.setId(0);
        applyJob.setApplicant_id(19);
        applyJob.setJob_id(5);
        applyJob.setStatus("waiting");

        boolean result = applyJobRepository.post(applyJob);
        if (result) {
            System.out.println("Success to apply a job");
        } else {
            System.out.println("Failed to apply a job");
        }
    }

    public void get() {
        for (ApplyJob applyJob : applyJobRepository.get()) {
            System.out.println("\nName\t\t: " + applyJob.getName());
            System.out.println("Birth of Place\t: " + applyJob.getAddress());
            System.out.println("Birth of Date\t: " + applyJob.getTanggalLahir());
            System.out.println("University\t: " + applyJob.getUniversity());
            System.out.println("Faculty\t\t: " + applyJob.getFaculty());
            System.out.println("GPA\t\t: " + applyJob.getIPK());
            System.out.println("Company Name\t: " + applyJob.getCompanyName());
            System.out.println("Job Title\t: " + applyJob.getJobTitle());
            System.out.println("Job Type\t: " + applyJob.getJobType());
            System.out.println("Sallary\t\t: " + applyJob.getSallary());
            System.out.println("Status\t\t: " + applyJob.getStatus());
        }
    }

    public void getById(String id) {
        for (ApplyJob applyJob : applyJobRepository.getById(id)) {
            System.out.println("\nName\t\t: " + applyJob.getName());
            System.out.println("Birth of Place\t: " + applyJob.getAddress());
            System.out.println("Birth of Date\t: " + applyJob.getTanggalLahir());
            System.out.println("University\t: " + applyJob.getUniversity());
            System.out.println("Faculty\t\t: " + applyJob.getFaculty());
            System.out.println("GPA\t\t: " + applyJob.getIPK());
            System.out.println("Company Name\t: " + applyJob.getCompanyName());
            System.out.println("Job Title\t: " + applyJob.getJobTitle());
            System.out.println("Job Type\t: " + applyJob.getJobType());
            System.out.println("Sallary\t\t: " + applyJob.getSallary());
            System.out.println("Status\t\t: " + applyJob.getStatus());
        }
    }
}
