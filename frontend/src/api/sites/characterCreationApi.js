import {apiFetch, apiPost} from "../apiClient.js";

export function init() {
    return apiFetch("/api/game/characterCreation/init");
}

export function submitCharacterCreationRequest(data) {
    return apiPost("/api/game/characterCreation/submitCharacterCreationRequest", data);
}