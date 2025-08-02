package models;

import lombok.Data;

@Data
public class SingleUserResponseModel {
    Data Information;
    @lombok.Data
    public static class Information {
        private Integer id;
        private String email;
        private String first_name;
        private String last_name;
        private String avatar;
    }


}
