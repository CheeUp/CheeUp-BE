package com.cheerup.converter;

import com.cheerup.web.dto.temp.TempResponseDTO;

public class TempConverter {
    public static TempResponseDTO.TempTestDTO toTempTestDTO(){
        return TempResponseDTO.TempTestDTO.builder()
                .testString("This is Test!")
                .build();
    }
}


