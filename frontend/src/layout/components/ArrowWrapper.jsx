import {ArrowFancyLeft, ArrowFancyRight, BorderFancy} from "./ArrowFancySvg.jsx"

export function ArrowWrapperText({children}) {
    return (
        <div className="leg-flex-row text">
            <ArrowFancyLeft showDiamond={false}/>
            <div className="leg-fancy-arrow-container ">
                <BorderFancy/>
                {children}
            </div>
            <ArrowFancyRight showDiamond={false}/>
        </div>
    )
}

export function ArrowWrapperHeadline(props) {
    return (
        <div className={"leg-flex-row leg-fancy-arrow-headline headline leg-w100 " + props.className}>
            <ArrowFancyLeft showDiamond={true}/>
            <div className="leg-fancy-arrow-container ">
                <BorderFancy/>
                {props.children}
            </div>
            <ArrowFancyRight showDiamond={true}/>
        </div>
    )
}

export function ArrowWrapperClickable({children}) {
    return (
        <div className="leg-flex-row leg-fancy-arrow-headline link">
            <ArrowFancyLeft showDiamond={false}/>
            <div className="leg-fancy-arrow-container ">
                <BorderFancy/>
                {children}
            </div>
            <ArrowFancyRight showDiamond={false}/>
        </div>
    )
}

export function ArrowWrapperError({children}) {
    return (
        <div className="leg-flex-row leg-fancy-arrow-headline error">
            <ArrowFancyLeft showDiamond={false}/>
            <div className="leg-fancy-arrow-container ">
                <BorderFancy/>
                {children}
            </div>
            <ArrowFancyRight showDiamond={false}/>
        </div>
    )
}


