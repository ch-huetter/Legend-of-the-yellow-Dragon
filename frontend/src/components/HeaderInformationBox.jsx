import {useContext, useEffect} from "react";
import {useLayoutContext} from "../layout/MainContext.jsx";

export function HeaderInformationBox() {
    const data = useContext(useLayoutContext());

    useEffect(() => {

    }, []);

    return <div className="leg-header"
                style={{backgroundImage: `url(${data === undefined ? "" : data.backgroundImage})`}}>
        <p>
            Hier könnte ihr Header stehen
        </p>
    </div>
}