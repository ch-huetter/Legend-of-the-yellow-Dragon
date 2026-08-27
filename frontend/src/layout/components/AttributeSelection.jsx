import {ArrowWrapperText} from "./ArrowWrapper.jsx";
import {useState} from "react";
import {useMessageContext} from "../MessageContext.jsx";

export function AttributeSelection({
                                       className,
                                       attributes,
                                       setAttributes,
                                       attributePoints,
                                       setAttributePoints
                                   }) {
    const [canIncrease, setCanIncrease] = useState(true);

    const messageContext = useMessageContext();

    function increaseAttribute(attributeIndex) {
        if (attributePoints > 0) {
            const newAttributes = [...attributes];
            newAttributes[attributeIndex].value++;
            newAttributes[attributeIndex].canDecrease = isDecreasable(attributeIndex);
            setAttributes(newAttributes);
            const newAttributePoints = attributePoints - 1;
            setAttributePoints(newAttributePoints);
            checkIsIncreasable(newAttributePoints);
        }
    }

    function decreaseAttribute(attributeIndex) {
        const newAttributes = [...attributes];
        const attribute = attributes[attributeIndex];
        if (attribute.value > attribute.minValue) {
            newAttributes[attributeIndex].value--;
            newAttributes[attributeIndex].canDecrease = isDecreasable(attributeIndex);
            setAttributes(newAttributes);
            const newAttributePoints = attributePoints + 1;
            setAttributePoints(newAttributePoints);
            checkIsIncreasable(newAttributePoints);
        }
    }

    function checkIsIncreasable(newAttributePoints) {
        setCanIncrease(!(newAttributePoints <= 0))
    }

    function isDecreasable(attributeIndex) {
        const attribute = attributes[attributeIndex];
        return !(attribute.value <= attribute.minValue);
    }

    return (
        <>
            <ArrowWrapperText className={"leg-m-b4 leg-w50"}>
                        <span
                            style={{zIndex: 1}}>{messageContext.getMessage("label.attributePointDisplay") + " " + attributePoints}</span>
            </ArrowWrapperText>
            <div className={"leg-flex-column "}>
                <div className={"leg-m-a leg-w50"}>
                    {attributes.map((attribute, index) => (
                        <div className={"leg-flex-row leg-m-b4"} key={attribute.key}>
                            <button className={"leg-round-input leg-m-r8"} disabled={!attribute.canDecrease}
                                    onClick={(e) => {
                                        decreaseAttribute(index);
                                        e.preventDefault()
                                    }}>
                                -
                            </button>

                            <ArrowWrapperText className={"leg-w90"}>
                                <div style={{zIndex: 1}} className={"leg-flex-row"}>
                                        <span>
                                            {messageContext.getMessage("label." + attribute.key) + " "}
                                        </span>
                                    <span>
                                            {attributes[index].value}
                                        </span>
                                </div>
                            </ArrowWrapperText>
                            <button className={"leg-round-input leg-m-l8 "} disabled={!canIncrease} onClick={(e) => {
                                increaseAttribute(index);
                                e.preventDefault()
                            }}>
                                +
                            </button>
                        </div>
                    ))}
                </div>
            </div>
        </>
    )
}