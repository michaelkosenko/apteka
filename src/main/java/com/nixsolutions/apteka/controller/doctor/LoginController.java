package com.nixsolutions.apteka.controller.doctor;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.nixsolutions.apteka.Constants;
import com.nixsolutions.apteka.dao.DoctorDao;
import com.nixsolutions.apteka.model.Doctor;
import com.nixsolutions.apteka.service.DoctorService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Controller
@RequestMapping("/doctor")
@Slf4j
public class LoginController {

    private final DoctorService doctorService;

    @RequestMapping(path = "/login", method = POST)
    public ModelAndView doLogin(LoginForm form, HttpSession session) {
        log.debug("FORM: {}", form);
        Doctor doctor = doctorService
                .findCurrentDoctor(Doctor.builder().username(form.getUsername())
                        .password(form.getPassword()).build());
        log.debug("FOUND DOCTOR: {}", doctor);
        if (doctor == null) {
            ModelAndView modelAndView = new ModelAndView("doctor/login");
            modelAndView.addObject("errorMessage", "Логин или пароль неверный");
            return modelAndView;
        }
        session.setAttribute(Constants.CURRENT_DOCTOR, doctor);
        return new ModelAndView("redirect:/doctor/index");
    }

    @RequestMapping(path = "/login", method = GET)
    public ModelAndView showLoginForm() {
        ModelAndView modelAndView = new ModelAndView("doctor/login");
        LoginForm form = new LoginForm();
        modelAndView.addObject("loginForm", form);
        return modelAndView;
    }

}
