import {apiPost} from "../apiClient.js";

export function logout() {
    return apiPost("/api/auth/logout", {})
}