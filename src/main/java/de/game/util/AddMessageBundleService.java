package de.game.util;

import de.game.model.MessageResolvableImpl;
import jakarta.validation.UnexpectedTypeException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AddMessageBundleService {

    public void addPlayerClasses (Object obj) {
        addPlayerClassNames(obj);
        addPlayerClassDescriptions(obj);
    }

    public void addPlayerClassNames (Object obj) {
        fill(obj, getPlayerClassNames());
    }

    public void addPlayerClassDescriptions (Object obj) {
        fill(obj, getPlayerClassDescriptions());

    }

    private List<MessageResolvableImpl> getPlayerClassNames () {
        ArrayList<MessageResolvableImpl> mRRList = new ArrayList<>();
        mRRList.add(new MessageResolvableImpl("playerClass.duelist.name"));
        mRRList.add(new MessageResolvableImpl("playerClass.warrior.name"));
        mRRList.add(new MessageResolvableImpl("playerClass.mage.name"));
        mRRList.add(new MessageResolvableImpl("playerClass.monk.name"));
        return mRRList;
    }

    private List<MessageResolvableImpl> getPlayerClassDescriptions () {
        ArrayList<MessageResolvableImpl> mRRList = new ArrayList<>();
        mRRList.add(new MessageResolvableImpl("playerClass.duelist.description"));
        mRRList.add(new MessageResolvableImpl("playerClass.warrior.description"));
        mRRList.add(new MessageResolvableImpl("playerClass.mage.description"));
        mRRList.add(new MessageResolvableImpl("playerClass.monk.description"));
        return mRRList;
    }

    @SuppressWarnings("all")
    private void fill (Object obj, List<MessageResolvableImpl> message) {
        if (obj instanceof ArrayList) {
            fillList((List) obj, message);
        } else if (obj instanceof Map) {
            fillMap((Map) obj, message);
        } else {
            throw new UnexpectedTypeException("Type of obj is " + obj.getClass() + " but List<MessageRessourceResolvable> or Map<String, MessageRessourceResolvable> was expected");
        }
    }

    private void fillList (List<MessageResolvableImpl> toFill, List<MessageResolvableImpl> messages) {
        toFill.addAll(messages);
    }

    private void fillMap (Map<String, MessageResolvableImpl> toFill, List<MessageResolvableImpl> messages) {
        messages.forEach(msg -> toFill.put(msg.getKey(), msg));
    }


}
