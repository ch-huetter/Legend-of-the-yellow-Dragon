import {useEffect, useState} from "react";
import {apiFetch, apiPost} from "../api/apiClient";
import {getMessageFromDto} from "../service/util/messageGetter.js"
import {ArrowWrapperClickable, ArrowWrapperError, ArrowWrapperText} from "../layout/components/ArrowWrapper.jsx";

const landingPageUrl = import.meta.env.VITE_LANDING_PAGE;

export function Login() {
    let [formData, setFormData] = useState({username: "", password: ""});
    let [dto, setDto] = useState({});
    const [error, setError] = useState(false);

    function handleChange(event) {
        const {name, value} = event.target;
        setFormData(prev => ({
            ...prev,
            [name]: value
        }))
    }

    async function onSubmit(event) {
        event.preventDefault();
        await apiPost("api/auth/login", formData).then((res) => {
            if (res.success) {
                window.location.href = landingPageUrl;
            } else {
                console.log("Error logged in");
                setError(true);
            }
        })
    }

    useEffect(() => {
        async function load() {
            return await apiFetch("/api/game/loginInit");
        }

        load().then((result) => {
            if (result.isLoggedIn) {
                window.location.href = landingPageUrl;
            }
            setDto(result);
        });

    }, []);

    return <form method="post" onSubmit={(event) => onSubmit(event)} style={{width: "100%"}}>
        <div className={"main-wrapper-fullscreen leg-background-image"}
             style={{background: "url(/images/background/login_background.png)"}}>
            <div className="leg-flex-column leg-h100">
                <div className={"leg-flex-row mb-3 leg-flex-justify-center"}>
                    <div className={"leg-box leg-p8"}>
                        <h1>Die Legende des gelben Drachen</h1>
                    </div>
                </div>
                {/* Error Banner */}
                <div className={"leg-box leg-m-a leg-p8"}>
                    {error && (
                        <div className={"leg-p4"}>
                            <ArrowWrapperError>
                                <div className={"leg-fancy-arrow-text leg-flex-row leg-flex-justify-center leg-p4"}>
                                    <span>
                                        {getMessageFromDto(dto, "error.login.wrongLoginCredentials")}
                                    </span>
                                </div>
                            </ArrowWrapperError>
                        </div>
                    )}
                    {/* Input for Login Name */}
                    <div className={"leg-p4"}>
                        <ArrowWrapperText>
                            <div className={"leg-fancy-arrow-text leg-flex-row leg-flex-justify-center leg-p4"}>
                                <label form="username">
                                    {getMessageFromDto(dto, "label.user.loginName")}
                                    <input required name="username" type="text" value={formData.username}
                                           onChange={e => handleChange(e)}/>
                                </label>
                            </div>
                        </ArrowWrapperText>
                    </div>
                    {/* Input for Password */}
                    <div className={"leg-p4"}>
                        <ArrowWrapperText>
                            <div className={"leg-fancy-arrow-text leg-flex-row leg-flex-justify-center"}>
                                <label form="password">
                                    {getMessageFromDto(dto, "label.user.password")}
                                    <input required name="password" type="password"
                                           onChange={e => handleChange(e)}/>
                                </label>

                            </div>
                        </ArrowWrapperText>

                    </div>
                    <div className={"leg-display-flex leg-flex-justify-center leg-p4"}>
                        <button className={"leg-fancy-arrow-button"} type="submit">
                            <ArrowWrapperClickable>
                                <div className={"leg-fancy-arrow-text leg-flex-row leg-flex-justify-center leg-p4"}>
                                    <span className={"leg-fancy-arrow-text"}> Login </span>
                                </div>
                            </ArrowWrapperClickable>
                        </button>
                    </div>
                </div>
            </div>
        </div>
    </form>
}

function wrongCredentialsError() {

}

