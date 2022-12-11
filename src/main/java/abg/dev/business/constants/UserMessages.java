package abg.dev.business.constants;

public class UserMessages {
    private String userInformationHasBeenRetrieved = "Kullanıcı bilgileri getirildi.";
    private String UserInformationBrought = "Kullanıcıların bilgileri getirildi.";
    private String addUser = "Kullanıcı eklendi.";
    private String deleteUser = "Kullanıcı silindi.";
    private String updateUser = "Kullanıcı güncellendi";
    private String userNameAlreadyExists = "Kullanıcı adı zaten mevcut";
    private String eMailAlreadyExists = "Email zaten mevcut.";
    private String userNotFound = "Kullanıcı bulunamadı.";

    public String getUserInformationHasBeenRetrieved() {
        return userInformationHasBeenRetrieved;
    }

    public String getUserInformationBrought() {
        return UserInformationBrought;
    }

    public String getAddUser() {
        return addUser;
    }

    public String getDeleteUser() {
        return deleteUser;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public String getUserNameAlreadyExists() {
        return userNameAlreadyExists;
    }

    public String geteMailAlreadyExists() {
        return eMailAlreadyExists;
    }

    public String getUserNotFound() {
        return userNotFound;
    }
}
