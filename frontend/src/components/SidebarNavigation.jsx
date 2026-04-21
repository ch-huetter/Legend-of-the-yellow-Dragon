import {Link} from "react-router-dom";
import {useContext} from "react";
import {useLayoutContext} from "../layout/MainContext.jsx"
import {ArrowWrapper} from "../layout/elements/ArrowWrapper.jsx";

export function SidebarNavigation() {
    const data = useContext(useLayoutContext());
    console.log(data);


    return <aside className="leg-sidebar">
        <Link className="leg-fancy-arrow-link" to="/characterSelection">
            <ArrowWrapper>
                <span className="leg-fancy-arrow-link-text">Character Selection</span>
            </ArrowWrapper>
        </Link>
    </aside>
}