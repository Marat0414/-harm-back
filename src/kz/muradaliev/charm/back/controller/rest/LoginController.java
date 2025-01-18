package kz.muradaliev.charm.back.controller.rest;

import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kz.muradaliev.charm.back.dto.LoginDto;
import kz.muradaliev.charm.back.dto.UserDetails;
import kz.muradaliev.charm.back.mapper.JsonMapper;
import kz.muradaliev.charm.back.mapper.RequestToLoginDtoMapper;
import kz.muradaliev.charm.back.service.ProfileService;
import kz.muradaliev.charm.back.validator.LoginValidator;
import kz.muradaliev.charm.back.validator.ValidationResult;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static jakarta.servlet.http.HttpServletResponse.*;
import static kz.muradaliev.charm.back.utils.UrlUtils.LOGIN_REST_URL;

@WebServlet(LOGIN_REST_URL)
public class LoginController extends HttpServlet {

    private final ProfileService service = ProfileService.getInstance();

    private final LoginValidator loginValidator = LoginValidator.getInstance();

    private final JsonMapper jsonMapper = JsonMapper.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        try (BufferedReader reader = req.getReader()) {
            LoginDto dto = jsonMapper.readValue(reader, LoginDto.class);
            ValidationResult validationResult = loginValidator.validate(dto);
            if (validationResult.isValid()) {
                Optional<UserDetails> userDetailsOpt = service.login(dto);
                if (userDetailsOpt.isPresent()) {
                    UserDetails userDetails = userDetailsOpt.get();
                    req.getSession().setAttribute("userDetails", userDetails);
                } else {
                    validationResult.add("error.password.invalid");
                    req.setAttribute("errors", validationResult.getErrors());
                    res.sendError(SC_BAD_REQUEST);
                }
            } else {
                req.setAttribute("errors", validationResult.getErrors());
                res.sendError(SC_BAD_REQUEST);
            }
        } catch (DatabindException ex) {
            req.setAttribute("errors", List.of(ex.getLocalizedMessage(), ex.getOriginalMessage()));
            res.sendError(SC_BAD_REQUEST);
        }
    }
}
