import {apiFetch, apiPost} from "../apiClient.js";

export async function initDashboard() {
    return await apiFetch("/api/game/dashboard/init");
}

export async function postDismissal(data) {
    return await apiPost("/api/game/dashboard/dismiss", data);
}