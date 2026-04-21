import {createContext, useCallback, useContext, useEffect, useState} from "react";
import {apiFetch} from "../api/apiClient.js";
import {Outlet} from "react-router-dom";
import {SidebarNavigation} from "../components/SidebarNavigation.jsx";
import {HeaderInformationBox} from "../components/HeaderInformationBox.jsx"


const MainLayoutContext = createContext(null);

export function MainLayout() {
    const [backgroundUrl, setBackgroundUrl] = useState("");
    const [character, setCharacter] = useState({});
    const [data, setData] = useState(null);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    const load = useCallback(async () => {
        setLoading(true);
        setError(null);

        try {
            const response = await apiFetch("/api/game/mainInit");

            const json = await response.json();
            setData(json);
        } catch (err) {
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
        data,
        loading,
        error,
        reload: load
    };

    return (
        <MainLayoutContext.Provider value={layoutData}>
            <div className="main-wrapper-fullscreen background-image"
                 style={{background: " url(/images/background/login_background.png"}}>
                <div className="leg-flex-row">
                    <div className="leg-sidebar-size">
                    </div>
                    <HeaderInformationBox/>
                </div>
                <div className="leg-flex-row leg-h100">
                    <SidebarNavigation/>
                    <Outlet/>
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