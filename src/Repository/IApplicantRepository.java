package Repository;

import java.util.List;

import Models.Applicant;

public interface IApplicantRepository {
    List<Applicant> get();

    List<Applicant> getById(String id);

    boolean post(Applicant applicant);

    boolean update(Applicant applicant, String id);

    boolean delete(String id);
}
