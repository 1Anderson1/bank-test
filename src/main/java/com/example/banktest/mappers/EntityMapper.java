package com.example.banktest.mappers;

import com.example.banktest.entity.Phone;
import com.example.banktest.models.responses.PhoneResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface EntityMapper {
    PhoneResponseDto phoneToPhoneResponseDto(Phone phone);
}
