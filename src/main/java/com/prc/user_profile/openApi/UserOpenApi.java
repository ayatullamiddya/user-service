package com.prc.user_profile.openApi;


import com.prc.user_profile.error.FieldDescription;
import com.prc.user_profile.model.GenericResponse;
import com.prc.user_profile.model.UserProfileDTO;

import com.prc.user_profile.model.UserSignupDTO;
import org.springdoc.core.fn.builders.operation.Builder;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;


import java.util.function.Consumer;

@Configuration
public class UserOpenApi {
    public static Consumer<Builder> studentGetAllStudentsAPI(){
        return ops-> {
            ops.operationId("getStudents")
                    .tag("getStudents")
                    .description("listing all students")
            .requestBody(org.springdoc.core.fn.builders.requestbody.Builder.requestBodyBuilder().implementation(UserProfileDTO.class))
                    .response(org.springdoc.core.fn.builders.apiresponse.Builder.responseBuilder()
                            .responseCode(String.valueOf(HttpStatus.OK.value()))
                            .description("Successfully fetched all students."))
                    .response(org.springdoc.core.fn.builders.apiresponse.Builder.responseBuilder()
                            .responseCode(String.valueOf(HttpStatus.BAD_REQUEST.value()))
                            .description(FieldDescription.BadRequestResponse.text())
                            .implementation(GenericResponse.class))
                    .response(org.springdoc.core.fn.builders.apiresponse.Builder.responseBuilder()
                            .responseCode(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()))
                            .description(FieldDescription.UnExpectedErrorResponse.text())
                            .implementation(GenericResponse.class));
        };

    }

    public static Consumer<Builder> signupUserAPI(){
        return ops-> {
            ops.operationId("signupUser")
                    .tag("signupUser")
                    .description("signupUser")
                    .requestBody(org.springdoc.core.fn.builders.requestbody.Builder.requestBodyBuilder().implementation(UserSignupDTO.class))
                    .response(org.springdoc.core.fn.builders.apiresponse.Builder.responseBuilder()
                            .responseCode(String.valueOf(HttpStatus.CREATED.value()))
                            .description("Successfully signed up the user."))
                    .response(org.springdoc.core.fn.builders.apiresponse.Builder.responseBuilder()
                            .responseCode(String.valueOf(HttpStatus.BAD_REQUEST.value()))
                            .description(FieldDescription.BadRequestResponse.text())
                            .implementation(GenericResponse.class))
                    .response(org.springdoc.core.fn.builders.apiresponse.Builder.responseBuilder()
                            .responseCode(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()))
                            .description(FieldDescription.UnExpectedErrorResponse.text())
                            .implementation(GenericResponse.class));
        };

    }
}
