import {ArrowWrapperText} from "../layout/components/ArrowWrapper.jsx";
import {useLayoutContext} from "../layout/MainLayout.jsx";
import {getMessageFromDto} from "../service/util/messageGetter.js";
import React, {useState} from "react";

export function HeaderInformationBox() {
    const mainLayoutContext = useLayoutContext();
    const character = !mainLayoutContext.loading ? mainLayoutContext.character : undefined;
    return <div className="leg-header">
        {!mainLayoutContext.loading ? <HeaderContent/> : <HeaderLoading/>}
    </div>

    function HeaderContent() {
        const [visible, setVisible] = useState(false);

        return <div className="leg-flex-column">
            <div className="leg-flex-row">
                <span className="leg-item-top-left leg-border-top-left-0">{character.name}</span>
                <span className="leg-item-top-right leg-border-top-right-0">{character.level}</span>
            </div>

            <div className="leg-flex-row leg-flex-justify-evenly leg-p4">
                <div>
                    <ArrowWrapperText>
                    <span
                        className="leg-fancy-arrow-text">{getMessageFromDto(mainLayoutContext, "health") + " : " + character.health + "/" + character.maxHealth}</span>
                    </ArrowWrapperText>
                </div>
                <div>
                    <ArrowWrapperText>
                        <span
                            className="leg-fancy-arrow-text">{getMessageFromDto(mainLayoutContext, "gold") + " : " + character.gold}</span>
                    </ArrowWrapperText>
                </div>
                <div>
                    <ArrowWrapperText>
                        <span
                            className="leg-fancy-arrow-text">{getMessageFromDto(mainLayoutContext, "experience") + " : " + character.experience + "/" + character.experienceForNextLevel}</span>
                    </ArrowWrapperText>
                </div>
            </div>
        </div>
    }

    function HeaderLoading() {
        return (
            <div className="leg-center-text-100">
                <span>Loading</span>
            </div>
        )

    }
}

