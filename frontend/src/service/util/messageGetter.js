const globalMessages = localStorage.getItem("messages");

export function getMessageFromDto(dto, key) {
    if (dto["messages"] === undefined) {
        return "";
    }
    if (dto["messages"][key] !== undefined) {
        return dto["messages"][key];
    } else {
        return globalMessages["error.missingKey"] + key;
    }
}

export function getMessageFromMap(map, key) {
    if (map[key] === undefined) {
        return globalMessages["error.missingKey"] + key;
    } else {
        return map[key];
    }

}