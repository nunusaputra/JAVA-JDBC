package Controllers;

import DBConnection.DbConnection;
import Models.Applicant;
import Repository.implement.ApplicantRepository;

public class ApplicantControllers {
    ApplicantRepository applicantRepository = new ApplicantRepository(DbConnection.getConnection());

    public void get() {
        for (Applicant applicant : applicantRepository.get()) {
            System.out.println("\nID\t\t: " + applicant.getId());
            System.out.println("Name\t\t: " + applicant.getName());
            System.out.println("Address\t\t: " + applicant.getAddress());
            System.out.println("Description\t: " + applicant.getDescription());
        }
    }

    public void getById(String id) {
        for (Applicant applicant : applicantRepository.getById(id)) {
            System.out.println("\nID\t\t: " + applicant.getId());
            System.out.println("Name\t\t: " + applicant.getName());
            System.out.println("Address\t\t: " + applicant.getAddress());
            System.out.println("Description\t: " + applicant.getDescription());
        }
    }

    public void post() {
        Applicant applicant = new Applicant();
        applicant.setId(0);
        applicant.setName("Alifia Nurhasanah");
        applicant.setAddress("Bekasi");
        applicant.setDescription("Aku cantik sekali");

        Boolean result = applicantRepository.post(applicant);

        if (result) {
            System.out.println("Insert data success");
        } else {
            System.out.println("Insert data failed");
        }
    }

    public void update(String id) {
        Applicant applicant = new Applicant();
        applicant.setId(0);
        applicant.setName("Michelle Zuidith");
        applicant.setAddress("Kabupaten Bekasi");
        applicant.setDescription("Ingin berhenti menjadi artis");

        Boolean result = applicantRepository.update(applicant, id);
        if (result) {
            System.out.println("Update data successfully");
        } else {
            System.out.println("Update data is failed");
        }
    }

    public void delete(String id) {
        Boolean result = applicantRepository.delete(id);
        if (result) {
            System.out.println("Data applicant success to deleted");
        } else {
            System.out.println("Failed to delete data applicant");
        }
    }
}
