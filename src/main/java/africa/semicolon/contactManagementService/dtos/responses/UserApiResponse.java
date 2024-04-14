package africa.semicolon.contactManagementService.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserApiResponse {
    boolean isSuccessful;
    Object userResponse;
}
