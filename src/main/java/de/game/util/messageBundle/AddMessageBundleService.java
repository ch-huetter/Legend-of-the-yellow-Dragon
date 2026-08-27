package de.game.util.messageBundle;

import de.game.model.MessageResolvableImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AddMessageBundleService {

    public void addPlayerClasses (List<MessageResolvableImpl> list) {
        list.addAll(getPlayerClassNames());
        list.addAll(getPlayerClassDescriptions());
    }

    public void addPlayerClassNames (List<MessageResolvableImpl> list) {
        list.addAll(getPlayerClassNames());
    }

    public void addPlayerClassDescriptions (List<MessageResolvableImpl> list) {
        list.addAll(getPlayerClassDescriptions());
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

    public List<MessageResolvableImpl> getAttributeLabels () {
        ArrayList<MessageResolvableImpl> mRRList = new ArrayList<>();
        mRRList.add(new MessageResolvableImpl("label.agility"));
        return mRRList;
    }

}
