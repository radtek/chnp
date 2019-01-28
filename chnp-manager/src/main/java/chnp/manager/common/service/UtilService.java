package chnp.manager.common.service;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;

@Service
public class UtilService {

    public Session getSession() {
        Subject subject = SecurityUtils.getSubject();
        if (null == subject) return null;
        else return subject.getSession();
    }

}