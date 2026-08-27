import {useMessageContext} from "../MessageContext.jsx";

export function ClassSelection({playerClasses, activePlayerClass, setActivePlayerClass, className = ""}) {
    const messageContext = useMessageContext();

    return (<div className={"leg-flex-row leg-w75 leg-align-item-center " + className}>
            <button className={"leg-round-input leg-m-r8"}
                    onClick={(e) => {
                        setActivePlayerClass(activePlayerClass - 1 < 0 ? playerClasses.length - 1 : activePlayerClass - 1);
                        e.preventDefault()
                    }}>
                {"<"}
            </button>

            {playerClasses.map((playerClass, index) => {
                return (
                    <div key={playerClass}
                         style={{display: activePlayerClass === index ? "block" : "none"}}
                         className={"leg-w90"}>
                        <div
                            className={"leg-box leg-background-text leg-flex-column leg-boxSizing-borderBox leg-p8"}
                            onClick={() => setActivePlayerClass(index)}>
                            <span className={"leg-m-b8"}>
                                    {messageContext.getMessage("playerClass." + playerClass.toLowerCase() + ".name")}
                                </span>
                            <span className={"leg-m-b4"}>
                                    {messageContext.getMessage("playerClass." + playerClass.toLowerCase() + ".description")}
                                </span>
                            <span className={"leg-text-positive leg-m-b4"}>
                                    {messageContext.getMessage("playerClass." + playerClass.toLowerCase() + ".positives")}
                                </span>
                            <span className={"leg-text-danger leg-m-b4"}>
                                    {messageContext.getMessage("playerClass." + playerClass.toLowerCase() + ".negatives")}
                                </span>
                        </div>
                    </div>
                )
            })}

            <button className={"leg-round-input leg-m-l8"}
                    onClick={(e) => {
                        setActivePlayerClass(activePlayerClass + 1 > playerClasses.length - 1 ? 0 : activePlayerClass + 1);
                        e.preventDefault()
                    }}>
                {">"}
            </button>
        </div>
    );
}