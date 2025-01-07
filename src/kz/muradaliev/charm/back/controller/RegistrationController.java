package kz.muradaliev.charm.back.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kz.muradaliev.charm.back.dto.ProfileGetDto;
import kz.muradaliev.charm.back.dto.RegistrationDto;
import kz.muradaliev.charm.back.mapper.RequestToRegistrationDtoMapper;
import kz.muradaliev.charm.back.model.Profile;
import kz.muradaliev.charm.back.service.ProfileService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

@WebServlet("/registration")
@Slf4j
public class RegistrationController extends HttpServlet {

    private final ProfileService service = ProfileService.getInstance();

    private final RequestToRegistrationDtoMapper requestToRegistrationDtoMapper = RequestToRegistrationDtoMapper.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/jsp/registration.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        RegistrationDto dto = requestToRegistrationDtoMapper.map(req);
        Long id = service.save(dto);
        log.info("Profile with the email address {} has been registered with id {}", dto.getEmail(), id);
        resp.sendRedirect(String.format("/profile?id=%s", id));
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String sId = req.getParameter("id");
        boolean success = false;
        if (!sId.isBlank()) {
            success = service.delete(Long.parseLong(sId));
        }
        resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
        if (success) {
            log.info("Profile with id {} has been deleted", sId);
        }
        resp.sendRedirect("/registration");
    }
}