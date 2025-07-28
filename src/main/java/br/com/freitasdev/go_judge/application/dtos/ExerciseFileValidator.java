package br.com.freitasdev.go_judge.application.dtos;


import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.web.multipart.MultipartFile;


public class ExerciseFileValidator implements ConstraintValidator<ExerciseFileValidation, MultipartFile> {
    @Override
    public void initialize(ExerciseFileValidation constraintAnnotation) {}

    @Override
    public boolean isValid(MultipartFile multipartFile, ConstraintValidatorContext constraintValidatorContext) {
        if(multipartFile.isEmpty()) {
            return false;
        }

        if(multipartFile.getSize() == 0) {
            return false;
        }

        String contentType = multipartFile.getContentType();
        String fileName = multipartFile.getOriginalFilename();

        if(fileName == null) {
            return false;
        }

        if(contentType == null) {
            return false;
        }

        if(!contentType.equals("application/octet-stream")) {
            return false;
        }

        String[] fileNameSplit = fileName.split("\\.");

        return fileNameSplit[fileNameSplit.length - 1].equals("py");
    }
}