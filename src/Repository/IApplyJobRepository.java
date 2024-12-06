package Repository;

import java.util.List;

import Models.ApplyJob;

public interface IApplyJobRepository {
    List<ApplyJob> get();

    List<ApplyJob> getById(String id);

    boolean post(ApplyJob applyJob);

}
