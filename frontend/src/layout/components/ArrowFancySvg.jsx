export function ArrowFancyLeft({showDiamond}) {
    return ArrowFancy("left", showDiamond);
}

export function ArrowFancyRight({showDiamond}) {
    return ArrowFancy("right", showDiamond);
}

export function BorderFancy() {
    return (<svg xmlns="http://www.w3.org/2000/svg" viewBox="1 0 72 56"
                 preserveAspectRatio="none" className="leg-fancy-border">
        <path style={{stroke: "rgb(0, 0, 0)"}} d="M -1 1 L 74 1 L 74 55 L -1 55 L -1 1 Z"
              className="leg-fancy-arrow-primary "/>
    </svg>);
}

export function ArrowFoldable() {
    return (<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 10 9" className="leg-fancy-arrow-foldable">
        <path style={{fill: "rgb(25, 24, 185)", strokeWidth: "0.2px", strokeLinejoin: "round"}}
              d="M 0.5 1.5 L 0.5 8.5 L 4.5 4.5 L 0.5 0.5 L 0.5 1.5 Z"/>
        <path style={{fill: "rgb(81, 84, 250)", strokeWidth: "0.2px", strokeLinejoin: "round"}}
              d="M 4.5 4.5 L 9.5 4.5 L 0.5 0.5 L 4.5 4.5 Z"/>
        <path style={{fill: "rgb(10, 12, 106)", strokeWidth: "0.2px", strokeLinejoin: "round"}}
              d="M 0.5 8.5 L 4.5 4.5 L 9.5 4.5 L 0.5 8.5 Z"/>
    </svg>)
}

function ArrowFancy(direction = "left", showDiamond = false, addClass = "") {
    return (<svg xmlns="http://www.w3.org/2000/svg" preserveAspectRatio="none"
                 viewBox={showDiamond ? "0 0 66 56" : "19 0 47 56"}
                 className={"leg-fancy-arrow-" + direction + (showDiamond ? "-b " : " ") + addClass}>
        {showDiamond ? <Diamond/> : null}
        <path className="leg-fancy-arrow-primary"
              d="M 66 55 L 59 55 C 55 55 49 52 45 42 C 41 32 35 28 31 28 C 35 28 41 24 45 14 C 49 4 55 1 59 1 L 66 1"/>
        <path style={{stroke: "rgb(0, 0, 0)", fill: "none"}}
              d="M 54 28 L 31 28 C 35 28 41 32 45 42 C 49 52 55 55 59 55 C 63 55 65 55 66 55"
        />
        <path style={{stroke: "rgb(0, 0, 0)", fill: "none"}}
              d="M 54 28 L 31 28 C 35 28 41 24 45 14 C 49 4 55 1 59 1 C 63 1 65 1 66 1"
        />
        <path style={{stroke: "rgb(0, 0, 0)"}}
              d="M 32 28 L 19 28 C 23 28 29 25 33 14 C 37 4 43 1 47 1 L 59 1 C 55 1 49 4 45 14 C 41 24 35 28 31 28 L 32 28 Z"
              className="leg-fancy-arrow-secondary"/>
        <path style={{stroke: "rgb(0, 0, 0)"}}
              d="M 32 28 L 19 28 C 23 28 29 31 33 42 C 37 52 43 55 47 55 L 59 54.968 C 55 54.968 49 52 45 42 C 41 32 35 28 31 28 L 32 28 Z"
              className="leg-fancy-arrow-secondary"/>
    </svg>)
}

function Diamond() {
    return <polyline style={{stroke: "rgb(0, 0, 0)"}} points="20 28 14 23 2 28 14 33 20 28"
                     className="leg-fancy-arrow-diamond"/>
}


