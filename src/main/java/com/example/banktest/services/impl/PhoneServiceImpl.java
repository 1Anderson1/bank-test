package com.example.banktest.services.impl;

import com.example.banktest.entity.Phone;
import com.example.banktest.entity.User;
import com.example.banktest.models.requests.AddPhoneRequestDto;
import com.example.banktest.models.requests.DeletePhoneRequestDto;
import com.example.banktest.models.requests.UpdatePhoneRequestDto;
import com.example.banktest.repository.PhoneRepository;
import com.example.banktest.services.PhoneService;
import com.example.banktest.services.UserService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class PhoneServiceImpl implements PhoneService {

    private final PhoneRepository phoneRepository;
    private final UserService userService;

    @Override
    public boolean isPhoneExists(@NonNull String phone) {
        return phoneRepository.existsByPhone(phone);
    }

    @Override
    public Phone addUserPhone(@NonNull AddPhoneRequestDto addPhoneRequestDto, @NonNull String emailOrPhone) {
        if (isPhoneExists(addPhoneRequestDto.getNewPhone())) {
            throw new IllegalArgumentException();
        }

        User user = userService.getByPhoneOrEmail(emailOrPhone);
        Phone phone = new Phone();
        phone.setPhone(addPhoneRequestDto.getNewPhone());
        phone.setUser(user);
        return save(phone);
    }

    @Override
    public void deleteUserPhone(@NonNull DeletePhoneRequestDto deletePhoneRequestDto, @NonNull String emailOrPhone) {
        User user = userService.getByPhoneOrEmail(emailOrPhone);

        if (!isItPossibleToDeleteUserPhone(user, deletePhoneRequestDto.getPhoneToDelete())) {
            log.warn("it is not possible to delete the user's phone {}", emailOrPhone);
            throw new IllegalArgumentException();
        }
        Phone phone = phoneRepository.getByPhone(deletePhoneRequestDto.getPhoneToDelete());
        phoneRepository.delete(phone);
    }

    @Override
    public Phone updatePhone(@NonNull UpdatePhoneRequestDto updatePhoneRequestDto, @NonNull String emailOrPhone) {
        User user = userService.getByPhoneOrEmail(emailOrPhone);

        if (!isItPossibleToUpdateUserPhone(user, updatePhoneRequestDto.getOldPhone())) {
            log.warn("it is not possible to update the user's phone {}", emailOrPhone);
            throw new IllegalArgumentException();
        }

        Phone phone = phoneRepository.getByPhone(updatePhoneRequestDto.getOldPhone());
        phone.setPhone(updatePhoneRequestDto.getNewPhone());
        return save(phone);
    }

    private Phone save(Phone phone) {
        return phoneRepository.save(phone);
    }

    private boolean isItPossibleToUpdateUserPhone(User user, String phoneToUpdate) {
        Set<Phone> userPhones = user.getPhones();
        return userPhones.stream().anyMatch(phone -> phone.getPhone().equals(phoneToUpdate));
    }

    private boolean isItPossibleToDeleteUserPhone(User user, String phoneToDelete) {
        Set<Phone> userPhones = user.getPhones();
        boolean isUserPhoneExists = userPhones.
                stream().
                anyMatch(phone -> phone.getPhone().equals(phoneToDelete));
        return isUserPhoneExists && userPhones.size() > 1;
    }
}
