package de.game.util;

import de.game.model.MessageResolvable;
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

    private List<MessageResolvable> getPlayerClassNames () {
        ArrayList<MessageResolvable> mRRList = new ArrayList<>();
        mRRList.add(new MessageResolvable("playerClass.duelist.name"));
        mRRList.add(new MessageResolvable("playerClass.warrior.name"));
        mRRList.add(new MessageResolvable("playerClass.mage.name"));
        mRRList.add(new MessageResolvable("playerClass.monk.name"));
        return mRRList;
    }

    private List<MessageResolvable> getPlayerClassDescriptions () {
        ArrayList<MessageResolvable> mRRList = new ArrayList<>();
        mRRList.add(new MessageResolvable("playerClass.duelist.description"));
        mRRList.add(new MessageResolvable("playerClass.warrior.description"));
        mRRList.add(new MessageResolvable("playerClass.mage.description"));
        mRRList.add(new MessageResolvable("playerClass.monk.description"));
        return mRRList;
    }

    @SuppressWarnings("all")
    private void fill (Object obj, List<MessageResolvable> message) {
        if (obj instanceof ArrayList) {
            fillList((List) obj, message);
        } else if (obj instanceof Map) {
            fillMap((Map) obj, message);
        } else {
            throw new UnexpectedTypeException("Type of obj is " + obj.getClass() + " but List<MessageRessourceResolvable> or Map<String, MessageRessourceResolvable> was expected");
        }
    }

    private void fillList (List<MessageResolvable> toFill, List<MessageResolvable> messages) {
        toFill.addAll(messages);
    }

    private void fillMap (Map<String, MessageResolvable> toFill, List<MessageResolvable> messages) {
        messages.forEach(msg -> toFill.put(msg.getKey(), msg));
    }


}
