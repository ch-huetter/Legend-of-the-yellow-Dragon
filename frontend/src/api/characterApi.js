import {apiPost} from "./apiClient.js";

export function postNewActiveCharacterName(name) {
    return apiPost("/api/game/character/setNewActiveCharacterByName", {characterName: name});
}
