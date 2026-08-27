package de.game.apiController.sites.characterCreation;

import de.game.apiController.AbstractApiDtoFactory;
import de.game.model.MessageResolvableImpl;
import de.game.util.messageBundle.AddMessageBundleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CharacterCreationApiDtoFactory extends AbstractApiDtoFactory<CharacterCreationApiDto> {

    private final AddMessageBundleService addMessageBundleService;

    @Override
    protected void addMessages (CharacterCreationApiDto characterCreationApiDto) {
        List<MessageResolvableImpl> messagesRessourceResolvableList = characterCreationApiDto.getMessageResolvableList();
        messagesRessourceResolvableList.add(new MessageResolvableImpl("characterCreation.headline"));
        messagesRessourceResolvableList.add(new MessageResolvableImpl("characterCreation.description"));
        messagesRessourceResolvableList.add(new MessageResolvableImpl("characterCreation.name.description"));
        messagesRessourceResolvableList.add(new MessageResolvableImpl("label.name"));
        messagesRessourceResolvableList.add(new MessageResolvableImpl("characterCreation.attribute.headline"));
        messagesRessourceResolvableList.add(new MessageResolvableImpl("characterCreation.attribute.description"));
        messagesRessourceResolvableList.add(new MessageResolvableImpl("characterCreation.attribute.introduction"));
        messagesRessourceResolvableList.add(new MessageResolvableImpl("characterCreation.class.headline"));
        messagesRessourceResolvableList.add(new MessageResolvableImpl("characterCreation.class.description"));
        messagesRessourceResolvableList.add(new MessageResolvableImpl("label.attributePointDisplay"));
        messagesRessourceResolvableList.add(new MessageResolvableImpl("button.choose"));
        messagesRessourceResolvableList.add(new MessageResolvableImpl("button.choosen"));
        messagesRessourceResolvableList.add(new MessageResolvableImpl("button.create"));

        messagesRessourceResolvableList.addAll(addMessageBundleService.getAttributeLabels());
    }


    @Override
    protected Class<CharacterCreationApiDto> getDtoClass () {
        return CharacterCreationApiDto.class;
    }
}
