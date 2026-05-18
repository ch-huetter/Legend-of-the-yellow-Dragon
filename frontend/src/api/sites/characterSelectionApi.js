import {apiFetch} from "../apiClient.js";

export function initCharacterSelection() {
    return apiFetch("/api/game/characterSelection/init");
}