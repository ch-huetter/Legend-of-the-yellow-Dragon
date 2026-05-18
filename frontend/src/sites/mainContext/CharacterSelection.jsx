import React, {useEffect, useState} from "react"
import {postNewActiveCharacterName} from "../../api/characterApi.js";
import {useLayoutContext} from "../../layout/MainLayout.jsx";
import {ArrowWrapperClickable, ArrowWrapperText} from "../../layout/components/ArrowWrapper.jsx";
import {initCharacterSelection} from "../../api/sites/characterSelectionApi.js";
import {getMessageFromDto} from "../../service/util/messageGetter.js";

export function CharacterSelection() {
    const [context, setContext] = useState({});
    const [characters, setCharacters] = useState([]);
    const [loading, setLoading] = useState(true);
    const layoutContext = useLayoutContext();


    useEffect(() => {
        layoutContext.setBackgroundUrl("/images/background/character_background.png");
        initSite().then(response => {
            setCharacters(response["playerCharacterList"]);

            setContext(response);
            setLoading(false)
        });
    }, [])

    async function initSite() {
        return await initCharacterSelection();
    }

    return (
        <div className="leg-main-content leg-flex-row leg-flex-wrap leg-flex-justify-center">
            {!loading ? <Content/> : <></>}
        </div>
    );


    function Content({props}) {
        return (
            <>
                {characters.map((character, index) =>
                    <div key={character.name} className="leg-w100 leg-p4">
                        <form className="leg-box leg-h100">
                            <div className="leg-flex-row">
                                <div className="leg-flex-column">
                                    <div className="leg-flex-row">
                                        <span className="leg-item-top-left">{character.name}</span>
                                        <span className="leg-item-top-right">{character.level}</span>
                                    </div>
                                    <div className={"leg-flex-row leg-flex-justify-evenly leg-p4"}>
                                        <div>
                                            <ArrowWrapperText>
                                                <span
                                                    className={"leg-fancy-arrow-text"}>{getMessageFromDto(context, "health") + " : " + character.health + "/" + character.maxHealth}</span>
                                            </ArrowWrapperText>
                                        </div>
                                        <div>
                                            <ArrowWrapperText>
                                                <span
                                                    className={"leg-fancy-arrow-text"}>{getMessageFromDto(context, "gold") + " : " + character.gold}</span>
                                            </ArrowWrapperText>
                                        </div>
                                        <div>
                                            <ArrowWrapperText>
                                                <span
                                                    className={"leg-fancy-arrow-text"}>{getMessageFromDto(context, "experience") + " : " + character.experience + "/" + character.experienceForNextLevel}
                                                </span>
                                            </ArrowWrapperText>
                                        </div>
                                    </div>
                                    <div className="leg-flex-row leg-flex-justify-center p-4">
                                        <div>
                                            <ArrowWrapperText>
                                                <span className={"leg-fancy-arrow-text"}>
                                                    {getMessageFromDto(context, "class") + " : " + getMessageFromDto(context, "playerClass." + character.playerClass.name + ".name")}
                                                </span>
                                            </ArrowWrapperText>
                                        </div>
                                    </div>

                                    <div className="leg-display-flex leg-flex-justify-end">
                                        <button className={"leg-fancy-arrow-button"} onClick={(e) => {
                                            e.preventDefault();
                                            postNewActiveCharacterName(character.name).then((response) => {
                                                layoutContext.setCharacter(response["activeCharacter"]);
                                                setCharacters(response["sortedCharacterList"]);
                                            });
                                        }}>
                                            <ArrowWrapperClickable>
                                            <span
                                                className={"leg-fancy-arrow-text"}>{getMessageFromDto(context, "characterSelection.card.button.select")}</span>
                                            </ArrowWrapperClickable>
                                        </button>
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>
                )}
            </>)
    }
}


