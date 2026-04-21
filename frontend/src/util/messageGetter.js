export function getMessageFromDto(dto, key) {
    if (dto["messages"] !== undefined && dto["messages"][key] !== undefined) {
        return dto["messages"][key];
    }
    return "";
}