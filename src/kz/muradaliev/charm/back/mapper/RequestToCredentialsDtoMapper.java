package kz.muradaliev.charm.back.mapper;

import jakarta.servlet.http.HttpServletRequest;
import kz.muradaliev.charm.back.dto.CredentialsDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RequestToCredentialsDtoMapper implements Mapper<HttpServletRequest, CredentialsDto> {

    private static final RequestToCredentialsDtoMapper INSTANCE = new RequestToCredentialsDtoMapper();

    public static RequestToCredentialsDtoMapper getInstance() {
        return INSTANCE;
    }

    @Override
    public CredentialsDto map(HttpServletRequest req) {
        return map(req, new CredentialsDto());
    }

    @Override
    public CredentialsDto map(HttpServletRequest req, CredentialsDto dto) {
        dto.setId(Long.parseLong(req.getParameter("id")));
        dto.setEmail(req.getParameter("email"));
        dto.setNewPassword(req.getParameter("newPassword"));
        dto.setConfirmNewPassword(req.getParameter("confirm"));
        dto.setCurrentPassword(req.getParameter("password"));
        return dto;
    }
}