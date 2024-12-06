package Repository.implement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import Models.Applicant;
import Repository.IApplicantRepository;

public class ApplicantRepository implements IApplicantRepository {
    private Connection connection;

    public ApplicantRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Applicant> get() {
        List<Applicant> applicants = new ArrayList<>();
        String query = "SELECT * FROM tb_applicant";

        try {
            ResultSet resultSet = connection.prepareStatement(query).executeQuery();
            while (resultSet.next()) {
                Applicant applicant = new Applicant();
                applicant.setId(resultSet.getInt(1));
                applicant.setName(resultSet.getString(2));
                applicant.setAddress(resultSet.getString(3));
                applicant.setDescription(resultSet.getString(4));
                applicants.add(applicant);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return applicants;
    }

    @Override
    public boolean post(Applicant applicant) {
        String query = "INSERT INTO tb_applicant(id, name, address, description) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, applicant.getId());
            preparedStatement.setString(2, applicant.getName());
            preparedStatement.setString(3, applicant.getAddress());
            preparedStatement.setString(4, applicant.getDescription());
            Integer result = preparedStatement.executeUpdate();
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Applicant> getById(String id) {
        List<Applicant> applicants = new ArrayList<>();
        String query = "SELECT * FROM tb_applicant where id = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, id);

            try {
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    Applicant applicant = new Applicant();
                    applicant.setId(resultSet.getInt(1));
                    applicant.setName(resultSet.getString(2));
                    applicant.setAddress(resultSet.getString(3));
                    applicant.setDescription(resultSet.getString(4));
                    applicants.add(applicant);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return applicants;
    }

    @Override
    public boolean update(Applicant applicant, String id) {
        String query = "UPDATE tb_applicant SET name=?, address=?, description=? WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, applicant.getName());
            preparedStatement.setString(2, applicant.getAddress());
            preparedStatement.setString(3, applicant.getDescription());
            preparedStatement.setString(4, id);
            Integer result = preparedStatement.executeUpdate();

            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(String id) {
        String selectBiodataId = "SELECT id FROM tb_biodata_applicant WHERE applicant_id = ?";
        String deleteExperience = "DELETE FROM tb_experience WHERE biodata_id = ?";
        String deleteBiodataUniversity = "DELETE FROM tb_biodata_university WHERE biodata_id = ?";
        String deleteBiodataFaculty = "DELETE FROM tb_biodata_faculty WHERE biodata_id = ?";
        String deleteUsersApplicant = "DELETE FROM tb_users_applicant WHERE applicant_id = ?";
        String deleteApplyJob = "DELETE FROM tb_apply_job WHERE applicant_id = ?";
        String deleteBiodataApplicant = "DELETE FROM tb_biodata_applicant WHERE applicant_id = ?";
        String deleteIPK = "DELETE FROM tb_ipk WHERE applicant_id = ?";
        String deleteApplicant = "DELETE FROM tb_applicant WHERE id = ?";

        try {
            PreparedStatement selectBiodata = connection.prepareStatement(selectBiodataId);
            selectBiodata.setString(1, id);
            ResultSet resultSet = selectBiodata.executeQuery();

            String biodataId = null;
            if (resultSet.next()) {
                biodataId = resultSet.getString("id");
            }

            if (biodataId != null) {
                PreparedStatement preparedDeleteUniv = connection.prepareStatement(deleteBiodataUniversity);
                preparedDeleteUniv.setString(1, biodataId);
                preparedDeleteUniv.executeUpdate();

                PreparedStatement preparedFaculty = connection.prepareStatement(deleteBiodataFaculty);
                preparedFaculty.setString(1, biodataId);
                preparedFaculty.executeUpdate();

                PreparedStatement preparedExperience = connection.prepareStatement(deleteExperience);
                preparedExperience.setString(1, biodataId);
                preparedExperience.executeUpdate();
            }

            PreparedStatement preparedStatementUsersApplicant = connection.prepareStatement(deleteUsersApplicant);
            PreparedStatement preparedStatementApplyJob = connection.prepareStatement(deleteApplyJob);
            PreparedStatement preparedStatementBiodata = connection.prepareStatement(deleteBiodataApplicant);
            PreparedStatement preparedStatementApplicant = connection.prepareStatement(deleteApplicant);
            PreparedStatement preparedStatementIPK = connection.prepareStatement(deleteIPK);

            preparedStatementUsersApplicant.setString(1, id);
            preparedStatementUsersApplicant.executeUpdate();

            preparedStatementApplyJob.setString(1, id);
            preparedStatementApplyJob.executeUpdate();

            preparedStatementBiodata.setString(1, id);
            preparedStatementBiodata.executeUpdate();

            preparedStatementIPK.setString(1, id);
            preparedStatementIPK.executeUpdate();

            preparedStatementApplicant.setString(1, id);
            Integer result = preparedStatementApplicant.executeUpdate();

            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
