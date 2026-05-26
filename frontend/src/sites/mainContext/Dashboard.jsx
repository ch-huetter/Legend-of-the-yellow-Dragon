import {useEffect, useState} from "react";
import {initDashboard, postDismissal} from "../../api/sites/dashboardApi.js";
import {useLayoutContext} from "../../layout/MainLayout.jsx";
import {getMessageFromMap} from "../../service/util/messageGetter.js";
import {ArrowWrapperClickable} from "../../layout/components/ArrowWrapper.jsx";


export function Dashboard(props) {
    const [messages, setMessages] = useState({});
    const [dashboardMessages, setDashboardMessages] = useState({});
    const [loading, setLoading] = useState(true);
    const layoutContext = useLayoutContext();


    useEffect(() => {
        console.log("Loading...")
        initDashboard().then((response) => {
            layoutContext.setBackgroundUrl("/images/background/studyroom_background.png");
            setMessages(response.messages);
            setDashboardMessages(response.dashboardMessages);
            setLoading(false);
        })
    }, [])

    async function dismissMessage(key) {
        await postDismissal({key}).then(response => {
            setDashboardMessages(response);
        }, error => console.log(error));
    }

    return (
        <div className={"leg-flex-column"}>
            <div className={"leg-flex-column leg-box leg-m-b8 leg-p8 leg-background-text"}>
                <span className={"leg-center-text"}>
                    {!loading ? getMessageFromMap(messages, "dashboard.description") : "Loading..."}
                </span>
                {!loading && dashboardMessages.length === 0 && (
                    <span className={"leg-center-text"}>
                                    {getMessageFromMap(messages, "dashboard.description.noMessages")}
                    </span>
                )}
            </div>
            {!loading && (
                /* Message Cards */
                <div className="leg-flex-column">
                    {dashboardMessages.length > 0 && dashboardMessages.map((message) =>
                        <div key={message.messageKey}
                             className="leg-flex-column leg-box leg-background-text leg-w80 leg-m-a">
                            <div className={"leg-item-top-right " + getBackgroundClass(message.urgency)}></div>
                            <span
                                className={"leg-p8 leg-center-text leg-white-space-pre-wrap"}>{getMessageFromMap(messages, message.messageKey)}</span>
                            {message.dismissable && (
                                <button className={"leg-fancy-arrow-button leg-align-self-end"} onClick={() => {
                                    dismissMessage(message.messageKey);
                                }}>
                                    <ArrowWrapperClickable>
                                        <span
                                            className={"leg-fancy-arrow-text"}>{getMessageFromMap(messages, "button.seen")}</span>
                                    </ArrowWrapperClickable>
                                </button>
                            )}
                        </div>
                    )}
                    {/* No Messages */}

                </div>
            )}

        </div>
    )

    function getBackgroundClass(urgency) {
        switch (urgency) {
            case "NORMAL":
                return "leg-background-ok";
            case "IMPORTANT":
                return "leg-background-warning";
            case "CRITICAL" :
                return "leg-background-critical";
            default:
                return "";
        }
    }
}