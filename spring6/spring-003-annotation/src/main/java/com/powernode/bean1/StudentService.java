package com.powernode.bean1;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service("studentService")
public class StudentService {

    @Resource(name = "studentImp")
    private StudentDao studentDao;

    public void deleteStudent(){
        studentDao.deleteById();
    }
}
