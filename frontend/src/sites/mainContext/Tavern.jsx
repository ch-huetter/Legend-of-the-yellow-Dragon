import {useEffect, useState} from "react";
import {initTavern, restCharakter} from "../../api/sites/tavernApi.js";
import {getMessageFromMap} from "../../service/util/messageGetter.js";
import {ArrowWrapperClickable, ArrowWrapperError} from "../../layout/components/ArrowWrapper.jsx";
import {useLayoutContext} from "../../layout/MainLayout.jsx";

export function Tavern(props) {
    const [messages, setMessages] = useState({});
    const [loading, setLoading] = useState(true);
    const layoutContext = useLayoutContext();
    const [error, setError] = useState(false);
    const [errorMsg, setErrorMsg] = useState("");

    useEffect(() => {
        layoutContext.setBackgroundUrl("/images/background/tavern_background.png");
        initTavern().then(response => {
            setMessages(response.messages);
            setLoading(false);
        });
    }, []);

    async function rest() {
        await restCharakter().then((response) => {
            console.log("Rest Response is ", response);
            if (response.status === "ERROR") {
                setError(true);
                switch (response.error) {
                    case "INSUFFICIENT_GOLD" :
                        setErrorMsg(messages["error.tavern.insufficientGold"]);
                        break;
                    case "FULL_HEALTH" :
                        setErrorMsg(messages["error.tavern.fullHealth"]);
                        break;
                }
            } else if (response.status === "SUCCESS") {
                setError(false);
                layoutContext.setCharacter(response.playerCharacter);
            }

        })
    }

    return (
        <div className={"leg-h100 leg-align-content-end"}>
            <div className={"leg-flex-column leg-box"}>
                <div className={"leg-p8"}>
                    <span className={"leg-box-content leg-p4 leg-center-text leg-white-space-pre-wrap"}>
                        {getMessageFromMap(messages, "tavern.description")}
                    </span>
                </div>
                {error && (
                    <div className={"leg-p8"}>
                        <ArrowWrapperError>
                            <div className={"leg-fancy-arrow-text leg-flex-row leg-flex-justify-center leg-p4"}>
                            <span className={"leg-fancy-arrow-text"}>
                                {errorMsg}
                            </span>
                            </div>
                        </ArrowWrapperError>
                    </div>
                )}

                <div className={"leg-display-flex leg-flex-justify-center leg-p8"}>
                    <button className={"leg-fancy-arrow-button"} onClick={() => rest()}>
                        <ArrowWrapperClickable>
                                <span className={"leg-fancy-arrow-text "}>
                                    {getMessageFromMap(messages, "tavern.restButton")}
                                </span>
                        </ArrowWrapperClickable>
                    </button>
                </div>
            </div>
        </div>
    )
}