import {checkGlobalContext} from "../api/sites/globalContextApi.js";

export function initializeGlobalValues() {
    const messageHash = localStorage.getItem("messageHash") === null ? "" : JSON.parse(localStorage.getItem("messageHash"));

    checkGlobalContext({"messageHash": messageHash}).then((response) => {
        if (response.status === "OUTDATED") {
            localStorage.setItem("messages", JSON.stringify(response.messages));
            localStorage.setItem("messageHash", JSON.stringify(response.messageHash));
        } else if (response.status === "OK") {
            //Nothing
        }
    }, (error) => {
        throw new Error("Unable to retrieve global context.");
    })

}













