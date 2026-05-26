import {ArrowWrapperText} from "./ArrowWrapper.jsx";
import {getMessageFromMap} from "../../service/util/messageGetter.js";
import React from "react";
import {isObject} from "../../service/util/objectChecker.js";


export function HeaderInformationBox({character, loading = false, messages = {}}) {
    return <>{loading === true && <HeaderLoading></HeaderLoading>}
        {loading === false && isObject(character) && (<HeaderContent></HeaderContent>)}

    </>

    function HeaderContent() {
        return (
            <div className="leg-header">
                <div className="leg-flex-column">
                    <div className="leg-flex-row">
                        <span className="leg-item-top-left leg-border-top-left-0">{character.name}</span>
                        <span className="leg-item-top-right leg-border-top-right-0">{character.level}</span>
                    </div>

                    <div className="leg-flex-row leg-flex-justify-evenly leg-p4">
                        <div>
                            <ArrowWrapperText>
                    <span
                        className="leg-fancy-arrow-text">{getMessageFromMap(messages, "health") + " : " + character.health + "/" + character.maxHealth}</span>
                            </ArrowWrapperText>
                        </div>
                        <div>
                            <ArrowWrapperText>
                        <span
                            className="leg-fancy-arrow-text">{getMessageFromMap(messages, "gold") + " : " + character.gold}</span>
                            </ArrowWrapperText>
                        </div>
                        <div>
                            <ArrowWrapperText>
                        <span
                            className="leg-fancy-arrow-text">{getMessageFromMap(messages, "experience") + " : " + character.experience + "/" + character.experienceForNextLevel}</span>
                            </ArrowWrapperText>
                        </div>
                    </div>
                </div>
            </div>
        )
    }

    function HeaderLoading() {
        return (
            <div className="leg-header">
                <div className="leg-center-text-100">
                    <span>Loading</span>
                </div>
            </div>
        )

    }
}

