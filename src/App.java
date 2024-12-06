import java.util.Scanner;

import Controllers.ApplicantControllers;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner input = new Scanner(System.in);
        ApplicantControllers applicantControllers = new ApplicantControllers();

        System.out.print("Masukan id applicant\t: ");
        String id = input.nextLine();

        // TODO: GET ALL APPLICANT
        applicantControllers.get();

        // TODO: GET APPLICANT BY ID
        applicantControllers.getById(id);

        // TODO: POST NEW APPLICANT
        applicantControllers.post();

        // TODO: UPDATE APPLICANT
        applicantControllers.update(id);

        // TODO: DELETE APPLICANT
        applicantControllers.delete(id);
        input.close();
    }
}
