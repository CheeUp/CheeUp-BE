package com.cheeup.converter;

import com.cheeup.web.dto.TempDTO;

public class TempConverter {
    public static TempDTO.ResponseDTO toTempDTO(){
        return TempDTO.ResponseDTO.builder()
                .testString("This is Test!")
                .build();
    }
}


