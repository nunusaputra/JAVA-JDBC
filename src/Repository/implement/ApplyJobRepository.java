package Repository.implement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import Models.ApplyJob;
import Repository.IApplyJobRepository;

public class ApplyJobRepository implements IApplyJobRepository {
    private Connection connections;

    public ApplyJobRepository(Connection connection) {
        this.connections = connection;
    }

    @Override
    public boolean post(ApplyJob applyJob) {
        String query = "INSERT INTO tb_apply_job(id, applicant_id, job_id, status) VALUES(?,?,?,?)";

        try {
            PreparedStatement preparedStatement = connections.prepareStatement(query);

            preparedStatement.setInt(1, applyJob.getId());
            preparedStatement.setInt(2, applyJob.getApplicant_id());
            preparedStatement.setInt(3, applyJob.getJob_id());
            preparedStatement.setString(4, applyJob.getStatus());

            Integer result = preparedStatement.executeUpdate();

            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public List<ApplyJob> get() {
        List<ApplyJob> applyJobs = new ArrayList<>();
        String query = "SELECT distinct tb_applicant.name AS \"Nama Pelamar\", tb_biodata_applicant.place_of_birth AS \"Tempat Lahir\", date_of_birth AS \"Tanggal Lahir\", tb_university.name AS \"University\", tb_faculty.name AS \"Faculty\", tb_ipk.ipk AS \"IPK\", tb_company.name AS \"Nama Perusahaan\", tb_job.job_title, job_type, sallary, tb_apply_job.status\r\n"
                +
                "FROM tb_applicant\r\n" +
                "LEFT JOIN tb_biodata_applicant ON tb_applicant.id = tb_biodata_applicant.applicant_id\r\n" +
                "LEFT JOIN tb_biodata_university ON tb_biodata_applicant.id = tb_biodata_university.biodata_id\r\n" +
                "LEFT JOIN tb_university ON tb_biodata_university.university_id = tb_university.id\r\n" +
                "LEFT JOIN tb_biodata_faculty ON tb_biodata_applicant.id = tb_biodata_faculty.faculty_id\r\n" +
                "LEFT JOIN tb_faculty ON tb_biodata_faculty.faculty_id = tb_faculty.id\r\n" +
                "LEFT JOIN tb_ipk ON tb_applicant.id = tb_ipk.applicant_id\r\n" +
                "LEFT JOIN tb_apply_job ON tb_applicant.id = tb_apply_job.applicant_id\r\n" +
                "LEFT JOIN tb_job ON tb_job.id = tb_apply_job.job_id\r\n" +
                "LEFT JOIN tb_company ON tb_company.id = tb_job.company_id ";

        try {
            ResultSet resultSet = connections.prepareStatement(query).executeQuery();
            while (resultSet.next()) {
                ApplyJob applyJob = new ApplyJob();
                applyJob.setName(resultSet.getString(1));
                applyJob.setAddress(resultSet.getString(2));
                applyJob.setTanggalLahir(resultSet.getDate(3));
                applyJob.setUniversity(resultSet.getString(4));
                applyJob.setFaculty(resultSet.getString(5));
                applyJob.setIPK(resultSet.getString(6));
                applyJob.setCompanyName(resultSet.getString(7));
                applyJob.setJobTitle(resultSet.getString(8));
                applyJob.setJobType(resultSet.getString(9));
                applyJob.setSallary(resultSet.getInt(10));
                applyJob.setStatus(resultSet.getString(11));

                applyJobs.add(applyJob);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return applyJobs;
    }

    @Override
    public List<ApplyJob> getById(String id) {
        List<ApplyJob> applyJobs = new ArrayList<>();
        String query = "SELECT distinct tb_applicant.id, tb_applicant.name AS \"Nama Pelamar\", tb_biodata_applicant.place_of_birth AS \"Tempat Lahir\", date_of_birth AS \"Tanggal Lahir\", tb_university.name AS \"University\", tb_faculty.name AS \"Faculty\", tb_ipk.ipk AS \"IPK\", tb_company.name AS \"Nama Perusahaan\", tb_job.job_title, job_type, sallary, tb_apply_job.status\r\n"
                +
                "FROM tb_applicant\r\n" +
                "LEFT JOIN tb_biodata_applicant ON tb_applicant.id = tb_biodata_applicant.applicant_id\r\n" +
                "LEFT JOIN tb_biodata_university ON tb_biodata_applicant.id = tb_biodata_university.biodata_id\r\n" +
                "LEFT JOIN tb_university ON tb_biodata_university.university_id = tb_university.id\r\n" +
                "LEFT JOIN tb_biodata_faculty ON tb_biodata_applicant.id = tb_biodata_faculty.faculty_id\r\n" +
                "LEFT JOIN tb_faculty ON tb_biodata_faculty.faculty_id = tb_faculty.id\r\n" +
                "LEFT JOIN tb_ipk ON tb_applicant.id = tb_ipk.applicant_id\r\n" +
                "LEFT JOIN tb_apply_job ON tb_applicant.id = tb_apply_job.applicant_id\r\n" +
                "LEFT JOIN tb_job ON tb_job.id = tb_apply_job.job_id\r\n" +
                "LEFT JOIN tb_company ON tb_company.id = tb_job.company_id " + "WHERE tb_applicant.id = ?";

        try {
            PreparedStatement preparedStatement = connections.prepareStatement(query);
            preparedStatement.setString(1, id);

            try {
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    ApplyJob applyJob = new ApplyJob();
                    applyJob.setId(resultSet.getInt(1));
                    applyJob.setName(resultSet.getString(2));
                    applyJob.setAddress(resultSet.getString(3));
                    applyJob.setTanggalLahir(resultSet.getDate(4));
                    applyJob.setUniversity(resultSet.getString(5));
                    applyJob.setFaculty(resultSet.getString(6));
                    applyJob.setIPK(resultSet.getString(7));
                    applyJob.setCompanyName(resultSet.getString(8));
                    applyJob.setJobTitle(resultSet.getString(9));
                    applyJob.setJobType(resultSet.getString(10));
                    applyJob.setSallary(resultSet.getInt(11));
                    applyJob.setStatus(resultSet.getString(12));

                    applyJobs.add(applyJob);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return applyJobs;
    }

}
