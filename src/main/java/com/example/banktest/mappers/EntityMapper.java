package com.example.banktest.mappers;

import com.example.banktest.entity.Phone;
import com.example.banktest.entity.User;
import com.example.banktest.models.responses.PhoneResponseDto;
import com.example.banktest.models.responses.UserResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface EntityMapper {

    PhoneResponseDto phoneToPhoneResponseDto(Phone phone);

    List<UserResponseDto> toUserResponseDtoList(List<User> content);
}
