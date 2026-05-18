import {Link} from "react-router-dom";
import {useLayoutContext} from "../layout/MainLayout.jsx"
import {ArrowWrapperClickable, ArrowWrapperHeadline} from "../layout/components/ArrowWrapper.jsx";
import {ArrowFoldable} from "../layout/components/ArrowFancySvg.jsx";
import {useState} from "react";

export function SidebarNavigation() {
    const layoutContext = useLayoutContext();
    const [visible, setVisible] = useState(true);
    //TODO Changing sidebar from static to a dynamic Server Based Version!
    return (
        <div className={"leg-sidebar-wrapper " + (visible ? "visible" : "")}>
            <aside className="leg-sidebar">
                <ArrowWrapperHeadline>
                    <span className="leg-fancy-arrow-text">Übersicht</span>
                </ArrowWrapperHeadline>
                <Link className="leg-fancy-arrow-link" to="/game/characterSelection">
                    <ArrowWrapperClickable>
                        <span className="leg-fancy-arrow-text">Character wechseln</span>
                    </ArrowWrapperClickable>
                </Link>
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