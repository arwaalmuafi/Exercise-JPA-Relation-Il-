package com.example.schoolmanagementsoftware.Service;

import com.example.schoolmanagementsoftware.ApiResponse.ApiException;
import com.example.schoolmanagementsoftware.Model.Address;
import com.example.schoolmanagementsoftware.Model.Teacher;
import com.example.schoolmanagementsoftware.Repository.AddressRepository;
import com.example.schoolmanagementsoftware.Repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeacherService {
    private final TeacherRepository teacherRepository;
    private final AddressRepository addressRepository;
    public List<Teacher> getALLTeacher(){
        return teacherRepository.findAll();
    }

    public void addTeacher(Teacher teacher){
        teacherRepository.save(teacher);
    }

    public void updateTeacher(Teacher teacher,Integer id){
        Teacher teacher1=teacherRepository.findTeacherById(id);
        if(teacher1==null){
            throw new ApiException("Teacher cannot found ");
        }
        teacher1.setAddress(teacher.getAddress());
        teacher1.setAge(teacher.getAge());
        teacher1.setName(teacher.getName());
        teacher1.setSalary(teacher.getSalary());
        teacher1.setEmail(teacher.getEmail());

        teacherRepository.save(teacher1);
    }

    public void deleteTeacher(Integer id){
        Teacher teacher=teacherRepository.findTeacherById(id);
        if(teacher==null){
            throw new ApiException("Teacher cannot found ");
        }
        Address address=addressRepository.findAddressById(id);
        teacher.setAddress(null);

        addressRepository.delete(address);
        teacherRepository.delete(teacher);
    }


    public Teacher getByTeacherId(Integer id) {
        Teacher teacher = teacherRepository.findTeacherById(id);
        if (teacher == null) {
            throw new ApiException("Teacher cannot found ");
        }
        return teacher;
    }
    }

