import {ArrowWrapperClickable, ArrowWrapperHeadline, ArrowWrapperText} from "../../layout/components/ArrowWrapper.jsx";
import {useEffect, useState} from "react";
import {useLayoutContext} from "../../layout/MainLayout.jsx";
import {AttributeSelection} from "../../layout/components/AttributeSelection.jsx";
import {ClassSelection} from "../../layout/components/ClassSelection.jsx";
import {init, submitCharacterCreationRequest} from "../../api/sites/characterCreationApi.js";
import {useMessageContext} from "../../layout/MessageContext.jsx";

export function CharacterCreation(data) {
    const layoutContext = useLayoutContext();
    const [loading, setLoading] = useState(true);
    const [name, setName] = useState("Phoebe");
    const [attributePoints, setAttributePoints] = useState(3);
    const [attributes, setAttributes] = useState({});
    const [playerClasses, setPlayerClasses] = useState([]);
    const [activePlayerClass, setActivePlayerClass] = useState(0);

    const messageContext = useMessageContext();

    useEffect(() => {
        layoutContext.setBackgroundUrl("/images/background/character_background.png");
        init().then(response => {
            setAttributePoints(response.attributePoints);
            setAttributes(response.attributeSelectionEntries);
            setPlayerClasses(response.playerClasses);
            setActivePlayerClass(response.activePlayerClass);
            messageContext.setSiteMessages(response.messages);
            setLoading(false);
        })
    }, [])

    function formSubmit() {
        console.log("Collecting Data")
        let data = {name: name, attributes: attributes, activePlayerClass: activePlayerClass};
        console.log(data);
        submitCharacterCreationRequest(data).then(response => {
            //TODO Success Handling and Error Handling
        });
    }

    return (
        <>
            {loading && (<div className={"leg-box"}>
                <span>
                    Loading ...
                </span>
            </div>)}

            {!loading && (
                <form className={"leg-flex-column leg-align-item-center leg-box leg-p12 leg-center-text"} method="post"
                      onSubmit={(event) => onSubmit(event)}>
                    <ArrowWrapperHeadline className="leg-m-b8">
                    <span
                        className={"leg-fancy-arrow-text"}>{messageContext.getMessage("characterCreation.headline")}</span>
                    </ArrowWrapperHeadline>
                    <div className={"leg-box leg-w75 leg-background-text leg-p8 leg-center-text leg-m-b4"}>
                        <span>{messageContext.getMessage("characterCreation.description")}</span>
                    </div>

                    <ArrowWrapperText className={"leg-w50"}>
                        <span
                            className={"leg-fancy-arrow-text"}>{messageContext.getMessage("characterCreation.name.description")}
                        </span>
                    </ArrowWrapperText>

                    {/* Name Input */}
                    <div className={"leg-p4 leg-w50"}>
                        <ArrowWrapperText>
                            <div className={"leg-fancy-arrow-text leg-flex-row leg-flex-justify-center"}>
                                <label form="name">{messageContext.getMessage("label.name") + " "}</label>
                                <input required name="name" type="text"
                                       onChange={(e) => setName(e.target.value)}></input>
                            </div>
                        </ArrowWrapperText>
                    </div>

                    {/* Attribute Headline */}
                    <ArrowWrapperHeadline className={"leg-m-b8 leg-m-t12"}>
                        <span className={"leg-fancy-arrow-text"}>
                            {messageContext.getMessage("characterCreation.attribute.headline")}
                        </span>
                    </ArrowWrapperHeadline>

                    {/* Attribute Description */}
                    <div className={"leg-box leg-w75 leg-background-text leg-p8 leg-m-b4"}>
                        <div className={"leg-m-b4"}>
                            <span className={"leg-center-text"}>
                                {messageContext.getMessage("characterCreation.attribute.description")}
                            </span>
                        </div>
                        <div className={"leg-m-b4 leg-text-align-start leg-m-l12"}>
                            <span className={"leg-white-space-pre-wrap"}>
                                {messageContext.getMessage("characterCreation.attribute.introduction")}
                            </span>
                        </div>
                    </div>

                    {/* Attribute Selection */}
                    {attributes.length > 0 && (<>
                        <AttributeSelection className={"leg-w50"}
                                            attributes={attributes} setAttributes={(index) => setAttributes(index)}
                                            attributePoints={attributePoints}
                                            setAttributePoints={(value) => setAttributePoints(value)}/>
                    </>)}

                    {/* Class Selection Headline */}
                    <ArrowWrapperHeadline className={"leg-m-b8 leg-m-t12"}>
                    <span
                        className={"leg-fancy-arrow-text"}>{messageContext.getMessage("characterCreation.class.headline")}</span>
                    </ArrowWrapperHeadline>

                    {/* Class Selection Description */}
                    <div className={"leg-box leg-w75 leg-background-text leg-p8 leg-center-text leg-m-b4"}>
                        <span>{messageContext.getMessage("characterCreation.class.description")}</span>
                    </div>

                    {/* Class Selection Carousel */}
                    <ClassSelection className={"leg-m-b12"}
                                    playerClasses={playerClasses} activePlayerClass={activePlayerClass}
                                    setActivePlayerClass={(activePlayerClassId) => setActivePlayerClass(activePlayerClassId)}
                    >

                    </ClassSelection>
                    <button className={"leg-fancy-arrow-button"}
                            onClick={(event) => {
                                formSubmit();
                                event.preventDefault()
                            }}>
                        <ArrowWrapperClickable>
                            <span className={"leg-fancy-arrow-text"}>
                                {messageContext.getMessage("button.create")}
                            </span>
                        </ArrowWrapperClickable>
                    </button>

                </form>)
            }
        </>

    )
}