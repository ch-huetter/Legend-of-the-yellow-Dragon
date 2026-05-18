import {apiFetch, apiPost} from "../apiClient.js";

export async function initTavern() {
    return await apiFetch("/api/game/tavern/init");
}

export async function restCharakter() {
    return await apiPost("/api/game/tavern/rest");
}