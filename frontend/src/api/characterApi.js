import {apiFetch, apiPost} from "./apiClient.js";

export function fetchCharactersSortedByActiveThenLevel() {
    return apiFetch("/api/character/getUserCharactersSortedByActiveThenLevel");
}

export function postNewActiveCharacterName(name) {

    return apiPost("/api/character/setNewActiveCharacterByName", {characterName: name});
}
