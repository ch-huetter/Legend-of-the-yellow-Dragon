import {ArrowFancyLeft, ArrowFancyRight, BorderFancy} from "./ArrowFancySvg.jsx"

export function ArrowWrapper({children}) {
    return (
        <div className="leg-flex-row">
            <ArrowFancyLeft/>
            <div className="leg-fancy-arrow-link-container">
                <BorderFancy/>
                {children}
            </div>
            <ArrowFancyRight/>
        </div>

    )
}

export function ArrowWrapperDiamond({}) {

}