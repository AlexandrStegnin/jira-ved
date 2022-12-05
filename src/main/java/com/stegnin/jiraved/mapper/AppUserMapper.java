package com.stegnin.jiraved.mapper;

import com.stegnin.jiraved.config.MapStructConfig;
import com.stegnin.jiraved.storage.model.AppUser;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Contact;

import java.time.LocalDateTime;

/**
 * @author Alexandr Stegnin
 */
@Component
@Mapper(config = MapStructConfig.class, imports = {LocalDateTime.class})
public interface AppUserMapper {

    @Mapping(target = "id", source = "contact.userId")
    @Mapping(target = "phone", source = "contact.phoneNumber")
    @Mapping(target = "createdAt", expression = "java(LocalDateTime.now())")
    AppUser toUser(Contact contact);

}
