import {createContext, useCallback, useContext, useEffect, useState} from "react";
import {apiFetch} from "../api/apiClient.js";

import {Outlet} from "react-router-dom";
import {SidebarNavigation} from "./components/SidebarNavigation.jsx";
import {HeaderInformationBox} from "./components/HeaderInformationBox.jsx"


const MainLayoutContext = createContext(null);

export function MainLayout() {
    const [character, setCharacter] = useState({});
    const [messages, setMessages] = useState({});
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    //Set by the loaded Side itself. Does not get Initialized on mainInit call
    const [backgroundUrl, setBackgroundUrl] = useState("/images/background/login_background.png");
    const load = useCallback(async () => {
        setLoading(true);
        setError(null);

        try {
            const response = await apiFetch("/api/game/mainInit");
            setCharacter(response["playerCharacter"]);
            setMessages(response["messages"]);
        } catch (err) {
            console.log(err);
            setError(err);
        } finally {
            setLoading(false);
        }
    }, []);

    useEffect(() => {
        load();
    }, [load]);

    const layoutData = {
        character,
        setCharacter: setCharacter,
        backgroundUrl,
        setBackgroundUrl: setBackgroundUrl,
        messages,
        loading,
        error,
        reload: load
    };

    return (
        <MainLayoutContext.Provider value={layoutData}>
            <div className="main-wrapper-fullscreen leg-background-image"
                 style={{background: "url(" + backgroundUrl + ")"}}>
                <SidebarNavigation/>
                <div className="leg-flex-column leg-h100 leg-align-item-center">
                    <HeaderInformationBox character={character} loading={loading} messages={messages}/>
                    <div className={"leg-main-layout-content"}>
                        <Outlet/>
                    </div>
                </div>
            </div>
        </MainLayoutContext.Provider>
    );
}

export function useLayoutContext() {
    const context = useContext(MainLayoutContext);

    if (!context) {
        throw new Error("useLayoutContext must be used inside MainLayoutContext.Provider");
    }

    return context;
}