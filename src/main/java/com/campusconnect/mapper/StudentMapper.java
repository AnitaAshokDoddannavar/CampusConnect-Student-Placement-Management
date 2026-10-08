package com.campusconnect.mapper;

import org.mapstruct.Mapper;

import com.campusconnect.dto.request.StudentRequestDTO;
import com.campusconnect.dto.response.StudentResponseDTO;
import com.campusconnect.entity.Student;

@Mapper(componentModel = "spring")
public interface StudentMapper {

    Student toEntity(StudentRequestDTO studentRequestDTO);

    StudentResponseDTO toResponseDTO(Student student);
}