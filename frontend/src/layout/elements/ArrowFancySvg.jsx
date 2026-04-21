export function ArrowFancyLeft() {
    return ArrowFancy("left");
}

export function ArrowFancyRight() {
    return ArrowFancy("right");
}


export function BorderFancy() {
    return (<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 73 56"
                 preserveAspectRatio="none" className="leg-fancy-border">
        <path style={{stroke: "rgb(0, 0, 0)"}} d="M -2 1 L 75 1 L 75 55 L -2 55 L -2 1 Z"
              className="leg-fancy-arrow-primary "/>

    </svg>);
}

function ArrowFancy(direction = "left", showDiamond = false) {
    return (<svg xmlns="http://www.w3.org/2000/svg" width="66px" height="56px" preserveAspectRatio="none"
                 viewBox="0 0 66 56" className={"leg-fancy-arrow-" + direction}>
        {showDiamond ? <Diamond/> : null}
        <path className="leg-fancy-arrow-primary"
              d="M 66 55 L 59.03 55 C 55 55 49 52 45 42 C 41 32 35 28 31 28 C 35 28 41 24 45 14 C 49 4 55 1 59 1 L 66 1"/>
        <path style={{stroke: "rgb(0, 0, 0)", fill: "none"}}
              d="M 54 28 L 31 28 C 35 28 41 32 45 42 C 49 52 55 55 59 55 C 63 55 65 55 66 55"
        />
        <path style={{stroke: "rgb(0, 0, 0)", fill: "none"}}
              d="M 54 28 L 31 28 C 35 28 41 24 45 14 C 49 4 55 1 59 1 C 63 1 65 1 66 1"
        />
        <path style={{stroke: "rgb(0, 0, 0)"}}
              d="M 32.03 28 L 19 28 C 23 28 29 25 33 14 C 37 4 42.877 1 47 1 L 59.03 1.032 C 55.03 1.032 49.03 4.032 45.03 14.017 C 41.03 24.032 35 28 31.03 28 L 32.03 28 Z"
              className="leg-fancy-arrow-secondary"/>
        <path style={{stroke: "rgb(0, 0, 0)"}}
              d="M 32.03 28 L 19 28 C 23 28 29 31 33 42 C 37 52 42.877 55 47 55 L 59.03 54.968 C 55.03 54.968 49.03 51.968 45.03 41.983 C 41.03 31.968 35 28 31.03 28 L 32.03 28 Z"
              className="leg-fancy-arrow-secondary"/>
    </svg>)
}

function Diamond() {
    return <polyline style={{stroke: "rgb(0, 0, 0)"}} points="20 28 14 23 2 28 14 33 20 28"
                     className="leg-fancy-arrow-diamond"/>
}