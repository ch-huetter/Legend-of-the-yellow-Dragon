import {useEffect} from "react";
import {logout} from "../api/sites/logoutApi.js";

const landingPageUrl = import.meta.env.VITE_LANDING_PAGE;

export function Logout() {

    const globalMessages = JSON.parse(localStorage.getItem("messages"));

    useEffect(() => {
        logout().then((response) => {
            setTimeout(() => {
                window.location.href = "/";
            }, 3000)
        })
    }, []);


    return (<div className={"main-wrapper-fullscreen leg-background-image"}
                 style={{background: "url(/images/background/login_background.png)"}}>
        <div className={"leg-flex-column leg-h100 leg-flex-justify-center"}>
            <div className={"leg-display-flex leg-flex-justify-center"}>
                <div className={"leg-box leg-p8"}>
                    <span>{globalMessages["logout.goodbye"]}</span>
                </div>
            </div>
        </div>
    </div>)

}