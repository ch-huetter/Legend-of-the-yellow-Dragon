import {useEffect, useState} from "react";
import {apiFetch, apiPost} from "../api/apiClient";
import {getMessageFromDto} from "../util/messageGetter.js"

export function Login() {
    let [formData, setFormData] = useState({username: "", password: ""});
    let [dto, setDto] = useState({});


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
            console.log("Response received")
            console.log(res);
            if (res.status === 200) {
                console.log("Successfully logged in");
                window.location = "/game/characters"
            } else {
                console.log("Error while logging in");
            }

        })
    }

    useEffect(() => {
        async function load() {
            return await apiFetch("/api/game/loginInit");
        }

        load().then((result) => {
            setDto(result);
        });

    }, []);

    return <form method="post" onSubmit={(event) => onSubmit(event)} style={{width: "100%"}}>
        <div>
            <div className="leg-flex-row mb-3">
                <div className="leg-flex-column">
                    <div className="">
                        <h1>Legende des gelben Drachen</h1>
                    </div>
                </div>
            </div>
            <div>
                <div>
                    <label form="username">
                        {getMessageFromDto(dto, "label.user.loginName")}
                    </label>
                    <input required name="username" type="text" value={formData.username}
                           onChange={e => handleChange(e)}/>
                </div>
                <div>
                    <label form="password">
                        {getMessageFromDto(dto, "label.user.password")}
                    </label>
                    <input required name="password" type="password" value={formData.password}
                           onChange={e => handleChange(e)}/>
                </div>
                <div>
                    <button type="submit">

                    </button>
                </div>
            </div>
        </div>
    </form>
}
