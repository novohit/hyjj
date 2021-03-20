package com.hyjj.util.tool;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.hyjj.util.responce.CommonReturnType;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ResponseUtil {

    public static void out(HttpServletResponse response, CommonReturnType commonReturnType) {
        ObjectMapper mapper = new ObjectMapper();
        response.setStatus(HttpStatus.OK.value());
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        try {
            mapper.writeValue(response.getWriter(), commonReturnType);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
