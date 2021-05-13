package com.nixsolutions.apteka.service;

import static org.apache.commons.codec.digest.DigestUtils.sha3_256Hex;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nixsolutions.apteka.dao.DoctorDao;
import com.nixsolutions.apteka.model.Doctor;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class DoctorService {
    private final DoctorDao doctorDao;

    public Doctor findCurrentDoctor(Doctor doctor) {
        Doctor result = doctorDao.findByUsername(doctor.getUsername());
        if (result == null) {
            return null;
        }
        if (!result.getPassword()
                .equals(sha3_256Hex(doctor.getPassword()))) {
            return null;
        }
        return result;
    }
}
