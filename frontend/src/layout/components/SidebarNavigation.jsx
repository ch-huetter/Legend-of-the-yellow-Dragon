import {Link} from "react-router-dom";
import {useLayoutContext} from "../MainLayout.jsx"
import {ArrowWrapperClickable, ArrowWrapperHeadline} from "./ArrowWrapper.jsx";
import {ArrowFoldable} from "./ArrowFancySvg.jsx";
import {useState} from "react";

export function SidebarNavigation() {
    const layoutContext = useLayoutContext();
    const [visible, setVisible] = useState(true);
    //TODO Changing sidebar from static to a dynamic Server Based Version!
    return (
        <div className={"leg-sidebar-wrapper " + (visible ? "visible" : "")}>
            <aside className="leg-sidebar">
                <ArrowWrapperHeadline className={"leg-m-b4"}>
                    <span className="leg-fancy-arrow-text">Zuhause</span>
                </ArrowWrapperHeadline>
                <Link className="leg-fancy-arrow-link" to="/game/dashboard">
                    <ArrowWrapperClickable>
                        <span className={"leg-fancy-arrow-text"}>Arbeitszimmer</span>
                    </ArrowWrapperClickable>
                </Link>
                <ArrowWrapperHeadline className={"leg-m-b4"}>
                    <span className="leg-fancy-arrow-text">Altar der Seelen</span>
                </ArrowWrapperHeadline>
                <Link className="leg-fancy-arrow-link" to="/game/characterCreation">
                    <ArrowWrapperClickable>
                        <span className="leg-fancy-arrow-text">Character erstellen</span>
                    </ArrowWrapperClickable>
                </Link>
                <Link className="leg-fancy-arrow-link" to="/game/characterSelection">
                    <ArrowWrapperClickable>
                        <span className="leg-fancy-arrow-text">Character wechseln</span>
                    </ArrowWrapperClickable>
                </Link>
                <ArrowWrapperHeadline className={"leg-m-b4"}>
                    <span className="leg-fancy-arrow-text">Stadt</span>
                </ArrowWrapperHeadline>
                <Link className="leg-fancy-arrow-link" to="/game/tavern">
                    <ArrowWrapperClickable>
                        <span className="leg-fancy-arrow-text">Taverne</span>
                    </ArrowWrapperClickable>
                </Link>
                <Link className="leg-fancy-arrow-link" to="/logout">
                    <ArrowWrapperClickable>
                        <span className="leg-fancy-arrow-text">Logout</span>
                    </ArrowWrapperClickable>
                </Link>

            </aside>
            <div className="leg-fancy-arrow-foldable-wrapper vert" onClick={() => setVisible(!visible)}>
                <ArrowFoldable/>
            </div>
        </div>
    )

    function changeVisibility() {
        setVisible(!visible);
    }

}