import {apiPost} from "../apiClient.js";

export async function checkGlobalContext(data = {}) {
    return apiPost("/api/game/globalContext/checkGlobalContext", data);
}