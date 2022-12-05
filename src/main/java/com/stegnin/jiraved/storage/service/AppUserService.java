package com.stegnin.jiraved.storage.service;

import com.stegnin.jiraved.mapper.AppUserMapper;
import com.stegnin.jiraved.storage.model.AppUser;
import com.stegnin.jiraved.storage.repository.AppUserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Contact;

/**
 * @author Alexandr Stegnin
 */
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class AppUserService {

    AppUserRepository appUserRepository;
    AppUserMapper appUserMapper;
    public AppUser save(Contact contact) {
        var user = appUserMapper.toUser(contact);
        log.info("Save user {}", user);
        return appUserRepository.save(user);
    }

    public boolean isRegisteredUser(Long chatUserId) {
        return appUserRepository.existsById(chatUserId);
    }

}
